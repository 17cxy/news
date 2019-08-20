package com.example.acer.myapplicationtest;


import java.util.ArrayList;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestOptions;




public class MyAdapter extends BaseAdapter{
    ArrayList<CarBean.StoriesBean> stories;
    Context context;

    public MyAdapter(ArrayList<CarBean.StoriesBean> stories, Context context) {
        super();
        this.stories = stories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int position) {
        return stories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //初始化holder对象
        ViewHold holder;
        if(convertView == null){
            //把条目布局转化为view对象
            convertView = View.inflate(context, R.layout.item, null);
            //初始化holder对象，并初始化holder中的控件
            holder = new ViewHold();
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            //给当前view做个标记，并把数据存到该tag中
            convertView.setTag(holder);

        }else {
            //如果当前view存在，则直接从中取出其保存的控件及数据
            holder = (ViewHold) convertView.getTag();
        }
        //通过position获取当前item的car数据，从car数据中取出title和image
        CarBean.StoriesBean car = stories.get(position);
        holder.tv_title.setText(car.title);

        String [] str = car.images.toArray(new String[car.images.size()]);
                RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.hourglass)
                .error(R.drawable.error);

        Glide.with(context)
                .load(str[0])
                .apply(requestOptions)
                .into(holder.iv);

        return convertView;
    }

    /*
     * 用来存放item布局中控件的holder类
     */
    class ViewHold{
        ImageView iv; //显示图片的控件
        TextView tv_title; //显示标题的控件

    }
}

