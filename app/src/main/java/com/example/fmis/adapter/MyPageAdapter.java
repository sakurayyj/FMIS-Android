package com.example.fmis.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MyPageAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> list;

    public MyPageAdapter(FragmentActivity fa, ArrayList<Fragment> list) {
        super(fa);
        this.list = list;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
