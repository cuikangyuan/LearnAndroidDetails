package com.cky.learnandroiddetails.popupWindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cky.learnandroiddetails.R;

public class PopWindowMainActivity extends AppCompatActivity {


    PopView popView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window_main);


        button = (Button) findViewById(R.id.button);
        popView = new PopView(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popView.show(button);
            }
        });
    }
}
