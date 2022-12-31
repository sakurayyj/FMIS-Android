package com.example.fmis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fmis.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AprioriAdapter extends RecyclerView.Adapter<AprioriAdapter.ViewHolder>{

    List<Apriori> list ;

    public AprioriAdapter(List<Apriori> list){
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public AprioriAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_apriori,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AprioriAdapter.ViewHolder holder, int position) {
        Apriori apriori = list.get(position);
        holder.textView1.setText(apriori.getFor1());
        holder.textView2.setText(apriori.getFor2());
        holder.textView3.setText(apriori.getConf());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.re_for);
            textView2 = itemView.findViewById(R.id.re_for2);
            textView3 = itemView.findViewById(R.id.re_conf);
        }
    }
}
