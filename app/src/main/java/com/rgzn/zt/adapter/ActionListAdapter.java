package com.rgzn.zt.adapter;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.R;
import com.rgzn.zt.entity.ActionInfo;

import java.util.ArrayList;
import java.util.List;

public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.MyHolder>{

    private List<ActionInfo> mActionInfoList = new ArrayList<>();

    public void setListData(List<ActionInfo> list){
        this.mActionInfoList = list;
        //一定要刷新
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //加载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_list_item, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //绑定数据
        ActionInfo actionInfo = mActionInfoList.get(position);

        //设置数据
        holder.action_img.setImageResource(actionInfo.getAction_img());
        holder.aciton_title.setText(actionInfo.getAction_title());
        holder.aciton_details.setText(actionInfo.getAction_details());
        holder.aciton_timeState.setText(actionInfo.getTimeState()+" minutes");//将TimeState的int类型转为String

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(actionInfo, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActionInfoList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        ImageView action_img;
        TextView aciton_title;
        TextView aciton_details;
        TextView aciton_timeState;


        public MyHolder(@NonNull View itemView){
            super(itemView);
            action_img = itemView.findViewById(R.id.action_img);
            aciton_title = itemView.findViewById(R.id.action_title);
            aciton_details = itemView.findViewById(R.id.action_details);
            aciton_timeState = itemView.findViewById(R.id.action_timeState);

        }

    }

    private onItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(ActionInfo actionInfo, int position);
    }


}
