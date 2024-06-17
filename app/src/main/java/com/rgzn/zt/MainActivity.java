package com.rgzn.zt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterFactory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor; 
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.rgzn.zt.db.UserDbHelper;
import com.rgzn.zt.entity.UserInfo;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private CheckBox cb;
    private EditText uname;
    private EditText upwd;
    private static final String SP_INFO = "myuser";
    private static final String USER_ID = "UserId";
    private static final String USERPWD = "UserPwd";

    private boolean is_login = false;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.txtUserName);
        upwd = findViewById(R.id.txtPassword);
        cb = findViewById(R.id.cbRememberMe);
        Button btnLogin = findViewById(R.id.btnLogin);

        mSharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        //是否记住密码
        is_login = mSharedPreferences.getBoolean("is_login", false);
        if (is_login){
            String username = mSharedPreferences.getString("username", null);
            String password = mSharedPreferences.getString("password", null);
            uname.setText(username);
            upwd.setText(password);
            cb.setChecked(true);
        }


        Button btnReg = (Button)findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CustomDialog实现
                CustomDialog customDialog = new CustomDialog(MainActivity.this, new CustomDialog.OnCustomDialogListener() {
                    @Override
                    public void btnConfirmListenerClicked(boolean isConfirm) {
                        if (!isConfirm)
                            ToastShow("只有接受用户协议，才能注册新用户");
                        else {
                            String userName = uname.getText().toString().trim();
                            String userPass = upwd.getText().toString().trim();
                            if(userName.equals("") || userPass.equals("")){
                                ToastShow("用户名或者密码为空，请重新输入！");
                            }else{
                                int row = UserDbHelper.getInstance(MainActivity.this).register(userName, userPass, "自律使我自由！");
                                if (row > 0) ToastShow("感谢您成为我们的新用户");
                            }
                        }
                    }
                });
                customDialog.show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = uname.getText().toString();
                String userPass = upwd.getText().toString();

                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(userPass))
                {
                    Toast.makeText(MainActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    UserInfo login = UserDbHelper.getInstance(MainActivity.this).login(userName);

                    if (login != null &&userName.equals(login.getUsername()) && userPass.equals(login.getPassword()))
                    {

                        SharedPreferences.Editor edit = mSharedPreferences.edit();
                        edit.putBoolean("is_login", is_login);
                        edit.putString("username", userName);
                        edit.putString("password", userPass);
                        edit.commit();
                        UserInfo.setsUserInfo(login);
                        Toast.makeText(MainActivity.this, userName+"用户登录成功！", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(MainActivity.this, Menu.class);
                        startActivity(it);
                    } else {
                        Toast.makeText(MainActivity.this, userName+"用户名或密码错误或用户不存在", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                is_login = isCheck;
            }
        });

    }

    void ToastShow(String Content)
    {
        Toast toast = Toast.makeText(MainActivity.this, Content, Toast.LENGTH_SHORT);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.toast_template, null);
        TextView txtToastContent = (TextView)view.findViewById(R.id.txtToastContent);
        txtToastContent.setText(Content);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }



}