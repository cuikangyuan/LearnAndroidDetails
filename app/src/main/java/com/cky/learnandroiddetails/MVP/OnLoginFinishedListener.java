package com.cky.learnandroiddetails.MVP;

/**
 * Created by cuikangyuan on 16/7/29.
 */
public interface OnLoginFinishedListener {
    void onSuccess();

    void onUsernameError();

    void onPasswordError();
}
