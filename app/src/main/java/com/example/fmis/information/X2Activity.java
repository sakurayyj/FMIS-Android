package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class X2Activity extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private List<Double> x2_list = new ArrayList<>();
    private static MainActivity m = new MainActivity();
    private Button x2_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x2);
        x2_back = findViewById(R.id.x2_back);
        x2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X2Activity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });
        initData();
        initBarChart();
    }
    private void initBarChart() {
        barChart = findViewById(R.id.x2_Chart);
        barChart.getDescription().setEnabled(false); // 不显示描述
        barChart.getLegend().setEnabled(false); // 不显示图例
        barChart.setExtraOffsets(30, 30, 30, 30);  // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setAxis();  // 设置坐标轴
        setData(); // 设置数据
        barChart.animateY(2000);
    }

    private void setData() {
        List<BarEntry> entryList = new ArrayList<>();
        for(int i = 0;i < x2_list.size(); i++){
            float d = x2_list.get(i).floatValue();
            entryList.add(new BarEntry(i,d));
        }

        BarDataSet barDataSet = new BarDataSet(entryList, "");
        barDataSet.setColors(Color.rgb(		147,112,219));
        barDataSet.setValueTextColor(Color.rgb(	138,43,226));
        barDataSet.setValueTextSize(15f);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return String.valueOf(v);
            }
        });
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.4f); // 设置柱子的宽度
        barChart.setData(barData);
        //barChart.getXAxis().setLabelRotationAngle(-45); //x轴文字倾斜
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x2_list.add(m.DataList.get(i).getX2());
        }
    }
//    private void initData() {
//        x2_list.add(181.54);
//        x2_list.add(214.63);
//        x2_list.add(239.56);
//        x2_list.add(261.58);
//        x2_list.add(283.14);
//        x2_list.add(308.58);
//        x2_list.add(348.09);
//        x2_list.add(387.81);
//        x2_list.add(453.49);
//        x2_list.add(533.55);
//        x2_list.add(598.33);
//        x2_list.add(665.32);
//        x2_list.add(738.97);
//        x2_list.add(877.07);
//        x2_list.add(1005.37);
//        x2_list.add(1118.03);
//        x2_list.add(1304.48);
//        x2_list.add(1700.87);
//        x2_list.add(1969.51);
//        x2_list.add(2110.78);
//    }

    /**
     * 因为此处的柱状图为水平柱状图，所以x轴变y轴，y轴变x轴
     */
    private void setAxis() {
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(15f);
        xAxis.setLabelCount(x2_list.size());
        xAxis.setGranularity(1f); // 防止放大图后，标签错乱
        //final String label[] = {"西瓜", "蓝莓", "草莓"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if ((int) v < x2_list.size()) {
                    return String.valueOf(m.DataList.get((int)v).getYear());
                } else {
                    return "";
                }
            }
        });

        YAxis yAxis_right = barChart.getAxisRight();
//        yAxis_right.setAxisMinimum(0f);
//        yAxis_right.setAxisMaximum(100f);
        yAxis_right.setStartAtZero(false);
        yAxis_right.setTextSize(15f);
        yAxis_right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return String.valueOf(v);
            }
        });

        // 不显示最顶部的轴
        YAxis yAxis_left = barChart.getAxisLeft();
//        yAxis_left.setAxisMinimum(0f);
//        yAxis_left.setAxisMaximum(100f);
        yAxis_left.setStartAtZero(false);
        yAxis_left.setEnabled(false);
    }
}