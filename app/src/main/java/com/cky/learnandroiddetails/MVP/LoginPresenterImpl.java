package com.cky.learnandroiddetails.MVP;

/**
 * Created by cuikangyuan on 16/7/29.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    private LoginView mLoginView;

    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (mLoginView != null) {
            mLoginView.showProgress();
        }

        mLoginModel.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onSuccess() {
        if (mLoginView != null) {
            mLoginView.hideProgress();
            mLoginView.navigateToHome();
        }
    }

    @Override
    public void onUsernameError() {
        if (mLoginView != null) {
            mLoginView.setUsernameError();
            mLoginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (mLoginView != null) {
            mLoginView.setPasswordError();
            mLoginView.hideProgress();
        }
    }
}
