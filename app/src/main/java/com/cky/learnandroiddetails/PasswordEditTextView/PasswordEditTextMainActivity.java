package com.cky.learnandroiddetails.PasswordEditTextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.widget.FadeInTextView;
import com.cky.learnandroiddetails.widget.SubmitButtonWithAnimationView;

public class PasswordEditTextMainActivity extends AppCompatActivity {

    FadeInTextView mFadeInTextView;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edit_text_main);

/*
        final SubmitButtonWithAnimationView viewById = (SubmitButtonWithAnimationView) findViewById(R.id.submit_view);
        viewById.setListener(new SubmitButtonWithAnimationView.ButtonOnClickListener() {
            @Override
            public void onClick(View view) {
                viewById.start();
            }
        });
  */

        mFadeInTextView = (FadeInTextView) findViewById(R.id.fade_text_view);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFadeInTextView
                        .setTextString("一二三四五七八九十一二三四五七八九十一二三四五七八九十一二三四五七八九十一二三四五七八九十一二三四五七八九十")
                        .startAnimation();
            }
        });
    }
}
