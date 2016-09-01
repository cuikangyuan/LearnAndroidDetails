package com.cky.learnandroiddetails;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.cky.learnandroiddetails.widget.RejectedPopWindow;

import java.util.ArrayList;

public class TestWidgetAct extends AppCompatActivity {

    Button mShowPopUpBtn;

    private static final String TAG = TestWidgetAct.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_widget);

        mShowPopUpBtn = (Button)findViewById(R.id.btnShowPopWindow);

        mShowPopUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showPopUpWindow();
                showRejectedPopWindow();
            }
        });
    }

    private void showRejectedPopWindow() {
        PopupWindow popUpWindow = new RejectedPopWindow(TestWidgetAct.this, TestWidgetAct.this.getWindow(), new ArrayList());
        popUpWindow.showAtLocation(TestWidgetAct.this.findViewById(R.id.activity_test_widget),
                Gravity.BOTTOM,
                0,
                0);
    }

    private void showPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_up_window, null);

        PopupWindow popUpWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        popUpWindow.setFocusable(true);

        Drawable drawable = new ColorDrawable(0xb000000);
        popUpWindow.setBackgroundDrawable(drawable);

        popUpWindow.setAnimationStyle(R.style.pop_window_anim_style);

        //popUp 窗体从底部出现
        popUpWindow.showAtLocation(TestWidgetAct.this.findViewById(R.id.activity_test_widget),
                Gravity.BOTTOM,
                0,
                0);

        //popUp 窗体出现时 整体背景变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);


        Button btn1 = (Button)view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Btn1 is clicked");
            }
        });

        popUpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d(TAG, "onDismiss");
                //popUp 窗体消失时 整体背景恢复原样
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
