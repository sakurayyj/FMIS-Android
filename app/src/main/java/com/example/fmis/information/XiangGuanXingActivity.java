package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.util.DoubleScaleImageView;
import com.example.fmis.util.LargeImageView;
import com.example.fmis.util.ScaleAttrsImageView;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.io.InputStream;

public class XiangGuanXingActivity extends AppCompatActivity{

    private Button back;
    private LargeImageView mLargeImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_guan_xing);
        mLargeImageView = (LargeImageView) findViewById(R.id.xgx_imageview);
        try
        {
            InputStream inputStream = getAssets().open("examples2.jpg");
            mLargeImageView.setInputStream(inputStream,R.drawable.examples2,0);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        back = findViewById(R.id.xgx_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XiangGuanXingActivity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                finish();
            }
        });

    }

}