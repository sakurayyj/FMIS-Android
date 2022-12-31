package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class X8Activity extends AppCompatActivity {

    private List<Double> x8_list = new ArrayList<>();
    private List<String> title_list = new ArrayList<>();
    private PieChart pieChart;
    private Button x8_back;
    private static MainActivity m = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x8);
        x8_back = findViewById(R.id.x8_back);
        x8_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X8Activity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });
        initData();
        initPieChart();
//        init();
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(runnable).start();
//                //用Uri显示图片
//                //imageView.setImageResource(R.drawable.testimg);
//            }
//        });
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x8_list.add(m.DataList.get(i).getX8());
        }
    }

    //    private void initData() {
//        x1_list.add(3831732);
//        x1_list.add(3913824);
//        x1_list.add(3928907);
//        x1_list.add(4282130);
//        x1_list.add(4453911);
//        x1_list.add(4548852);
//        x1_list.add(4962579);
//        x1_list.add(5029338);
//        x1_list.add(5070216);
//        x1_list.add(5210706);
//        x1_list.add(5407087);
//        x1_list.add(5744550);
//        x1_list.add(5994973);
//        x1_list.add(6236312);
//        x1_list.add(6529045);
//        x1_list.add(6791495);
//        x1_list.add(7110695);
//        x1_list.add(7431755);
//        x1_list.add(7512997);
//        x1_list.add(7599295);
//    }
    private void initPieChart() {
        pieChart = findViewById(R.id.x8_Chart);
        pieChart.getDescription().setEnabled(true); // 不显示描述
        pieChart.setDrawHoleEnabled(true); // 不显示饼图中间的空洞
        pieChart.setRotationEnabled(false); // 不允许饼图旋转
        pieChart.setDrawEntryLabels(false); // 不在饼图中显示标签
        pieChart.setDrawCenterText(true);//显示中间文字
        pieChart.setCenterText("地区生产总值");
        pieChart.setCenterTextSize(20);//中间文字大小
        pieChart.setCenterTextColor(Color.parseColor("#696969"));//中间文字颜色
        pieChart.setExtraOffsets(20, 20, 20, 20); // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setLegend(); // 设置图例
        setData(); // 为饼图设置数据
        pieChart.animateXY(3000,3000);
    }

    private void setData() {
        List<PieEntry> pieEntries = new ArrayList<>();
        // 准备饼图中要显示的数据
        for(int i = 0;i < x8_list.size(); i++){
            float d = x8_list.get(i).floatValue();
            pieEntries.add(new PieEntry(d,m.DataList.get(i).getYear()));
        }
        // 把准备好的数据统一进行格式设置
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        // 设置饼图各部分的颜色
        pieDataSet.setColors(Color.rgb(122,114,129),
                Color.rgb(150,84,84),
                Color.rgb(107,81,82),
                Color.rgb(238,229,248),
                Color.rgb(162,126,126),
                Color.rgb(234,208,209));
        // 设置饼图中数据显示的格式
//        pieDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                // 此处的value就是PieEntry（）中第一个参数的value
//                    return String.valueOf(x1_list.get((int) value));
//            }
//        });
        pieDataSet.setValueTextSize(0f);
        pieDataSet.setSliceSpace(0f); // 设置扇区中的间隔
        // 设置饼图显示的线
//        pieDataSet.setValueLineColor(Color.rgb(	105,105,105));
//        pieDataSet.setValueLinePart1OffsetPercentage(80); // 第一条线离圆心的百分比
//        pieDataSet.setValueLinePart1Length(0.4f); // 第一条线长度
//        pieDataSet.setValueLinePart2Length(0.3f); // 第二条线长度
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE); // 设置值显示的位置

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData); // 为饼图设置数据
        //设置点击事件
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if(e == null) {
                    return;
                }
                System.out.println(e.getY());
                for(int i = 0;i < pieEntries.size(); i++){
                    if(pieEntries.get(i).getValue() == e.getY()){
                        Toast.makeText(X8Activity.this, pieEntries.get(i).getData() + "年:" + (int)pieEntries.get(i).getValue()+"元", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void setLegend() {
        Legend legend = pieChart.getLegend();
        legend.setFormSize(15f); // 图例的图形大小
        legend.setTextSize(15f); // 图例的文字大小
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); // 显示的位置水平居中
        legend.setDrawInside(true); // 设置图例在图中
        legend.setYOffset(5); // 设置图例在垂直方向的偏移量
    }


}