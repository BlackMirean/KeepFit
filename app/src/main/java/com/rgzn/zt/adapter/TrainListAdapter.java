package com.rgzn.zt.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.R;

import java.util.ArrayList;
import java.util.List;

public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.MyHolder> {

    private List<String> dataList = new ArrayList<>();
    private int currentIndex = 0;

    public TrainListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_nav_item, null);
        return new MyHolder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        // 绑定数据
        String action = dataList.get(position);
        holder.tv_action.setText(action);

        //分类的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mNavListOnClickItemListener){
                    mNavListOnClickItemListener.onitmeClick(position);
                }
            }
        });

        //
        if (currentIndex == position){
            holder.itemView.setBackgroundResource(R.drawable.type_selector_bg);
        }else {
            holder.itemView.setBackgroundResource(R.drawable.type_selector_normal);
        }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_action;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_action = itemView.findViewById(R.id.action);
        }
    }

    private NavListOnClickItemListener mNavListOnClickItemListener;

    public void setmNavListOnClickItemListener(NavListOnClickItemListener mNavListOnClickItemListener) {
        this.mNavListOnClickItemListener = mNavListOnClickItemListener;
    }

    public interface  NavListOnClickItemListener{
        void onitmeClick(int position);

    }

    public void setCurrentIndex(int position){
        this.currentIndex = position;
        notifyDataSetChanged();
    }

}
