package com.cky.learnandroiddetails.UnitTestExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;
/*
* 参考http://stackoverflow.com/questions/2047261/using-android-test-framework
* http://evgenii.com/blog/testing-activity-in-android-studio-tutorial-part-1/
* */
public class TestUnitTestAct extends AppCompatActivity {

    EditText mEditText;
    TextView mTextView;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_unit_test);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);
        mButton = (Button) findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditText.getText().toString();
                String s = String.format("Hello, %s", name);
                mTextView.setText(s);
            }
        });

    }
}
