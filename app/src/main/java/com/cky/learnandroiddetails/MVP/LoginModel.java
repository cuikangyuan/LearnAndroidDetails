package com.cky.learnandroiddetails.MVP;

/**
 * Created by cuikangyuan on 16/7/29.
 */
public interface LoginModel {
    void login(String username, String password, OnLoginFinishedListener listener);
}
