package com.cky.learnandroiddetails;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvTest;
    Button btnStartService;
    Button btnStartIntentService;

    private MyService.DownloadBinder mDownloadBinder;

    private static final String TAG = MainActivity.class.getSimpleName();

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
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStartIntentService = (Button)findViewById(R.id.btnStartIntentService);
        /*
        * 1.子线程 更新 UI 控件
        * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                tvTest.setText("How Are You ?");
            }
        }).start();

        btnStartService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        /*
        * 2.活动 和 服务绑定
        *
        Intent bindIntent = new Intent(this, MyService.class);
        /*
        *BIND_AUTO_CREATE 活动和服务进行绑定之后 自动创建服务 服务的onCreate会执行 onStartCommand方法不执行
        bindService(bindIntent, mConnection, BIND_AUTO_CREATE);//绑定服务
        startService(bindIntent);
        */
        //unbindService(mConnection);//解绑服务

        /*
        * 后台计时服务
        * */
        Intent i = new Intent(this, LongRunningService.class);
        startService(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnStartService:
                Log.d(TAG, "btnStartService executed");
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
                break;
            case R.id.btnStartIntentService:
                Log.d(TAG, "btnStartIntentService executed");
                Log.d(TAG, "Thread id is " + Thread.currentThread().getId());
                Intent intentService = new Intent(MainActivity.this, MyIntentService.class);
                startService(intentService);
                break;
        }


    }
}
