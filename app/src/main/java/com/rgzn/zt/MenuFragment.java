package com.rgzn.zt;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rgzn.zt.entity.UserInfo;

public class MenuFragment extends Fragment {
    private ToggleButton body, relax, waist, back, leg, abdomen, chest, arm, shoulders;
    private TextView level, timeState;

    private TextView greetingText;
    private TextView menu_tvCalc;
    private AttendanceRingView attendanceRingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        greetingText = view.findViewById(R.id.greetingText);
        menu_tvCalc = view.findViewById(R.id.menu_tvCalc);
        attendanceRingView = view.findViewById(R.id.attendanceRingView);

        body = view.findViewById(R.id.toggleButtonqs); //全身
        relax = view.findViewById(R.id.toggleButtonfs);//放松
        waist = view.findViewById(R.id.toggleButtonyb);//腰部
        back = view.findViewById(R.id.toggleButtonbb);//背部
        leg = view.findViewById(R.id.toggleButtontb);//腿部
        abdomen = view.findViewById(R.id.toggleButtonfb);//腹部
        chest = view.findViewById(R.id.toggleButtonxb);//胸部
        arm = view.findViewById(R.id.toggleButtonsb);//手臂
        shoulders = view.findViewById(R.id.toggleButtonjb);//手臂
        relax.setChecked(true);

        level = view.findViewById(R.id.level);
        timeState = view.findViewById(R.id.timeState);
        setToggleButtonListener(body);
        setToggleButtonListener(relax);
        setToggleButtonListener(waist);
        setToggleButtonListener(back);
        setToggleButtonListener(leg);
        setToggleButtonListener(abdomen);
        setToggleButtonListener(chest);
        setToggleButtonListener(arm);
        setToggleButtonListener(shoulders);

        update();

        ImageView userImg = view.findViewById(R.id.userImg);
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在点击时启动新的 Activity
                Intent intent = new Intent(requireContext(), MainDiary.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.menuStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String totalValue = timeState.getText().toString();
                totalValue = totalValue.substring(0, totalValue.length() - 8);
                System.out.println(totalValue);
                Intent intent = new Intent(getActivity(), DoTrain.class);
                intent.putExtra("TOTAL_VALUE", totalValue);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.menu_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("介绍")
                        .setMessage("作者：     \n浙江财经大学    \n信息管理与人工智能学院   \n21人工智能3班朱腾\n")
                        .setNegativeButton("关闭", null)
                        .show();
            }
        });


        return view;
//        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    private void setToggleButtonListener(ToggleButton toggleButton) {
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                update();
            }
        });
    }

    private void update()
    {
        int count = 0;
        if (body.isChecked()) count ++ ;
        if (relax.isChecked()) count ++ ;
        if (waist.isChecked()) count ++ ;
        if (back.isChecked()) count ++ ;
        if (leg.isChecked()) count ++ ;
        if (abdomen.isChecked()) count ++ ;
        if (chest.isChecked()) count ++ ;
        if (arm.isChecked()) count ++ ;
        if (shoulders.isChecked()) count ++ ;

        if (count <= 3)
        {
            level.setText("low level");
            timeState.setText("10 minutes");
        }
        else if (count <= 6)
        {
            level.setText("middle level");
            timeState.setText("20 minutes");
        }
        else
        {
            level.setText("high level");
            timeState.setText("30 minutes");
        }
        int progress = (int)(100.0 * count / 7);
        if (progress >= 100) progress = 100;

        if (progress == 100)
            menu_tvCalc.setText("Amazing!\nYou will lost " + progress + "% of your daily calorie intake");
        else
            menu_tvCalc.setText("Great!\nYou will lost " + progress + "% of your daily calorie intake");
        attendanceRingView.setProgress(progress);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置用户数据
        UserInfo userInfo = UserInfo.getsUserInfo();
        if (null != userInfo)
        {
            greetingText.setText("Hello " + userInfo.getUsername());
        }

    }

}