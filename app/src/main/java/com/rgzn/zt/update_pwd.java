package com.rgzn.zt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rgzn.zt.db.UserDbHelper;
import com.rgzn.zt.entity.UserInfo;

public class update_pwd extends AppCompatActivity {
    private EditText et_npwd;
    private EditText et_confirmpwd;
    private Button btn_update_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

        et_npwd = findViewById(R.id.et_npwd);
        et_confirmpwd = findViewById(R.id.et_confirmpwd);



        findViewById(R.id.btn_update_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpwd = et_npwd.getText().toString();
                String comfirmpwd = et_confirmpwd.getText().toString();

                if (TextUtils.isEmpty(newpwd) || TextUtils.isEmpty(comfirmpwd)) {
                    Toast.makeText(update_pwd.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                else if (!newpwd.equals(comfirmpwd)) {
                    Toast.makeText(update_pwd.this, "新密码和确认密码不一样！", Toast.LENGTH_SHORT).show();
                } else {
                    UserInfo userInfo = UserInfo.getsUserInfo();
                    if (null != userInfo) {
                        int row = UserDbHelper.getInstance(update_pwd.this).updatePwd(userInfo.getUsername(), newpwd);
                        if (row > 0) {
                            Toast.makeText(update_pwd.this, "修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                            setResult(1000);
                            finish();
                        } else {
                            Toast.makeText(update_pwd.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }

                    }


                }
            }
        });

        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}