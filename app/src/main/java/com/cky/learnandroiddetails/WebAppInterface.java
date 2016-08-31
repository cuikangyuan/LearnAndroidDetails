package com.cky.learnandroiddetails;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/31.
 */

public class WebAppInterface {

    Context context;

    public WebAppInterface(Context context) {

        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

}
