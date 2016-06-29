package com.cky.learnandroiddetails;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service {

    private static final String TAG = RemoteService.class.getSimpleName();

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        //mBinder 就是 Binder 的子类 同时 Binder 又实现了 IBinder
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate executed");

        /*
        //不会 造成 ANR 因为该 服务运行在另外一个 进程中 并不会影响当前的应用程序
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {

            if (str != null) {
                return str.toUpperCase();
            }

            return null;
        }
    };
}
