package com.cky.learnandroiddetails;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestTipActivity extends AppCompatActivity {

    Button mButtonShowTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tip);

        mButtonShowTip = (Button) findViewById(R.id.btnShowTip);

        mButtonShowTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialogTip();
                //showToastTip();
                showSnackTip();
            }
        });
    }

    private void showDialogTip() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestTipActivity.this);
        builder.setTitle("Title")
                .setMessage("Dialog content.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

    private void showToastTip() {
        //Toast.makeText(TestTipActivity.this, "Toast", Toast.LENGTH_SHORT).show();
        //ToastUtil.showToast(TestTipActivity.this, "Toast");
    }

    private void showSnackTip() {
        Snackbar.make(mButtonShowTip, "Snack", Snackbar.LENGTH_SHORT)
                .setAction("UnDo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }
}
