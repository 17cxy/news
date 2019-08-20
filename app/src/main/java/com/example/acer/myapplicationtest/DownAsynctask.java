package com.example.acer.myapplicationtest;



import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.acer.myapplicationtest.CarBean.StoriesBean;

public class DownAsynctask extends AsyncTask<String, Void, byte[]>{

    ArrayList<CarBean.StoriesBean>stories;
    MyAdapter adapter;
    Context context;

    public DownAsynctask(ArrayList<CarBean.StoriesBean> stories, MyAdapter adapter, Context context) {
        super();
        this.stories = stories;
        this.adapter = adapter;
        this.context = context;
    }

    /*
     * 当主线程中调用executeOnExecutor方法或execute方法时，会调用此方法
     */
    @Override
    protected byte[] doInBackground(String... params) {
        //下载网络数据
        return NetUtils.getNetData(params[0]);
    }

    /*
     * doInBackground方法执行之后会执行此方法，并把结果传过来
     */
    @Override
    protected void onPostExecute(byte[] result) {
        super.onPostExecute(result);
        if (result != null) {
            //把从网络上获取的byte类型的数据转换为String字符串
            String jsonString = new String(result);
            Log.i("all",jsonString);
            //用json解析工具来解析该字符串数据
            CarBean cb = JsonUtils.parseJson(jsonString);
            //取出data数据，并保存到集合中
            stories.addAll(cb.stories);
            //刷新数据
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
        }
    }
}

