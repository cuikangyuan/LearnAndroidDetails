package com.cky.learnandroiddetails.TimePickerWithSecond;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.TimePickerWithSecond.wheelview.DateUtils;
import com.cky.learnandroiddetails.TimePickerWithSecond.wheelview.JudgeDate;
import com.cky.learnandroiddetails.TimePickerWithSecond.wheelview.ScreenInfo;
import com.cky.learnandroiddetails.TimePickerWithSecond.wheelview.WheelMain;
import com.cky.learnandroiddetails.TimePickerWithSecond.wheelview.WheelWeekMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者：cky
 * 时间：2016/11/8 16:00
 * 描述：
 */

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mEditText;

    private WheelMain wheelMainDate;
    private WheelWeekMain wheelWeekMainDate;
    private TextView tv_house_time;
    private TextView tv_week_house_time;
    private TextView tv_center;
    private String beginTime;
    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_picker_with_second_main_act);

        tv_house_time = (TextView) findViewById(R.id.tv_house_time);
        tv_week_house_time = (TextView) findViewById(R.id.tv_week_house_time);
        tv_center = (TextView) findViewById(R.id.tv_center);

        tv_house_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottoPopupWindow();
            }
        });

        //mButton = (Button) findViewById(R.id.show_picker);
        //mEditText = (EditText) findViewById(R.id.datetime);
/*
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DateTimePickerDialog dateTimePicKDialog = new DateTimePickerDialog(
                //        MainActivity.this);
                //dateTimePicKDialog.dateTimePicKDialog(mEditText, 0);
                showBottoPopupWindow();
            }
        });
*/
    }

    public void showBottoPopupWindow() {
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(this).inflate(R.layout.show_popup_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width*0.8),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(this);
        wheelMainDate = new WheelMain(menuView, true);
        wheelMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelMainDate.initDateTimePicker(year, month, day, hours,minute);
        final String currentTime = wheelMainDate.getTime().toString();
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(tv_center, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText("选择起始时间");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelMainDate.getTime().toString();
                try {
                    Date begin = dateFormat.parse(currentTime);
                    Date end = dateFormat.parse(beginTime);
                    tv_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
