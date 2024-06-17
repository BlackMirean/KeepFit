package com.rgzn.zt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FragmentManager fragmentManager = getSupportFragmentManager();

        // 开始Fragment事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 创建MenuFragment实例
        MenuFragment menuFragment = new MenuFragment();

        // 将MenuFragment添加到容器中（这里使用FrameLayout作为容器）
        fragmentTransaction.add(R.id.fragment_container, menuFragment);

        // 提交事务
        fragmentTransaction.commit();
    }
}