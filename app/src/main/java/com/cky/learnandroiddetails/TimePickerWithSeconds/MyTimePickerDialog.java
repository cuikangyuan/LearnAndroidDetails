/*
 * Copyright (C) 2007 The Android Open Source Project
 * Copyright (C) 2013 Ivan Kovac  navratnanos@gmail.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cky.learnandroiddetails.TimePickerWithSeconds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import com.cky.learnandroiddetails.R;

/**
 * A dialog that prompts the user for the time of day using a {@link TimePicker}.
 */
public class MyTimePickerDialog extends AlertDialog implements OnClickListener,
        TimePicker.OnTimeChangedListener {

    /**
     * The callback interface used to indicate the user is done filling in
     * the time (they clicked on the 'Set' button).
     */
    public interface OnTimeSetListener {

        /**
         * @param view The view associated with this listener.
         * @param hourOfDay The hour that was set.
         * @param minute The minute that was set.
         */
        void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds);
        void onTimeSet(TimePicker view, int currentYear, int currentMonth, int currentDay, int hourOfDay, int minute, int seconds);
    }

    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";
    private static final String SECONDS = "seconds";
    private static final String IS_24_HOUR = "is24hour";
    
    private final TimePicker mTimePicker;
    private final DatePicker mDatePicker;

    private final OnTimeSetListener mCallback;
    private final Calendar mCalendar;
    private final java.text.DateFormat mDateFormat;

    int mInitialHourOfDay;
    int mInitialMinute;
    int mInitialSeconds;    
    boolean mIs24HourView;

    /**
     * @param context Parent.
     * @param callBack How parent is notified.
     * @param hourOfDay The initial hour.
     * @param minute The initial minute.
     * @param is24HourView Whether this is a 24 hour view, or AM/PM.
     */
    public MyTimePickerDialog(Context context,
            OnTimeSetListener callBack,
            int hourOfDay, int minute, int seconds, boolean is24HourView) {
    	
        this(context, 0,
                callBack, hourOfDay, minute, seconds, is24HourView);
    }

    /**
     * @param context Parent.
     * @param theme the theme to apply to this dialog
     * @param callBack How parent is notified.
     * @param hourOfDay The initial hour.
     * @param minute The initial minute.
     * @param is24HourView Whether this is a 24 hour view, or AM/PM.
     */
    public MyTimePickerDialog(Context context,
            int theme,
            OnTimeSetListener callBack,
            int hourOfDay, int minute, int seconds, boolean is24HourView) {
        super(context, theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mCallback = callBack;
        mInitialHourOfDay = hourOfDay;
        mInitialMinute = minute;
        mInitialSeconds = seconds;
        mIs24HourView = is24HourView;

        mDateFormat = DateFormat.getTimeFormat(context);
        mCalendar = Calendar.getInstance();
        //updateTitle(mInitialHourOfDay, mInitialMinute, mInitialSeconds);

        setTitle("选择时间");
        setButton("确认", this);
        setButton2("取消", (OnClickListener) null);
        //setIcon(android.R.drawable.ic_dialog_time);
        
        LayoutInflater inflater = 
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.time_picker_dialog, null);
        setView(view);
        mTimePicker = (TimePicker) view.findViewById(R.id.timePicker);
        mDatePicker = (DatePicker) view.findViewById(R.id.datepicker);

        resizePikcer(mDatePicker);//调整datepicker大小
        resizePikcer(mTimePicker);//调整timepicker大小

        // initialize state
        mTimePicker.setCurrentHour(mInitialHourOfDay);
        mTimePicker.setCurrentMinute(mInitialMinute);
        mTimePicker.setCurrentSecond(mInitialSeconds);
        mTimePicker.setIs24HourView(mIs24HourView);
        mTimePicker.setOnTimeChangedListener(this);      
    }
    
    public void onClick(DialogInterface dialog, int which) {
        if (mCallback != null) {
            mTimePicker.clearFocus();
            mDatePicker.clearFocus();
            mCallback.onTimeSet(mTimePicker,
                    mDatePicker.getYear(),
                    mDatePicker.getMonth() + 1,
                    mDatePicker.getDayOfMonth(),
                    mTimePicker.getCurrentHour(),
                    mTimePicker.getCurrentMinute(),
                    mTimePicker.getCurrentSeconds());
        }
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute, int seconds) {
        //updateTitle(hourOfDay, minute, seconds);
    }
    
    public void updateTime(int hourOfDay, int minutOfHour, int seconds) {
        mTimePicker.setCurrentHour(hourOfDay);
        mTimePicker.setCurrentMinute(minutOfHour);
        mTimePicker.setCurrentSecond(seconds);
    }
    
    private void updateTitle(int hour, int minute, int seconds) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        mCalendar.set(Calendar.MINUTE, minute);
        mCalendar.set(Calendar.SECOND, seconds);
        //更新标题显示的时间内容
        setTitle(mDateFormat.format(mCalendar.getTime()) + ":" + String.format("%02d" , seconds));
    }
    
    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(HOUR, mTimePicker.getCurrentHour());
        state.putInt(MINUTE, mTimePicker.getCurrentMinute());
        state.putInt(SECONDS, mTimePicker.getCurrentSeconds());
        state.putBoolean(IS_24_HOUR, mTimePicker.is24HourView());
        return state;
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int hour = savedInstanceState.getInt(HOUR);
        int minute = savedInstanceState.getInt(MINUTE);
        int seconds = savedInstanceState.getInt(SECONDS);
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);
        mTimePicker.setCurrentSecond(seconds);
        mTimePicker.setIs24HourView(savedInstanceState.getBoolean(IS_24_HOUR));
        mTimePicker.setOnTimeChangedListener(this);
        //updateTitle(hour, minute, seconds);
    }

    /**
     * 调整FrameLayout大小
     * @param tp
     */
    private void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }

    /**
     * 得到viewGroup里面的numberpicker组件
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0; i < viewGroup.getChildCount(); i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                }
                else if(child instanceof LinearLayout){
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    /*
     * 调整numberpicker大小
     */
    private void resizeNumberPicker(NumberPicker np){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        np.setLayoutParams(params);
    }
}
