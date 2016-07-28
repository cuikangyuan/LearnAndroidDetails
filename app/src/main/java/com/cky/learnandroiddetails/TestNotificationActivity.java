package com.cky.learnandroiddetails;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class TestNotificationActivity extends AppCompatActivity {

    Button btnShowTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        btnShowTip = (Button)findViewById(R.id.btnShowTip);

        btnShowTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(TestNotificationActivity.this);

                Notification notification = builder.setContentTitle("这是通知标题")
                        .setContentText("这是通知内容")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setColor(Color.parseColor("#96c85a"))
                        .build();

                manager.notify(1, notification);
            }
        });
    }
}
