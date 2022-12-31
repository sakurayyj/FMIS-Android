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

public class JuleiFragment extends Fragment {
    private Activity activity;
    private LargeImageView mLargeImageView;

    public JuleiFragment(Activity activity) {
        this.activity = activity;
    }
//    // TODO: Rename and change types and number of parameters
//    public static JuleiFragment newInstance(String param1, String param2) {
//        JuleiFragment fragment = new JuleiFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_julei, container, false);
        mLargeImageView = (LargeImageView) inflate.findViewById(R.id.kmeans_imageview);
        try
        {
            InputStream inputStream = activity.getAssets().open("examples7.jpg");
            mLargeImageView.setInputStream(inputStream,R.drawable.examples7,0);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return inflate;
    }
}