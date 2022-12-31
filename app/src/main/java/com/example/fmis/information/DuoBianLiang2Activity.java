package com.example.fmis.information;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fmis.R;
import com.example.fmis.activity.MainActivity;
import com.example.fmis.util.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

public class DuoBianLiang2Activity extends AppCompatActivity {

    private Button back;
    private LargeImageView mLargeImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_bian_liang2);
        mLargeImageView = (LargeImageView) findViewById(R.id.dbl2_imageview);
        try
        {
            InputStream inputStream = getAssets().open("examples4.jpg");
            mLargeImageView.setInputStream(inputStream,R.drawable.examples4,1);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        back = findViewById(R.id.dbl2_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DuoBianLiang2Activity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
            }
        });

    }
}
