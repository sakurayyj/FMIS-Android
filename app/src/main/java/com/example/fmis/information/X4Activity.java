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

public class X4Activity extends AppCompatActivity implements OnChartValueSelectedListener {
    private ScatterChart mScatterChart;
    private List<Double> x4_list = new ArrayList<>();
    private static MainActivity m = new MainActivity();
    private Button x4_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x4);
        mScatterChart = findViewById(R.id.x4_Chart);
        x4_back = findViewById(R.id.x4_back);
        x4_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(X4Activity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        initScatter();
    }

    private void initData() {
        System.out.println("x1:"+m.DataList.size());
        for(int i = 0;i < m.DataList.size(); i++){
            x4_list.add(m.DataList.get(i).getX4());
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
        //散点图
        mScatterChart = (ScatterChart) findViewById(R.id.x4_Chart);
        mScatterChart.getDescription().setEnabled(false);
        mScatterChart.setOnChartValueSelectedListener(this);

        mScatterChart.setDrawGridBackground(false);
        mScatterChart.setTouchEnabled(true);

        // 支持缩放和拖动
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
        l.setDrawInside(true); // 设置图例在图中

        YAxis yl = mScatterChart.getAxisLeft();
        yl.setAxisMinimum(0f);
        yl.setStartAtZero(false);

        mScatterChart.getAxisRight().setEnabled(false);

        XAxis xl = mScatterChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawGridLines(false);
        //设置y轴升起的动画
        mScatterChart.animateY(2000);
        //设置点击的高亮
//        mScatterChart.getData().setHighlightEnabled(true);

        setData();
    }

    //设置数据
    private void setData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        initData();
        for (int i = 0; i < x4_list.size(); i++) {
            float val = (float) (Math.random() * 10 + 3);
            yVals1.add(new Entry(m.DataList.get(i).getYear(), Float.parseFloat(String.valueOf(x4_list.get(i)))));
        }


        //创建一个数据集,并给它一个类型
        ScatterDataSet set1 = new ScatterDataSet(yVals1, "单位:1");
        set1.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        //设置颜色
        set1.setColor(ColorTemplate.COLORFUL_COLORS[1]);

        set1.setScatterShapeSize(20f);

        List<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
        dataSets.add(set1);

        //创建一个数据集的数据对象
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
