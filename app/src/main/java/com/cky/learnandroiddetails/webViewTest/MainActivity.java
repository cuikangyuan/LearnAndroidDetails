package com.cky.learnandroiddetails.webViewTest;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.cky.learnandroiddetails.R;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity_main);


        initView();
    }

    private void initView() {
        FrameLayout root = (FrameLayout) findViewById(R.id.root);

        WebView webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //return super.shouldOverrideUrlLoading(view, url);


                //当前webview 加载
                //若没有设置 WebViewClient 则在点击链接之后由系统处理该 url，通常是使用浏览器打开或弹出浏览器选择对话框。
                //若设置 WebViewClient 且该方法返回 true ，则说明由应用的代码处理该 url，WebView 不处理。
                //view.loadUrl(url);
                //return true;
                //若设置 WebViewClient 且该方法返回 false，则说明由 WebView 处理该 url，即用 WebView 加载该 url。
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        });

        root.addView(webView);

        webView.loadUrl(URL);
    }
}
