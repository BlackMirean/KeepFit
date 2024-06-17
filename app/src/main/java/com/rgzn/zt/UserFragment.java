package com.rgzn.zt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rgzn.zt.entity.UserInfo;


public class UserFragment extends Fragment {

    private View rootView;
    TextView tv_username;
    TextView tv_nickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_user, container, false);

        tv_username = rootView.findViewById(R.id.tv_username);
        tv_nickname = rootView.findViewById(R.id.tv_nickname);

        rootView.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("确定要退出登陆吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActivity().finish();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })

                        .show();
            }
        });


        rootView.findViewById(R.id.update_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), update_pwd.class);
                startActivityForResult(intent, 1000);
            }
        });



        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置用户数据
        UserInfo userInfo = UserInfo.getsUserInfo();
        if (null != userInfo)
        {
            tv_username.setText(userInfo.getUsername());
            tv_nickname.setText(userInfo.getNickname());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            getActivity().finish();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

    }
}