package com.cky.learnandroiddetails.PasswordEditTextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.widget.SubmitButtonWithAnimationView;

public class PasswordEditTextMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_edit_text_main);


        final SubmitButtonWithAnimationView viewById = (SubmitButtonWithAnimationView) findViewById(R.id.submit_view);
        viewById.setListener(new SubmitButtonWithAnimationView.ButtonOnClickListener() {
            @Override
            public void onClick(View view) {
                viewById.start();
            }
        });
    }
}
