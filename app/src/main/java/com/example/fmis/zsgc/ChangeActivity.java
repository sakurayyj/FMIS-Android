package com.example.fmis.zsgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.adapter.Data;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back,queren;
    private NiceSpinner yearSpinner,dataSpinner;
    private TextView beforeChange;
    private EditText afterChange;
    private static MainActivity m = new MainActivity();
    private List<Integer> year_list = new ArrayList<>();
    private List<String> data_list = new ArrayList<>();
    private static int year_id;
    private static int data_id;
    private static int x1,x6,x13,year;
    private static Double x2,x3,x4,x5,x7,x8,x9,x10,x11,x12,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        back = findViewById(R.id.change_back);
        queren = findViewById(R.id.change_queren);
        yearSpinner = findViewById(R.id.change_choose_year);
        dataSpinner = findViewById(R.id.change_choose_content);
        beforeChange = findViewById(R.id.before_change);
        afterChange = findViewById(R.id.after_change);
        back.setOnClickListener(this);
        queren.setOnClickListener(this);
        initYearSpinner(); //初始化年份的spinner
        initDataSpinner(); //初始化数据标题的spinner
    }

    private void initDataSpinner() {
        data_list.add("社会从业人数");
        data_list.add("在岗职工工资总额");
        data_list.add("社会消费品零售总额");
        data_list.add("城镇居民人均可支配收入");
        data_list.add("城镇居民人均消费性支出");
        data_list.add("年末总人口");
        data_list.add("全社会固定资产投资额");
        data_list.add("地区生产总值");
        data_list.add("第一产业产值");
        data_list.add("税收");
        data_list.add("居民消费价格指数");
        data_list.add("第三产业与第二产业产值比");
        data_list.add("居民消费水平");
        data_list.add("财政收入");
        dataSpinner.attachDataSource(data_list);
        dataSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data_id = position;
                System.out.println("选取的修改数据是"+position);
                x1 = m.DataList.get(year_id).getX1();
                x2 = m.DataList.get(year_id).getX2();
                x3 = m.DataList.get(year_id).getX3();
                x4 = m.DataList.get(year_id).getX4();
                x5 = m.DataList.get(year_id).getX5();
                x6 = m.DataList.get(year_id).getX6();
                x7 = m.DataList.get(year_id).getX7();
                x8 = m.DataList.get(year_id).getX8();
                x9 = m.DataList.get(year_id).getX9();
                x10 = m.DataList.get(year_id).getX10();
                x11 = m.DataList.get(year_id).getX11();
                x12 = m.DataList.get(year_id).getX12();
                x13 = m.DataList.get(year_id).getX13();
                y = m.DataList.get(year_id).getY();
                year = m.DataList.get(year_id).getYear();
                switch (position){
                    case 0:
                        beforeChange.setText(String.valueOf(x1));
                    case 1:
                        beforeChange.setText(String.valueOf(x2));
                    case 2:
                        beforeChange.setText(String.valueOf(x3));
                    case 3:
                        beforeChange.setText(String.valueOf(x4));
                    case 4:
                        beforeChange.setText(String.valueOf(x5));
                    case 5:
                        beforeChange.setText(String.valueOf(x6));
                    case 6:
                        beforeChange.setText(String.valueOf(x7));
                    case 7:
                        beforeChange.setText(String.valueOf(x8));
                    case 8:
                        beforeChange.setText(String.valueOf(x9));
                    case 9:
                        beforeChange.setText(String.valueOf(x10));
                    case 10:
                        beforeChange.setText(String.valueOf(x11));
                    case 11:
                        beforeChange.setText(String.valueOf(x12));
                    case 12:
                        beforeChange.setText(String.valueOf(x13));
                    case 13:
                        beforeChange.setText(String.valueOf(y));
                    default:
                        break;
                }

                //Toast.makeText(SearchActivity.this, "获取的年份索引"+year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initYearSpinner() {
        //初始化年份数据
        System.out.println("数据集条数：" + m.DataList.size());
        System.out.println(m.DataList.get(0).getYear());
        for (int i = 0; i < m.DataList.size(); i++) {
            year_list.add(m.DataList.get(i).getYear());
        }
        yearSpinner.attachDataSource(year_list);
        yearSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                year_id = position;
                //Toast.makeText(SearchActivity.this, "获取的年份索引"+year, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_back:
                Intent intent = new Intent(ChangeActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
                finish();
                break;
            case R.id.change_queren:
                if(!afterChange.getText().equals("")){
                    m.DataList.remove(year_id);
                    switch (data_id){
                        case 0:
                            x1 = Integer.parseInt(String.valueOf(afterChange.getText()));
                            break;
                        case 1:
                            x2 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 2:
                            x3 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 3:
                            x4 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 4:
                            x5 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 5:
                            x6 = Integer.parseInt(String.valueOf(afterChange.getText()));
                            break;
                        case 6:
                            x7 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 7:
                            x8 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 8:
                            x9 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 9:
                            x10 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 10:
                            x11 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 11:
                            x12 = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        case 12:
                            x13 = Integer.parseInt(String.valueOf(afterChange.getText()));
                            break;
                        case 13:
                            y = Double.parseDouble(String.valueOf(afterChange.getText()));
                            break;
                        default:
                            break;
                    }
                    Data d = new Data(x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12,x13,y,year);
                    m.DataList.add(d);
                }
                year_list.clear();
                data_list.clear();
                beforeChange.setText("");
                Toast.makeText(ChangeActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(ChangeActivity.this, MainActivity.class);
                intent1.putExtra("id",1);
                startActivity(intent1);
                finish();
                break;
        }
    }
}