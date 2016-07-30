package com.cky.learnandroiddetails.MVP;
import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by cuikangyuan on 16/7/29.
 */
public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isError = false;
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    isError = true;
                }

                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                    isError = true;
                }

                if (!isError) {
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
