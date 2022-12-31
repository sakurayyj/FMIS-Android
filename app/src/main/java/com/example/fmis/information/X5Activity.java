package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class X5Activity extends AppCompatActivity {

    private LineChart lineChart;
    private List<Double> x5_list = new ArrayList<>();
    private static MainActivity m = new MainActivity();
    private Button x5_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5);
        x5_back = findViewById(R.id.x5_back);
        x5_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X5Activity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        initData();
        initLineChart();
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x5_list.add(m.DataList.get(i).getX5());
        }
    }

//    private void initData() {
//        x5_list.add(6212.7);
//        x5_list.add(7601.73);
//        x5_list.add(8092.82);
//        x5_list.add(8767.98);
//        x5_list.add(9422.33);
//        x5_list.add(9751.44);
//        x5_list.add(11349.47);
//        x5_list.add(11467.35);
//        x5_list.add(10671.78);
//        x5_list.add(11570.58);
//        x5_list.add(13120.83);
//        x5_list.add(14468.24);
//        x5_list.add(15444.93);
//        x5_list.add(18951.32);
//        x5_list.add(20835.95);
//        x5_list.add(22820.89);
//        x5_list.add(25011.61);
//        x5_list.add(28209.74);
//        x5_list.add(30490.44);
//        x5_list.add(33156.83);
//    }

    private void initLineChart() {
        lineChart = findViewById(R.id.x5_Chart);
        lineChart.getDescription().setEnabled(false); // 不显示描述
        lineChart.getLegend().setEnabled(false);  // 不显示图例
        setAxis(); // 设置坐标轴
        setData(); // 设置数据
        lineChart.animateXY(2000,2000);
    }

    private void setData() {
        List<ILineDataSet> sets = new ArrayList<>();

        List<Entry> entries1 = new ArrayList<>();
        for(int i = 0;i < x5_list.size(); i++){
            float d = x5_list.get(i).floatValue();
            entries1.add(new Entry(m.DataList.get(i).getYear(),d));
        }
        LineDataSet dataSet1 = new LineDataSet(entries1, "");
        dataSet1.setValueTextSize(15f);
        dataSet1.setColor(Color.parseColor("#008B45"));
        dataSet1.setCircleColor(Color.parseColor("#00FF7F"));
        dataSet1.setDrawValues(false); // 不显示值
        sets.add(dataSet1);

//        entries1.add(new Entry(0, 14));
//        entries1.add(new Entry(1, 10));
//        entries1.add(new Entry(2, 12));
//        entries1.add(new Entry(3, 13));
//        entries1.add(new Entry(4, 16));
//        entries1.add(new Entry(5, 16));
//        LineDataSet dataSet1 = new LineDataSet(entries1, "");
//        dataSet1.setValueTextSize(15f);
//        dataSet1.setDrawValues(false); // 不显示值
//        sets.add(dataSet1);
//
//        List<Entry> entries2 = new ArrayList<>();
//        entries2.add(new Entry(0, 28));
//        entries2.add(new Entry(1, 22));
//        entries2.add(new Entry(2, 20));
//        entries2.add(new Entry(3, 22));
//        entries2.add(new Entry(4, 25));
//        entries2.add(new Entry(5, 29));
//        LineDataSet dataSet2 = new LineDataSet(entries2, "");
//        dataSet2.setValueTextSize(15f);
//        dataSet2.setColor(Color.parseColor("#FDC06A"));
//        dataSet2.setCircleColor(Color.parseColor("#FDC06A"));
//        dataSet2.setDrawValues(false); // 不显示值
//        sets.add(dataSet2);

        LineData lineData = new LineData(sets);
        lineChart.setData(lineData);
        lineChart.setExtraOffsets(20, 20, 20, 20);
        lineChart.setScaleEnabled(false); // 设置不能缩放
        lineChart.setTouchEnabled(false); // 设置不能触摸
    }

    private void setAxis() {
        // x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);

        // 右y轴
        YAxis yAxis_right = lineChart.getAxisRight();
//        yAxis_right.setAxisMinimum(0f);
//        yAxis_right.setAxisMaximum(30f);
        yAxis_right.setTextSize(12f);
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        // 左y轴
        YAxis yAxis_left = lineChart.getAxisLeft();
//        yAxis_left.setAxisMinimum(0f);
//        yAxis_left.setAxisMaximum(30f);
        yAxis_left.setStartAtZero(false);
        yAxis_left.setTextSize(12f);
    }

}