package com.cky.learnandroiddetails;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by cuikangyuan on 16/6/25.
 */
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    private static final String TAG = MyService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload executed");
        }

        public int getProgress() {
            Log.d(TAG, "getProgress executed");
            return 0;
        }
    }
}
