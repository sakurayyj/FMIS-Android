package com.example.fmis.information;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.github.mikephil.charting.charts.BarChart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fmis.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class X3Activity extends AppCompatActivity {
    private BarChart barChart;
    private List<Double> x3_list = new ArrayList<>();
    private static MainActivity m = new MainActivity();
    private Button x3_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x3);
        x3_back = findViewById(R.id.x3_back);
        x3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X3Activity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });
        initData();
        initBarChart();
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x3_list.add(m.DataList.get(i).getX3());
        }
    }
    private void initBarChart() {
        barChart = findViewById(R.id.x3_Chart);
        barChart.getDescription().setEnabled(false); // 不显示描述
        barChart.setExtraOffsets(20, 20, 20, 20); // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setAxis(); // 设置坐标轴
        setLegend(); // 设置图例
        setData();  // 设置数据
        barChart.animateY(2000);
    }

    private void setData() {
        List<IBarDataSet> sets = new ArrayList<>();
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        List<BarEntry> barEntries1 = new ArrayList<>();
        for(int i = 0;i < x3_list.size(); i++){
            float d = x3_list.get(i).floatValue();
            barEntries1.add(new BarEntry(i,d));
        }
        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setValueTextColor(Color.rgb(0,134,139)); // 值的颜色
        barDataSet1.setValueTextSize(10f); // 值的大小
        barDataSet1.setColor(Color.parseColor("#98F5FF")); // 柱子的颜色
        barDataSet1.setLabel("单位:1"); // 设置标签之后，图例的内容默认会以设置的标签显示
        // 设置柱子上数据显示的格式
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                // 此处的value默认保存一位小数
                return String.valueOf(value);
            }
        });

        sets.add(barDataSet1);
        BarData barData = new BarData(sets);
        barData.setBarWidth(0.4f); // 设置柱子的宽度
        barChart.setData(barData);
        barChart.getXAxis().setLabelRotationAngle(-45); //x轴文字倾斜
    }

//    private void initData() {
//        x3_list.add(448.19);
//        x3_list.add(549.97);
//        x3_list.add(686.44);
//        x3_list.add(802.59);
//        x3_list.add(904.57);
//        x3_list.add(1000.69);
//        x3_list.add(1121.13);
//        x3_list.add(1248.29);
//        x3_list.add(1370.68);
//        x3_list.add(1494.27);
//        x3_list.add(1677.77);
//        x3_list.add(1905.84);
//        x3_list.add(2199.14);
//        x3_list.add(2624.24);
//        x3_list.add(3187.39);
//        x3_list.add(3615.77);
//        x3_list.add(4476.38);
//        x3_list.add(5243.03);
//        x3_list.add(5977.27);
//        x3_list.add(6882.85);
//    }


    private void setLegend() {
        Legend legend = barChart.getLegend();
        legend.setFormSize(12f); // 图例的图形大小
        legend.setTextSize(15f); // 图例的文字大小
        legend.setDrawInside(true); // 设置图例在图中
        legend.setOrientation(Legend.LegendOrientation.VERTICAL); // 图例的方向为垂直
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //显示位置，水平右对齐
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // 显示位置，垂直上对齐
        // 设置水平与垂直方向的偏移量
        legend.setYOffset(65f);
        legend.setXOffset(30f);
    }

    private void setAxis() {
        // 设置x轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false); // 将此设置为true，绘制该轴的网格线。
        xAxis.setLabelCount(x3_list.size());  // 设置x轴上的标签个数
        xAxis.setTextSize(10f); // x轴上标签的大小
        // 设置x轴显示的值的格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ((int) value < x3_list.size()) {
                    return String.valueOf(m.DataList.get((int)value).getYear());
                } else {
                    return "";
                }
            }
        });
        xAxis.setYOffset(0); // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        YAxis yAxis_right = barChart.getAxisRight();
//        yAxis_right.setAxisMaximum(1200f);  // 设置y轴的最大值
//        yAxis_right.setAxisMinimum(0f);  // 设置y轴的最小值
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        YAxis yAxis_left = barChart.getAxisLeft();
//        yAxis_left.setAxisMaximum(1200f);
//        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setStartAtZero(false);
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setXOffset(0);
        //yAxis_left.setTextSize(15f); // 设置y轴的标签大小
    }
}