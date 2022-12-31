package com.example.fmis.zsgc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.adapter.Data;
import com.example.fmis.adapter.Search;
import com.example.fmis.adapter.SearchAdapter;
import com.example.fmis.util.DBService;

import org.angmarch.views.NiceSpinner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> data_list = new ArrayList<>();
    private DBService dbService = new DBService();
    private static List<Search> search_list = new ArrayList<>();
    int i ; //搜索的年份对应的行数
    private Button search_button; //搜索按钮
    private Button search_back;
    private boolean isSearch = false;             //判断是否发送
    private NiceSpinner niceSpinner;
    private static MainActivity m = new MainActivity();
    private static Integer year;  //获取选中的年份

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_button = findViewById(R.id.search_button1);
        search_back = findViewById(R.id.search_back);
        niceSpinner = findViewById(R.id.search_choose_year);
        search_button.setOnClickListener(this);
        search_back.setOnClickListener(this);
        initSpinner();
//        data_list = dbService.getUserData(); //获取数据库数据
//        i = 2002-2002;
//
//        System.out.println(data_list);
        //init(0); //将数据库获取到的数据传入recyclerview中
        //初始化recyclerview
//        RecyclerView recyclerView = findViewById(R.id.re_search);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
//        recyclerView.setLayoutManager(layoutManager);
//        SearchAdapter adapter = new SearchAdapter(search_list);
//        recyclerView.setAdapter(adapter);
        //如果要连网的话就不能在主线程上操作，所以要另外开启一条线程

    }

    private void initSpinner() {
        //初始化年份数据
        System.out.println("数据集条数："+m.DataList.size());
        System.out.println(m.DataList.get(0).getYear());
        for(int i = 0;i < m.DataList.size();i++){
            data_list.add(m.DataList.get(i).getYear());
        }
        niceSpinner.attachDataSource(data_list);
        niceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                year = position;
                //Toast.makeText(SearchActivity.this, "获取的年份索引"+year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNewMessage() {
        init();
        System.out.println(search_list.size());
        RecyclerView recyclerView = findViewById(R.id.re_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SearchAdapter adapter = new SearchAdapter(search_list);
        recyclerView.setAdapter(adapter);
    }


    private void init() {
        Search s1 = new Search("社会从业人数",String.valueOf(m.DataList.get(year).getX1()));
        Search s2 = new Search("在岗职工工资总额",String.valueOf(m.DataList.get(year).getX2()));
        Search s3 = new Search("社会消费品零售总额",String.valueOf(m.DataList.get(year).getX3()));
        Search s4 = new Search("城镇居民人均可支配收入",String.valueOf(m.DataList.get(year).getX4()));
        Search s5 = new Search("城镇居民人均消费性支出",String.valueOf(m.DataList.get(year).getX5()));
        Search s6 = new Search("年末总人口",String.valueOf(m.DataList.get(year).getX6()));
        Search s7 = new Search("全社会固定资产投资额",String.valueOf(m.DataList.get(year).getX7()));
        Search s8 = new Search("地区生产总值",String.valueOf(m.DataList.get(year).getX8()));
        Search s9 = new Search("第一产业产值",String.valueOf(m.DataList.get(year).getX9()));
        Search s10 = new Search("税收",String.valueOf(m.DataList.get(year).getX10()));
        Search s11 = new Search("居民消费价格指数",String.valueOf(m.DataList.get(year).getX11()));
        Search s12 = new Search("第三产业与第二产业产值比",String.valueOf(m.DataList.get(year).getX12()));
        Search s13 = new Search("居民消费水平",String.valueOf(m.DataList.get(year).getX13()));
        Search sy = new Search("财政收入",String.valueOf(m.DataList.get(year).getY()));
        search_list.add(s1);
        search_list.add(s2);
        search_list.add(s3);
        search_list.add(s4);
        search_list.add(s5);
        search_list.add(s6);
        search_list.add(s7);
        search_list.add(s8);
        search_list.add(s9);
        search_list.add(s10);
        search_list.add(s11);
        search_list.add(s12);
        search_list.add(s13);
        search_list.add(sy);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.search_back:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("查询","发了一条消息");
//                        try {
//                            dos.writeUTF("返回");
//                            System.out.println("发送完了");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
                data_list.clear();
                search_list.clear();
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
                isSearch = false;
                finish();
                break;
            case R.id.search_button1:
                search_list.clear();
                showNewMessage();
                break;
            default:
                break;
        }
    }
}