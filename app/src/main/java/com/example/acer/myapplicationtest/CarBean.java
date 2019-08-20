package com.example.acer.myapplicationtest;


import java.util.ArrayList;
import java.util.List;

public class CarBean {

     ArrayList<StoriesBean> stories; //对应json数据中的stories数组

    public static class StoriesBean {
         List<String> images; //封面图
          String id; //详情链接id
          String title;


    }
}


