package com.cky.learnandroiddetails.MVP;

/**
 * Created by cuikangyuan on 16/7/29.
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
