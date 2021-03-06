package com.cky.learnandroiddetails.FirstCodeSecondVersion.ServiceBestPractice;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cuikangyuan on 2017/11/2.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListener mDownloadListener;

    private boolean mIsPaused = false;
    private boolean mIsCanceled = false;

    private int mLastProgress;

    private static final String TAG = "DownloadTask1";
    
    public void setDownloadListener(DownloadListener downloadListener) {
        mDownloadListener = downloadListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);

            if (file.exists()) {
                downloadedLength = file.length();
                Log.d(TAG, "暂停前已下载的字节长度 " + downloadedLength);
            }

            long contentLength = getContentLength(downloadUrl);
            Log.d(TAG, "文件总字节长度 " + contentLength);
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                return TYPE_SUCCESS;
            }


            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            Response response = okHttpClient.newCall(request).execute();

            if (response != null) {
                inputStream = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = inputStream.read(b)) != -1) {
                    if (mIsCanceled) {
                        return TYPE_CANCELED;
                    } else if (mIsPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        int progress = (int)((total + downloadedLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }

                if (mIsCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        int progress = values[0];
        if (progress > mLastProgress) {
            if (mDownloadListener != null) {
                mDownloadListener.onProgress(progress);
            }
            mLastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {

        if (mDownloadListener != null) {
            switch (integer) {
                case TYPE_SUCCESS:
                    mDownloadListener.onSuccess();
                    break;
                case TYPE_FAILED:
                    mDownloadListener.onFailed();
                    break;
                case TYPE_PAUSED:
                    mDownloadListener.onPaused();
                    break;
                case TYPE_CANCELED:
                    mDownloadListener.onCanceled();
                    break;
                default:
                    break;
            }
        }
    }

    public void pauseDownload() {
        mIsPaused = true;
    }

    public void cancelDownload() {
        mIsCanceled = true;
    }

    private long getContentLength(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }

        return 0;
    }
}
