package com.cky.learnandroiddetails;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

public class LongRunningService extends Service {

    private static final String TAG = LongRunningService.class.getSimpleName();


    @Override
    public void onCreate() {
        /*
        service 运行在主线程中

        因此 service 执行耗时任务依旧会ANR 但我们可以在service中创建子线程 在子线程中
        执行耗时逻辑
        * */
        Log.d(TAG, "LongRunningService Thread id is " + Thread.currentThread().getId());
        super.onCreate();
    }


    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "executed at " + new Date().toString());
            }
        }).start();


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int tenSeconds = 10 * 1000;
        Long triggerAtTime = SystemClock.elapsedRealtime() + tenSeconds;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
