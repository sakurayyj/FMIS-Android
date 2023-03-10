package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class X13Activity extends AppCompatActivity implements OnChartValueSelectedListener {

    private ScatterChart mScatterChart;
    private List<Integer> x13_list = new ArrayList<>();
    private static MainActivity m = new MainActivity();
    private Button x13_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x13);
        mScatterChart = findViewById(R.id.x13_Chart);
        x13_back = findViewById(R.id.x13_back);
        x13_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X13Activity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        initScatter();
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x13_list.add(m.DataList.get(i).getX13());
        }
    }
//    private void initData() {
//        x4_list.add(7571.0);
//        x4_list.add(9038.16);
//        x4_list.add(9905.31);
//        x4_list.add(10444.6);
//        x4_list.add(11255.7);
//        x4_list.add(12018.52);
//        x4_list.add(13966.53);
//        x4_list.add(14694.0);
//        x4_list.add(13380.47);
//        x4_list.add(15002.59);
//        x4_list.add(16884.16);
//        x4_list.add(18287.24);
//        x4_list.add(19850.66);
//        x4_list.add(22469.22);
//        x4_list.add(25316.72);
//        x4_list.add(27609.59);
//        x4_list.add(30658.49);
//        x4_list.add(34438.08);
//        x4_list.add(38053.52);
//        x4_list.add(42049.14);
//
//    }

    private void initScatter() {
        //?????????
        mScatterChart = (ScatterChart) findViewById(R.id.x13_Chart);
        mScatterChart.getDescription().setEnabled(false);
        mScatterChart.setOnChartValueSelectedListener(this);

        mScatterChart.setDrawGridBackground(false);
        mScatterChart.setTouchEnabled(true);

        // ?????????????????????
        mScatterChart.setDragEnabled(true);
        mScatterChart.setScaleEnabled(true);

        mScatterChart.setMaxVisibleValueCount(10);
        mScatterChart.setPinchZoom(true);


        Legend l = mScatterChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXOffset(5f);
        l.setDrawInside(true); // ?????????????????????

        YAxis yl = mScatterChart.getAxisLeft();
        yl.setAxisMinimum(0f);
        yl.setStartAtZero(false);

        mScatterChart.getAxisRight().setEnabled(false);

        XAxis xl = mScatterChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawGridLines(false);
        //??????y??????????????????
        mScatterChart.animateY(2000);
        //?????????????????????
//        mScatterChart.getData().setHighlightEnabled(true);

        setData();
    }

    //????????????
    private void setData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        initData();
        for (int i = 0; i < x13_list.size(); i++) {
            float val = (float) (Math.random() * 10 + 3);
            yVals1.add(new Entry(m.DataList.get(i).getYear(), Float.parseFloat(String.valueOf(x13_list.get(i)))));
        }


        //?????????????????????,?????????????????????
        ScatterDataSet set1 = new ScatterDataSet(yVals1, "??????:1");
        set1.setScatterShape(ScatterChart.ScatterShape.TRIANGLE);
        //????????????
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);

        set1.setScatterShapeSize(20f);

        List<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
        dataSets.add(set1);

        //????????????????????????????????????
        ScatterData data = new ScatterData(dataSets);
        mScatterChart.setData(data);
        mScatterChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}