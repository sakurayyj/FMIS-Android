package com.example.fmis.shouye;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fmis.R;
import com.example.fmis.information.DuoBianLiang1Activity;
import com.example.fmis.information.DuoBianLiang2Activity;
import com.example.fmis.information.DuoBianLiang3Activity;
import com.example.fmis.information.X1Activity;
import com.example.fmis.information.XiangGuanXingActivity;

public class PretrearmentFragment extends Fragment implements View.OnClickListener {

    private Activity activity;
    private FragmentManager fragmentManager;
    private LinearLayout relitu;
    private LinearLayout dbl1;
    private LinearLayout dbl2;
    private LinearLayout dbl3;

    public PretrearmentFragment(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_pretrearment, container, false);
        relitu = inflate.findViewById(R.id.relitu_layout);
        dbl1 = inflate.findViewById(R.id.duobianliang1_layout);
        dbl2 = inflate.findViewById(R.id.duobianliang2_layout);
        dbl3 = inflate.findViewById(R.id.duobianliang3_layout);
        relitu.setOnClickListener(this);
        dbl1.setOnClickListener(this);
        dbl2.setOnClickListener(this);
        dbl3.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relitu_layout:
                Intent intent = new Intent(activity, XiangGuanXingActivity.class);
                startActivity(intent);
                break;
            case R.id.duobianliang1_layout:
                Intent intent1 = new Intent(activity, DuoBianLiang1Activity.class);
                startActivity(intent1);
                break;
            case R.id.duobianliang2_layout:
                Intent intent2 = new Intent(activity, DuoBianLiang2Activity.class);
                startActivity(intent2);
                break;
            case R.id.duobianliang3_layout:
                Intent intent3 = new Intent(activity, DuoBianLiang3Activity.class);
                startActivity(intent3);
                break;
        }
    }
}