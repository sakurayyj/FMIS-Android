package com.example.fmis.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.fmis.R;
import com.example.fmis.adapter.Apriori;
import com.example.fmis.adapter.AprioriAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class GuanlianguizeFragment extends Fragment {
    private Activity activity;
    private List<Apriori> list = new ArrayList<>();

    public GuanlianguizeFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_guanlianguize, container, false);
        init2();
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerview_apriori);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        AprioriAdapter adapter = new AprioriAdapter(list);
        recyclerView.setAdapter(adapter);
        return inflate;
    }

    private void init2() {
        list.add(new Apriori("frozenset({'x10'})","frozenset({'x8'})","conf: 1.000000000000000"));
        list.add(new Apriori("frozenset({'x8'})","frozenset({'x10'})","conf: 0.7777777777777778"));
        list.add(new Apriori("frozenset({'x2'})","frozenset({'x8'})","conf: 10.9333333333333332"));
        list.add(new Apriori("frozenset({'x8'})","frozenset({'x2'})","conf: 0.7777777777777778"));
        list.add(new Apriori("frozenset({'x3'})","frozenset({'x8'})","conf: 0.9411764705882353"));
        list.add(new Apriori("frozenset({'x8'})","frozenset({'x3'})","conf: 0.8888888888888888"));
    }
}