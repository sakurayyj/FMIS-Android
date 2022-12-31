package com.example.fmis.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fmis.R;
import com.example.fmis.util.LargeImageView;

import java.io.IOException;
import java.io.InputStream;

public class YuceFragment extends Fragment {

    private Activity activity;
    private LargeImageView mLargeImageView;

    public YuceFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_yuce, container, false);
        mLargeImageView = (LargeImageView) inflate.findViewById(R.id.yuce1_imageview);
        try
        {
            InputStream inputStream = activity.getAssets().open("examples6.jpg");
            mLargeImageView.setInputStream(inputStream,R.drawable.examples6,0);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return inflate;
    }
}