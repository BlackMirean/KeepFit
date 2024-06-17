package com.rgzn.zt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.R;
import com.rgzn.zt.entity.ActionInfo;
import com.rgzn.zt.entity.PlanInfo;

import java.util.ArrayList;
import java.util.List;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.MyHoler> {

    private List<PlanInfo> mPlanInfoList = new ArrayList<>();

    public void setPlanInfoList(List<PlanInfo> list) {
        this.mPlanInfoList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_item, null);


        return new MyHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHoler holder, int position) {

        //绑定数据
        PlanInfo planInfo = mPlanInfoList.get(position);

        //设置数据
        holder.action_img.setImageResource(planInfo.getAction_img());
        holder.aciton_title.setText(planInfo.getAction_title());
        holder.aciton_timeState.setText(planInfo.getAction_timeState()+" minutes");//将TimeState的int类型转为String
        holder.action_count.setText(planInfo.getAction_count()+"");

        //设置点击事件
        holder.btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onSubOnClick(planInfo, position);
                }
            }
        });

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onAddOnClick(planInfo, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPlanInfoList.size();
    }

    static class MyHoler extends RecyclerView.ViewHolder{

        ImageView action_img;
        TextView aciton_title;
        TextView aciton_timeState;
        TextView action_count;
        TextView btn_sub;
        TextView btn_add;


        public MyHoler(@NonNull View itemView) {
            super(itemView);

            action_img = itemView.findViewById(R.id.action_img);
            aciton_title = itemView.findViewById(R.id.action_title);
            aciton_timeState = itemView.findViewById(R.id.action_timeState);
            action_count = itemView.findViewById(R.id.action_count);
            btn_sub = itemView.findViewById(R.id.btn_sub);
            btn_add = itemView.findViewById(R.id.btn_add);

        }
    }

    private onItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface onItemClickListener{
        void onAddOnClick(PlanInfo planInfo, int position);
        void onSubOnClick(PlanInfo planInfo, int position);
        void delOnClick(PlanInfo planInfo, int position);
    }


}
