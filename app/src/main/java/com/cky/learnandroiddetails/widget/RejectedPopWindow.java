package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.RecyclerViewPlus;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/1.
 */

public class RejectedPopWindow extends PopupWindow {

    private Context mContext;
    private Window mWindow;

    private View mPopContentView;

    private RecyclerViewPlus mRecyclerView;

    private Button mConfirm;

    private ArrayList data;

    public RejectedPopWindow(Context context, Window window, ArrayList data) {

        this.mContext = context;
        this.mWindow = window;
        this.data = data;

        initView();
        
    }

    private void initView() {
        mPopContentView = LayoutInflater.from(mContext).inflate(R.layout.reject_pop_window, null);
        mRecyclerView = (RecyclerViewPlus) mPopContentView.findViewById(R.id.rv_content);
        mConfirm = (Button) mPopContentView.findViewById(R.id.btn_confirm);

        this.setContentView(mPopContentView);

        this.setHeight(300);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        this.setFocusable(true);

        Drawable drawable = new ColorDrawable(0xb000000);
        this.setBackgroundDrawable(drawable);

        this.setAnimationStyle(R.style.pop_window_anim_style);
    }

}
