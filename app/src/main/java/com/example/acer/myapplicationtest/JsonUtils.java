package com.example.acer.myapplicationtest;

import com.google.gson.Gson;
public class JsonUtils {
    public static CarBean parseJson(String jsonString) {
        Gson gson = new Gson();
        CarBean cb = gson.fromJson(jsonString, CarBean.class);
        return cb;
    }
}
