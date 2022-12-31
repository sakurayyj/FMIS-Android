package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class X6Activity extends AppCompatActivity {
    private RadarChart radar;
    private List<Integer> x6_list = new ArrayList<>();
    private static MainActivity m = new MainActivity();
    private Button x6_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x6);
        radar = (RadarChart) findViewById(R.id.x6_Chart);
        x6_back = findViewById(R.id.x6_back);
        x6_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X6Activity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        initData();
        initRadar();
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x6_list.add(m.DataList.get(i).getX6());
        }
    }
//    private void initData() {
//        x6_list.add(6370241);
//        x6_list.add(6467115);
//        x6_list.add(6560508);
//        x6_list.add(6664862);
//        x6_list.add(6741400);
//        x6_list.add(6850024);
//        x6_list.add(7006896);
//        x6_list.add(7125979);
//        x6_list.add(7206229);
//        x6_list.add(7251888);
//        x6_list.add(7376720);
//        x6_list.add(7505322);
//        x6_list.add(7607220);
//        x6_list.add(7734787);
//        x6_list.add(7841695);
//        x6_list.add(7946154);
//        x6_list.add(8061370);
//        x6_list.add(8145797);
//        x6_list.add(8222969);
//        x6_list.add(8323096);
//    }

    private void initRadar() {
        List<RadarEntry> list = new ArrayList<>();
        for(int i = 0;i < x6_list.size();i++){
            list.add(new RadarEntry(x6_list.get(i)/100000));
        }

        RadarDataSet radarDataSet=new RadarDataSet(list,"单位：十万");
        radarDataSet.setColor(Color.rgb(255 ,165,0));
//        RadarDataSet radarDataSet1=new RadarDataSet(list2,"女性");
//        radarDataSet1.setColor(Color.BLUE);
        RadarData radarData=new RadarData(radarDataSet);
//        radarData.addDataSet(radarDataSet1);
        radar.setData(radarData);

        //Y轴最小值不设置会导致数据中最小值默认成为Y轴最小值
        //radar.getYAxis().setAxisMinimum(0);

        //大字的颜色（中心点和各顶点的连线）
        radar.setWebColor(Color.rgb(135 ,206,250));
        //所有五边形的颜色
        radar.setWebColorInner(Color.rgb(135 ,206,250));
        //整个控件的背景颜色
        radar.setBackgroundColor(Color.WHITE);

        radar.animateXY(2000,2000);
        XAxis xAxis=radar.getXAxis();
        xAxis.setTextColor(Color.rgb(70 ,130,180));//X轴字体颜色
        xAxis.setTextSize(10);     //X轴字体大小
        //自定义X轴坐标描述（也就是五个顶点上的文字,默认是0、1、2、3、4）
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return String.valueOf((int)v+2002);
            }
        });


        //是否绘制雷达框上对每个点的数据的标注    和Y轴坐标点一般不同时存在 否则显得很挤  默认为true
        radarDataSet.setDrawValues(false);
        radarDataSet.setValueTextSize(5);  //数据值得字体大小（这里只是写在这）
        radarDataSet.setValueTextColor(Color.rgb(70 ,130,180));//数据值得字体颜色（这里只是写在这）

        YAxis yAxis=radar.getYAxis();
        //是否绘制Y轴坐标点  和雷达框数据一般不同时存在 否则显得很挤 默认为true
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.rgb(135 ,206,250));//Y轴坐标数据的颜色
//        yAxis.setAxisMaximum(80);   //Y轴最大数值
//        yAxis.setAxisMinimum(0);   //Y轴最小数值
        //Y轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        yAxis.setLabelCount(x6_list.size(),false);


        //对于右下角一串字母的操作
        radar.getDescription().setEnabled(false);                  //是否显示右下角描述
        radar.getDescription().setText("这是修改那串英文的方法");    //修改右下角字母的显示
        radar.getDescription().setTextSize(20);                    //字体大小
        radar.getDescription().setTextColor(Color.CYAN);             //字体颜色

        //图例
        Legend legend=radar.getLegend();
        legend.setEnabled(true);    //是否显示图例
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);    //图例的位置

    }

}