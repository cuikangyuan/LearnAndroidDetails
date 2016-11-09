package com.cky.learnandroiddetails.TimePickerWithSeconds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;

import java.util.Calendar;

/**
 * 作者：cky
 * 时间：2016/11/9 09:14
 * 描述：
 */

public class MainActivity extends AppCompatActivity {
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_picker_with_seconds);
        updateViews();
    }

    private void updateViews(){
        time = (TextView) findViewById(R.id.time);
    }

    public void showPicker(View v){
        Calendar now = Calendar.getInstance();
        MyTimePickerDialog mTimePicker = new MyTimePickerDialog(this, new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds) {
                time.setText(getString(R.string.time) + String.format("%02d", hourOfDay)+
                        ":" + String.format("%02d", minute) +
                        ":" + String.format("%02d", seconds));
            }

            @Override
            public void onTimeSet(TimePicker view, int currentYear, int currentMonth, int currentDay, int hourOfDay, int minute, int seconds) {
                time.setText(getString(R.string.time) +
                                String.format("%d", currentYear)+
                                "-" + String.format("%02d", currentMonth)+
                                "-" + String.format("%02d", currentDay)+
                                " " + String.format("%02d", hourOfDay)+
                                ":" + String.format("%02d", minute) +
                                ":" + String.format("%02d", seconds));
            }
        }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND), true);
        mTimePicker.show();
    }
}
