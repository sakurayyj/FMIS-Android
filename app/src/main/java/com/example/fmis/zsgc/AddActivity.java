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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.adapter.Data;
import com.example.fmis.adapter.SearchAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.fmis.activity.MainActivity.dos;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    Button back;  //返回
    Button confirm; //确认添加
    EditText x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,y,year;
    private static MainActivity m = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        back = findViewById(R.id.add_back);
        confirm = findViewById(R.id.add_queren);
        x1 = findViewById(R.id.e_x1);
        x2 = findViewById(R.id.e_x2);
        x3 = findViewById(R.id.e_x3);
        x4 = findViewById(R.id.e_x4);
        x5 = findViewById(R.id.e_x5);
        x6 = findViewById(R.id.e_x6);
        x7 = findViewById(R.id.e_x7);
        x8 = findViewById(R.id.e_x8);
        x9 = findViewById(R.id.e_x9);
        x10 = findViewById(R.id.e_x10);
        x11 = findViewById(R.id.e_x11);
        x12 = findViewById(R.id.e_x12);
        x13 = findViewById(R.id.e_x13);
        y = findViewById(R.id.e_y);
        year = findViewById(R.id.e_year);
        back.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_back:
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
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
                break;
            case R.id.add_queren:
                addData();
                System.out.println("获取键盘输入"+x1.getText());
                System.out.println("数据添加更新后条数："+m.DataList.size());
                Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(AddActivity.this, MainActivity.class);
                intent1.putExtra("id",1);
                startActivity(intent1);
                break;
        }
    }

    private void addData() {
        Data d = new Data(Integer.parseInt(String.valueOf(x1.getText())),
                Double.parseDouble(String.valueOf(x2.getText())),
                Double.parseDouble(String.valueOf(x3.getText())),
                Double.parseDouble(String.valueOf(x4.getText())),
                Double.parseDouble(String.valueOf(x5.getText())),
                Integer.parseInt(String.valueOf(x6.getText())),
                Double.parseDouble(String.valueOf(x7.getText())),
                Double.parseDouble(String.valueOf(x8.getText())),
                Double.parseDouble(String.valueOf(x9.getText())),
                Double.parseDouble(String.valueOf(x10.getText())),
                Double.parseDouble(String.valueOf(x11.getText())),
                Double.parseDouble(String.valueOf(x12.getText())),
                Integer.parseInt(String.valueOf(x13.getText())),
                Double.parseDouble(String.valueOf(y.getText())),
                Integer.parseInt(String.valueOf(year.getText())));
        m.DataList.add(d);
    }
}
