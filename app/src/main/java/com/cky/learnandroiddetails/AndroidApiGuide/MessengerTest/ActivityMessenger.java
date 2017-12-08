package com.cky.learnandroiddetails.AndroidApiGuide.MessengerTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cky.learnandroiddetails.R;

public class ActivityMessenger extends AppCompatActivity {


    Messenger mMessenger = null;

    boolean mBound;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mMessenger = new Messenger(service);

            mBound = true;
            Toast.makeText(getApplicationContext(), "onServiceConnected", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            mMessenger = null;

            mBound = false;

            Toast.makeText(getApplicationContext(), "onServiceDisconnected", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayHello();
            }
        });
    }

    public void sayHello() {
        if (!mBound) {
            return;
        }

        Message message = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        //message.replyTo = mMessenger; 服务端可使用传递过去的messenger 向客户端发送响应
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        unbindService(mServiceConnection);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(
                new Intent(this, MessengerService.class),
                mServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
