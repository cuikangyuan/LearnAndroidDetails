package com.cky.learnandroiddetails.MVP;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.cky.learnandroiddetails.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private ProgressBar mProgressBar;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.validateCredentials(etUsername.getText().toString().trim(),
                        etPassword.getText().toString().trim());
            }
        });

        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        etUsername.setError("用户名不能为空!");
    }

    @Override
    public void setPasswordError() {
        etPassword.setError("密码不能为空!");
    }

    @Override
    public void navigateToHome() {
        Snackbar.make(btnLogin, "登录成功", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.onDestroy();
        super.onDestroy();
    }
}
