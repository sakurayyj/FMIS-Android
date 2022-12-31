package com.example.fmis.util;

import android.util.Log;

import com.example.fmis.adapter.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static DBService dbService=null;

    /**
     * 构造方法 私有化
     * */

    public DBService(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */

    public static DBService getDbService(){
        if(dbService==null){
            dbService=new DBService();
        }
        return dbService;
    }

    /**
     * 获取数据
     * */

    public List<Data> getUserData(){
        //结果存放集合
        List<Data> list=new ArrayList<Data>();
        //MySQL 语句
        String sql="select * from data1";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= (PreparedStatement) conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            Data d=new Data();
                            d.setX1(rs.getInt("x1"));
                            d.setX2(rs.getDouble("x2"));
                            d.setX3(rs.getDouble("x3"));
                            d.setX4(rs.getDouble("x4"));
                            d.setX5(rs.getDouble("x5"));
                            d.setX6(rs.getInt("x6"));
                            d.setX7(rs.getDouble("x7"));
                            d.setX8(rs.getDouble("x8"));
                            d.setX9(rs.getDouble("x9"));
                            d.setX10(rs.getDouble("x10"));
                            d.setX11(rs.getDouble("x11"));
                            d.setX12(rs.getDouble("x12"));
                            d.setX13(rs.getInt("x13"));
                            d.setY(rs.getDouble("y"));
                            d.setYear(rs.getInt("year"));
                            list.add(d);
                            Log.d("data",String.valueOf(d.getYear()));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.d("data","获取失败");
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
        return list;
    }

    /**
     * 修改数据库中某个对象的状态  改
     * */

//    public int updateUserData(String phone){
//        int result=-1;
//        if(!StringUtils.isEmpty(phone)){
//            //获取链接数据库对象
//            conn= DBOpenHelper.getConn();
//            //MySQL 语句
//            String sql="update user set state=? where phone=?";
//            try {
//                boolean closed=conn.isClosed();
//                if(conn!=null&&(!closed)){
//                    ps= (PreparedStatement) conn.prepareStatement(sql);
//                    ps.setString(1,"1");//第一个参数state 一定要和上面SQL语句字段顺序一致
//                    ps.setString(2,phone);//第二个参数 phone 一定要和上面SQL语句字段顺序一致
//                    result=ps.executeUpdate();//返回1 执行成功
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
//        return result;
//    }

    /**
     * 批量向数据库插入数据  增
     * */

//    public int insertUserData(List<User> list){
//        int result=-1;
//        if((list!=null)&&(list.size()>0)){
//            //获取链接数据库对象
//            conn= DBOpenHelper.getConn();
//            //MySQL 语句
//            String sql="INSERT INTO user (name,phone,content,state) VALUES (?,?,?,?)";
//            try {
//                boolean closed=conn.isClosed();
//                if((conn!=null)&&(!closed)){
//                    for(User user:list){
//                        ps= (PreparedStatement) conn.prepareStatement(sql);
//                        String name=user.getName();
//                        String phone=user.getPhone();
//                        String content=user.getContent();
//                        String state=user.getState();
//                        ps.setString(1,name);//第一个参数 name 规则同上
//                        ps.setString(2,phone);//第二个参数 phone 规则同上
//                        ps.setString(3,content);//第三个参数 content 规则同上
//                        ps.setString(4,state);//第四个参数 state 规则同上
//                        result=ps.executeUpdate();//返回1 执行成功
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
//        return result;
//    }

    /**
     * 删除数据 删
     * */

//    public int delUserData(String phone){
//        int result=-1;
//        if((!StringUtils.isEmpty(phone))&&(PhoneNumberUtils.isMobileNumber(phone))){
//            //获取链接数据库对象
//            conn= DBOpenHelper.getConn();
//            //MySQL 语句
//            String sql="delete from user where phone=?";
//            try {
//                boolean closed=conn.isClosed();
//                if((conn!=null)&&(!closed)){
//                    ps= (PreparedStatement) conn.prepareStatement(sql);
//                    ps.setString(1, phone);
//                    result=ps.executeUpdate();//返回1 执行成功
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
//        return result;
//    }

}