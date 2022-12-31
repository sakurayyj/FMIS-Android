package com.example.fmis.util;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBOpenHelper {

//    /*
//     * 连接数据库
//     * */
//    public static Connection getConn(){
//        Connection conn = null;
//        try {
//            Class.forName(diver);
//            conn = (Connection) DriverManager.getConnection(url,user,password);//获取连接
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//}

    private static String driver = "com.mysql.cj.jdbc.Driver";//MySQL 驱动
    //private static String url = "jdbc:mysql://localhost:3306/data1?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
    private static String url = "jdbc:mysql://192.168.0.101:3306/data1?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    private static String user = "root";//用户名
    private static String password = "LIL22006";//密码


    /**
     * 连接数据库
     */

    public static Connection getConn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                try {
                    Class.forName(driver);//获取MYSQL驱动
                } catch (ClassNotFoundException e) {
                    Log.d("data", "驱动失败");
                    e.printStackTrace();
                }
                try {
                    conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
                    if(conn != null){
                        System.out.println("成功");

                    }else{
                        System.out.println("失败");
                    }
                } catch (SQLException e) {
                    Log.d("data","数据库连接失败");
                    e.printStackTrace();
                }
            }
        }).start();
        //return conn;
        return null;
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}