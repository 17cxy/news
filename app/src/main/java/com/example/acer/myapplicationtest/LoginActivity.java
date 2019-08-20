package com.example.acer.myapplicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editName;

    private EditText editPwd;

    private Button btnLogin;

    private Button btnToRegister;

    static String strName;

    private String strPwd;

    static  boolean flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bmob.initialize(this, "4c43f077ab9b4b6adf81eccf8d67544c");
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        editName = (EditText) findViewById(R.id.edit_login_name);
        editPwd = (EditText) findViewById(R.id.edit_login_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnToRegister = (Button) findViewById(R.id.btn_to_register);
        btnLogin.setOnClickListener(this);
        btnToRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.btn_to_register:
                toRegister();
                break;
            default:
                break;
        }
    }


    private void userLogin() {
        getInput();
        BmobQuery<MyUser> userQuery = new BmobQuery<MyUser>();
        userQuery.addWhereEqualTo("userName",strName);
        userQuery.addWhereEqualTo("userPwd",strPwd);
        userQuery.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if(e == null) {
                    if(list.size() == 1) {
                        flag = true;
                        Toast.makeText(getApplicationContext(),
                                "登录成功！",
                                Toast.LENGTH_SHORT).show();

                        Intent intentMain = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intentMain);

                    } else {
                        flag = false;
                        Toast.makeText(getApplicationContext(),
                                "用户名或密码错误，请重新输入！",
                                Toast.LENGTH_SHORT).show();
                        cleanInput();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "登录失败，错误码：" + e.getErrorCode(),
                            Toast.LENGTH_SHORT).show();
                    cleanInput();
                }
            }
        });

    }

    private void toRegister() {
        Intent intentReg = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intentReg);
    }

    /**
     * 获取数据
     */
    private void getInput() {
        strName = editName.getText().toString();
        strPwd = editPwd.getText().toString();
    }

    /**
     * 清除数据
     */
    private void cleanInput() {
        editName.setText("");
        editPwd.setText("");
    }
}