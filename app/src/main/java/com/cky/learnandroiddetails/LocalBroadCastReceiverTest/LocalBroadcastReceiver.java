package com.cky.learnandroiddetails.LocalBroadCastReceiverTest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by cuikangyuan on 2017/10/27.
 */

public class LocalBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到本地广播", Toast.LENGTH_SHORT).show();
    }
}
