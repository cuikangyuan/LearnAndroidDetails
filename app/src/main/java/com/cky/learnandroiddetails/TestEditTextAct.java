package com.cky.learnandroiddetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ToggleButton;

public class TestEditTextAct extends AppCompatActivity {


    private EditText etPassword;
    private ToggleButton tbShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit_text);

        etPassword = (EditText) findViewById(R.id.etPassword);
        tbShowPassword = (ToggleButton) findViewById(R.id.btnToggle);

        tbShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbShowPassword.isChecked()) {
                    etPassword.setInputType(EditorInfo.TYPE_CLASS_TEXT |
                    EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etPassword.setInputType(EditorInfo.TYPE_CLASS_TEXT |
                            EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                }

                etPassword.setSelection(etPassword.getText().length());
            }
        });
    }
}
