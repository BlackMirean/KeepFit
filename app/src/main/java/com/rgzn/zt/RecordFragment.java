package com.rgzn.zt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.adapter.PlanListAdapter;
import com.rgzn.zt.db.PlanDbHelper;
import com.rgzn.zt.entity.PlanInfo;

import java.util.List;

public class RecordFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private TextView tvtotal;
    private Button btn_start;

    private PlanListAdapter mPlanListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_record, container, false);

        //初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        tvtotal = rootView.findViewById(R.id.tvtotal);
        btn_start = rootView.findViewById(R.id.btn_start);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化mPlanListAdapter
        mPlanListAdapter = new PlanListAdapter();
        recyclerView.setAdapter(mPlanListAdapter);

        //recyclerView点击事件
        mPlanListAdapter.setmOnItemClickListener(new PlanListAdapter.onItemClickListener() {
            @Override
            public void onAddOnClick(PlanInfo planInfo, int position) {
                PlanDbHelper.getInstance(getActivity()).updateAction(planInfo.getPlan_id(), planInfo);
                loadData();
            }

            @Override
            public void onSubOnClick(PlanInfo planInfo, int position) {
                PlanDbHelper.getInstance(getActivity()).subupdateAction(planInfo.getPlan_id(), planInfo);
                loadData();
            }

            @Override
            public void delOnClick(PlanInfo planInfo, int position) {
                PlanDbHelper.getInstance(getActivity()).delete(planInfo.getPlan_id()+"");
                loadData();
            }
        });

        //结算点击事件
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalValue = tvtotal.getText().toString();
                totalValue = totalValue.substring(0, totalValue.length() - 8);
                System.out.println(totalValue);
                Intent intent = new Intent(getActivity(), DoTrain.class);
                intent.putExtra("TOTAL_VALUE", totalValue);
                startActivity(intent);

            }
        });


        loadData();
    }

    private void setTotalData(List<PlanInfo> list) {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            int price = list.get(i).getAction_timeState() * list.get(i).getAction_count();
            total += price;
        }
        tvtotal.setText(total+" minutes");
    }

    public void loadData(){
        //获取数据
        List<PlanInfo> planList = PlanDbHelper.getInstance(getActivity()).queryPlanList("ls");
        //设置数据
        mPlanListAdapter.setPlanInfoList(planList);
        setTotalData(planList);
    }

}