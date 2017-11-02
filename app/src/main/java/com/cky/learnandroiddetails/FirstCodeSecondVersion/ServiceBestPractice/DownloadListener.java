package com.cky.learnandroiddetails.FirstCodeSecondVersion.ServiceBestPractice;

/**
 * Created by cuikangyuan on 2017/11/2.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
