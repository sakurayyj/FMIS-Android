package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.zsgc.AddActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class X1Activity extends AppCompatActivity {

    private ImageView imageView;


    private List<Integer> x1_list = new ArrayList<>();
    private List<String> title_list = new ArrayList<>();
    private PieChart pieChart;
    private Button x1_back;
    private static MainActivity m = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x1);
        x1_back = findViewById(R.id.x1_back);
        x1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X1Activity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });
        initData();
        initPieChart();

    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x1_list.add(m.DataList.get(i).getX1());
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
        pieChart = findViewById(R.id.x1_Chart);
        pieChart.getDescription().setEnabled(true); // ???????????????
        pieChart.setDrawHoleEnabled(true); // ??????????????????????????????
        pieChart.setRotationEnabled(false); // ?????????????????????
        pieChart.setDrawEntryLabels(false); // ???????????????????????????
        pieChart.setDrawCenterText(true);//??????????????????
        pieChart.setCenterText("??????????????????");
        pieChart.setCenterTextSize(20);//??????????????????
        pieChart.setCenterTextColor(Color.parseColor("#696969"));//??????????????????
        pieChart.setExtraOffsets(20, 20, 20, 20); // ????????????????????????????????????????????? ???????????????????????????
        setLegend(); // ????????????
        setData(); // ?????????????????????
        pieChart.animateXY(2000,2000);
    }

    private void setData() {
        List<PieEntry> pieEntries = new ArrayList<>();
        // ?????????????????????????????????
        for(int i = 0;i < x1_list.size(); i++){
            float d = x1_list.get(i).floatValue();
            pieEntries.add(new PieEntry(x1_list.get(i),m.DataList.get(i).getYear()));
        }
        // ?????????????????????????????????????????????
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        // ??????????????????????????????
        pieDataSet.setColors(Color.rgb(181,196,177),
                Color.rgb(193,203,215),
                Color.rgb(175,176,178),
                Color.rgb(199,184,161),
                Color.rgb(183,176,166),
                Color.rgb(162,153,136));
        // ????????????????????????????????????
//        pieDataSet.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                // ?????????value??????PieEntry???????????????????????????value
//                    return String.valueOf(x1_list.get((int) value));
//            }
//        });
        pieDataSet.setValueTextSize(0f);
        pieDataSet.setSliceSpace(0f); // ????????????????????????
        // ????????????????????????
//        pieDataSet.setValueLineColor(Color.rgb(	105,105,105));
//        pieDataSet.setValueLinePart1OffsetPercentage(80); // ?????????????????????????????????
//        pieDataSet.setValueLinePart1Length(0.4f); // ??????????????????
//        pieDataSet.setValueLinePart2Length(0.3f); // ??????????????????
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE); // ????????????????????????

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData); // ?????????????????????
        //??????????????????
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if(e == null) {
                    return;
                }
                System.out.println(e.getY());
                for(int i = 0;i < pieEntries.size(); i++){
                    if(pieEntries.get(i).getValue() == e.getY()){
                        Toast.makeText(X1Activity.this, pieEntries.get(i).getData() + "???:" + (int)pieEntries.get(i).getValue()+"???", Toast.LENGTH_SHORT).show();
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
        legend.setFormSize(15f); // ?????????????????????
        legend.setTextSize(15f); // ?????????????????????
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); // ???????????????????????????
        legend.setDrawInside(true); // ?????????????????????
        legend.setYOffset(5); // ???????????????????????????????????????
    }



}


