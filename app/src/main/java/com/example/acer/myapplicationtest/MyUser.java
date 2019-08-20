package com.example.acer.myapplicationtest;

import cn.bmob.v3.BmobObject;

public class MyUser extends BmobObject {
    //用户名
    private String userName;

    //密码
    private String userPwd;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}