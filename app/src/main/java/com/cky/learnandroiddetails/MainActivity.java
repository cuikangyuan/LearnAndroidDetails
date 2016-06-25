package com.cky.learnandroiddetails;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvTest;

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = (TextView) findViewById(R.id.tvTest);

        /*
        * 1.子线程 更新 UI 控件
        * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                tvTest.setText("How Are You ?");
            }
        }).start();


        /*
        * 2.活动 和 服务绑定
        * */
        Intent bindIntent = new Intent(this, MyService.class);
        /*
        *BIND_AUTO_CREATE 活动和服务进行绑定之后 自动创建服务 服务的onCreate会执行 onStartCommand方法不执行
        * */
        bindService(bindIntent, mConnection, BIND_AUTO_CREATE);//绑定服务
        unbindService(mConnection);//解绑服务
    }
}
