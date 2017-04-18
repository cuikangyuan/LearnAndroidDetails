package com.cky.learnandroiddetails.popupWindow;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cky.learnandroiddetails.R;


/**
 * Created by cuikangyuan on 2017/4/18.
 * https://juejin.im/entry/58a11fca8d6d81006c9d8df2
 */

public class PopView extends PopupWindow {

    private Context mContext;
    private String[] datas = {"选项选项选项选项选项1", "选项选项选项选项选项2", "选项选项选项选项选项3", "选项选项选项选项选项4", "选项选项选项选项选项5"};

    public PopView(Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.pop_view_layout, null);
        //方法1
        //view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(context, R.layout.pop_view_item, datas));

        setWidth(200);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //setWindowLayoutType();
        setContentView(view);
    }

    public void show(View view) {

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        //showAsDropDown(view);
        //方法2

        showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1]);
        //update(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
