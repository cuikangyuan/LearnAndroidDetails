package com.cky.learnandroiddetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TestViewAct extends AppCompatActivity implements onStartDragListener {

    ArrayList<Contact> mContacts;
    RecyclerViewPlus mRecyclerViewPlus;

    ContactAdapter mContactAdapter;

    ItemTouchHelper touchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        mContacts = Contact.createList(20);
        mContactAdapter = new ContactAdapter(TestViewAct.this, mContacts, this);

        mRecyclerViewPlus = (RecyclerViewPlus) findViewById(R.id.rvPlus);

        mRecyclerViewPlus.setLayoutManager(new LinearLayoutManager(this));

        TextView textView1 = new TextView(TestViewAct.this);
        TextView textView2= new TextView(TestViewAct.this);

        textView1.setText("Header-1");

        textView2.setText("Footer-1");

        //mContactAdapter.addHeader(textView1);
        //mContactAdapter.addFooter(textView2);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(TestViewAct.this, DividerItemDecoration.VERTICAL_LIST);

        mRecyclerViewPlus.addItemDecoration(itemDecoration);

        mRecyclerViewPlus.setAdapter(mContactAdapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallBack(mContactAdapter);

        touchHelper = new ItemTouchHelper(callback);

        touchHelper.attachToRecyclerView(mRecyclerViewPlus);

        mContactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToast(TestViewAct.this, position + "");
            }
        });
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }
}
