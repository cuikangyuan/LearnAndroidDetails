package com.cky.learnandroiddetails;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class TestWebViewAct extends Activity {
    /*
    * http://www.kymjs.com/code/2015/05/03/01
    *
    * http://www.kymjs.com/code/2015/05/04/01
    * */
    WebView mWebView;

    private static final String TARGET_URL = "http://www.chiphell.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_view);
        
        initView();

        loadData();

    }

    private void initView() {

        mWebView = (WebView) findViewById(R.id.web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new WebAppInterface(TestWebViewAct.this), "Android");

        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private void loadData() {
        mWebView.loadUrl(TARGET_URL);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            /*
            if (Uri.parse(url).getHost().equals(TARGET_URL)) {
                return false;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
            */
            /*
            点击的链接在当前browser中响应 而不是打开系统浏览器来响应
            * */
            view.loadUrl(url);
            return true;
        }
    }
}
