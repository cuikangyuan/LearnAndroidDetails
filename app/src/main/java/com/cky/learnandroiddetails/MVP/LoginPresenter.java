package com.cky.learnandroiddetails.MVP;

/**
 * Created by cuikangyuan on 16/7/29.
 */
public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();
}
