package com.cky.learnandroiddetails.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.RecyclerViewPlus;
import com.cky.learnandroiddetails.adapter.PopUpWindowRvAdapter;
import com.cky.learnandroiddetails.model.PopUpWindowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */

public class RejectedPopWindow extends PopupWindow {

    private Context mContext;
    private Window mWindow;

    private View mPopContentView;

    private RecyclerViewPlus mRecyclerView;

    private PopUpWindowRvAdapter mAdapter;

    private Button mConfirm;

    private List<PopUpWindowItem> data;

    public RejectedPopWindow(Context context, Window window, ArrayList data) {

        this.mContext = context;
        this.mWindow = window;
        this.data = data;

        initVariables();

        initView();

        setListener();
        
    }


    private void initVariables() {
        if (data == null) {
            data = new ArrayList<>();
        }

        loadFakeData();
    }

    private void loadFakeData() {
        for (int i = 0; i < 10; i++) {
            PopUpWindowItem item = new PopUpWindowItem();
            item.setId(i);
            item.setName("水果 " + i);

            data.add(item);
        }
    }

    private void initView() {

        mAdapter = new PopUpWindowRvAdapter(this.mContext, this.data);

        mPopContentView = LayoutInflater.from(mContext).inflate(R.layout.reject_pop_window, null);
        mRecyclerView = (RecyclerViewPlus) mPopContentView.findViewById(R.id.rv_content);
        mConfirm = (Button) mPopContentView.findViewById(R.id.btn_confirm);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        this.setContentView(mPopContentView);

        this.setHeight(wm.getDefaultDisplay().getHeight() / 2);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        this.setFocusable(true);

        //Drawable drawable = new ColorDrawable(0xb000000);
        //this.setBackgroundDrawable(drawable);

        this.setAnimationStyle(R.style.pop_window_anim_style);

    }


    private void setListener() {

        mAdapter.setOnItemClickListener(new PopUpWindowRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RejectedPopWindow.this.dismiss();
            }
        });

    }

}
