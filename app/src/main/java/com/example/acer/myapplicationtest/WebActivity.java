package com.example.acer.myapplicationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebActivity extends Activity {
    private WebView webview;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);


       Toolbar mToolbar=findViewById(R.id.toolbar2);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webview.goBack();
                finish();//结束退出程序
            }

        });


        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
//设置Web视图
        webview.setWebViewClient(new webViewClient ());
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "http://daily.zhihu.com/story/"+id;


       webview.loadUrl(url);
        webview.setWebViewClient(new webViewClient ());
    }

@Override
//设置回退
//覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
        webview.goBack(); //goBack()表示返回WebView的上一页面
        return true;
    }
    finish();//结束退出程序
    return false;
}

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}



