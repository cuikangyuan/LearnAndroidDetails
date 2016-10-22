package com.cky.learnandroiddetails.webviewandh5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.cky.learnandroiddetails.R;

public class WebViewAndH5 extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_and_h5);

        mWebView = (WebView) findViewById(R.id.web_view);

        WebSettings mWebViewSettings = mWebView.getSettings();

        mWebViewSettings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new JsInteration(), "control");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                testMethod(mWebView);
            }
        });

        mWebView.loadUrl("file:///android_asset/js_java_interaction.html");
    }

    private void testMethod(WebView webView) {
        String call = "javascript:sayHello()";

        //call = "javascript:alertMessage(\"" + "我是原生传递到H5的参数" + "\")";

        //call = "javascript:toastMessage(\"" + "我先从原生到H5,再从H5传递到原生" + "\")";

        call = "javascript:sumToJava(1,2)";
        webView.loadUrl(call);

    }

    public class JsInteration {

        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Toast.makeText(getApplicationContext(), "onSumResult result=" + result, Toast.LENGTH_LONG).show();
        }
    }
}
