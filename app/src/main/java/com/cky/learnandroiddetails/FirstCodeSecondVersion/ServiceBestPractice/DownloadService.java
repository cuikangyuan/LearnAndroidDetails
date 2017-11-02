package com.cky.learnandroiddetails.FirstCodeSecondVersion.ServiceBestPractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.cky.learnandroiddetails.R;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask mDownloadTask;
    private String downloadUrl;


    public DownloadService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mDownloadBinder;
    }

    private DownloadBinder mDownloadBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (mDownloadTask == null) {
                downloadUrl = url;
                mDownloadTask = new DownloadTask();
                mDownloadTask.setDownloadListener(mDownloadListener);
                mDownloadTask.execute(url);
                startForeground(1, getNotification("Downloading...", 0));
            }
        }

        public void pauseDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (mDownloadTask != null) {
                mDownloadTask.cancelDownload();
            }

            if (!TextUtils.isEmpty(downloadUrl)) {
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                if (file.exists()) {
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);

            }
        }


    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    private Notification getNotification(String title, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            mDownloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success...", -1));
        }

        @Override
        public void onFailed() {
            mDownloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed...", -1));
        }

        @Override
        public void onPaused() {
            mDownloadTask = null;

        }

        @Override
        public void onCanceled() {
            mDownloadTask = null;
            stopForeground(true);
        }
    };
}
