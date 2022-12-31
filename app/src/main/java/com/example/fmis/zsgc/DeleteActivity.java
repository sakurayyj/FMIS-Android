package com.example.fmis.zsgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.adapter.Data;
import com.example.fmis.util.CustomDialog;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private NiceSpinner niceSpinner;
    private static MainActivity m = new MainActivity();
    private Button delete_button,delete_back;
    private CustomDialog mDialog; //弹窗
    private List<Integer> data_list = new ArrayList<>();
    private static Integer year;  //获取选中的年份

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        niceSpinner = findViewById(R.id.delete_choose_year);
        delete_back = findViewById(R.id.delete_back);
        delete_button = findViewById(R.id.delete_button1);
        delete_button.setOnClickListener(this);
        delete_back.setOnClickListener(this);
        initSpinner();
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
                Toast.makeText(DeleteActivity.this, "获取的年份索引"+year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_back:
                Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
                finish();
                break;
            case R.id.delete_button1:
                Log.d("点击删除","开始");
                mDialog =new CustomDialog(this, "温馨提示", "确定要删除该行数据嘛?", "确定",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        Toast.makeText(DeleteActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                        Iterator<Data> iterator = m.DataList.iterator(); //除去自己
                        while (iterator.hasNext()) {
                            Data d = iterator.next();
                            if (d == m.DataList.get(year)) {
                                iterator.remove();//使用迭代器的删除
                                break;
                            }
                        }
                        data_list.clear();
                        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
                        intent.putExtra("id",1);
                        startActivity(intent);
                        finish();
                    }
                },"取消");
                mDialog.setCanotBackPress();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
        }
    }
}