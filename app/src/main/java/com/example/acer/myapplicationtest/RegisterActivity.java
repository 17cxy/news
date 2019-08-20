package com.example.acer.myapplicationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText editName;

    private EditText editPwd;

    private Button btnRegister;

    private String strName;

    private String strPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        editName = (EditText) findViewById(R.id.edit_register_name);
        editPwd = (EditText) findViewById(R.id.edit_register_pwd);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });
    }

    /**
     * 用户注册
     */
    private void userRegister() {
        getInput();
        MyUser mUser = new MyUser();
        mUser.setUserName(strName);
        mUser.setUserPwd(strPwd);
        mUser.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    Toast.makeText(getApplicationContext(),
                            "注册成功！请登录",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "注册失败！" + "错误码：" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void getInput() {
        strName = editName.getText().toString();
        strPwd = editPwd.getText().toString();
    }
}