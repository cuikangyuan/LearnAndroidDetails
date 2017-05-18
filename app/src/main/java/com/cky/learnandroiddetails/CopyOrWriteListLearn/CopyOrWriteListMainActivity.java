package com.cky.learnandroiddetails.CopyOrWriteListLearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOrWriteListMainActivity extends AppCompatActivity {

    List<ListItem> mListItems = new CopyOnWriteArrayList<>();

    private static final String TAG = "list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_or_write_list_main);


        test();
    }

    private void test() {
        ListItem item;
        for (int i=1; i<6; i++) {
            item = new ListItem();
            item.id = i;
            item.name = "name" + i;

            mListItems.add(item);
        }
        for (ListItem listItem : mListItems) {
            Log.d(TAG, listItem.id + "->" + listItem.name);
        }

    }
}
