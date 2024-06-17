package com.rgzn.zt;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rgzn.zt.db.PlanDbHelper;
import com.rgzn.zt.entity.ActionInfo;

public class ActionDetailsActivity extends AppCompatActivity {

    private ImageView action_img;
    private TextView aciton_title;
    private TextView aciton_details;
    private TextView aciton_timeState;

    private ActionInfo actionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_details);

        //获取数据
        actionInfo = (ActionInfo) getIntent().getSerializableExtra("actionInfo");

        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //初始化控件
        action_img = findViewById(R.id.action_img);
        aciton_title = findViewById(R.id.action_title);
        aciton_details = findViewById(R.id.action_details);
        aciton_timeState = findViewById(R.id.action_timeState);

        //设置数据
        if (actionInfo != null) {
            action_img.setImageResource(actionInfo.getAction_img());
            aciton_title.setText(actionInfo.getAction_title());
            aciton_details.setText(actionInfo.getAction_details());
            aciton_timeState.setText(actionInfo.getTimeState() + " minutes");
        }

        findViewById(R.id.addPlan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //加入到计划
                int row = PlanDbHelper.getInstance(ActionDetailsActivity.this).addPlan("ls",
                        actionInfo.getAction_id(),
                        actionInfo.getAction_img(),
                        actionInfo.getAction_title(),
                        actionInfo.getTimeState()
                );

                if (row > 0){
                    Toast.makeText(ActionDetailsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActionDetailsActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}