package com.example.acer.myapplicationtest;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import static com.example.acer.myapplicationtest.LoginActivity.flag;
import static com.example.acer.myapplicationtest.LoginActivity.strName;


public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<CarBean.StoriesBean> stories;
    private MyAdapter adapter;

    private ExecutorService es;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DrawerLayout drawerLayout = findViewById(R.id.dl_activity_main);
        Toolbar mToolbar=findViewById(R.id.toolbar);
        //设置导航Button点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        lv = (ListView) findViewById(R.id.lv);

        NavigationView mNavigationView=findViewById(R.id.nav_activity_main);
        mNavigationView.setCheckedItem(R.id.login);
        final View headview = mNavigationView.inflateHeaderView(R.layout.nav_head);
//登录后显示用户名
        if(flag == true) {
            TextView Title = headview.findViewById(R.id.name);
            Title.setText(strName);
        }
//给菜单设置监听
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){

                    case R.id.login://登录
                        Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.register://注册
                        Intent intent2 = new Intent(MainActivity.this,RegisterActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.logout://登出
                        TextView Title = headview.findViewById(R.id.name);
                        Title.setText("未登录");

                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        //存放json解析的数据的集合
        stories = new ArrayList<CarBean.StoriesBean>();
        //自定义适配器
        adapter = new MyAdapter(stories,this);

//        //给listview设置一个底部view(必须在设置数据之前)
//        View footView = View.inflate(this, R.layout.footer, null);
//        lv.addFooterView(footView);

        //给listview设置适配器
        lv.setAdapter(adapter);

        //使用线程池来实现异步任务的多线程下载
        es = Executors.newFixedThreadPool(10);
        new DownAsynctask(stories,adapter,this).executeOnExecutor(es, "http://news-at.zhihu.com/api/4/news/latest");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long iid) {



                Intent intent = new Intent();
                CarBean.StoriesBean car = stories.get(position);

                intent.putExtra("id",car.id);//获取点击item的id。传入webview
                Log.i("c",car.id);
                intent.setClass(MainActivity.this,WebActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
