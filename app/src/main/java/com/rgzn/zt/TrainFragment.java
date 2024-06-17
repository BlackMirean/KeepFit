package com.rgzn.zt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.adapter.ActionListAdapter;
import com.rgzn.zt.adapter.TrainListAdapter;
import com.rgzn.zt.entity.ActionInfo;
import com.rgzn.zt.entity.DataService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainFragment extends Fragment {

    private View rootView;
    private RecyclerView trainNavView;
    private RecyclerView trainActionView;
    private TrainListAdapter mTrainListAdapter;
    private ActionListAdapter mActionListAdapter;
    private List<String> navDataList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_train, container, false);

        //初始化控件
        trainNavView = rootView.findViewById(R.id.trainNav);
        trainActionView = rootView.findViewById(R.id.trainAction);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        navDataList.add("胸部");
        navDataList.add("背部");
        navDataList.add("腿部");
        navDataList.add("腹部");
        navDataList.add("肩部");
        navDataList.add("手臂");

        mTrainListAdapter = new TrainListAdapter(navDataList);
        trainNavView.setAdapter(mTrainListAdapter);


        mActionListAdapter = new ActionListAdapter();
        trainActionView.setAdapter(mActionListAdapter);
        //默认加载第一个
        mActionListAdapter.setListData(DataService.getListData(0));


        //recyclerView点击事件right
        mActionListAdapter.setmOnItemClickListener(new ActionListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(ActionInfo actionInfo, int position) {

                //跳转传值
                Intent intent = new Intent(getActivity(), ActionDetailsActivity.class);
                intent.putExtra("actionInfo", actionInfo);
                startActivity(intent);
            }
        });

        //recyclerView点击事件left
        mTrainListAdapter.setmNavListOnClickItemListener(new TrainListAdapter.NavListOnClickItemListener() {
            @Override
            public void onitmeClick(int position) {
                mTrainListAdapter.setCurrentIndex(position);

                //点击切换
                mActionListAdapter.setListData(DataService.getListData(position));
            }
        });


    }
}