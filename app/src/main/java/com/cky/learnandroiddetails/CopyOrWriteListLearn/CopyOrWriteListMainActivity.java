package com.cky.learnandroiddetails.CopyOrWriteListLearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOrWriteListMainActivity extends AppCompatActivity {

    //List<ListItem> mListItems = new CopyOnWriteArrayList<>();

    List<ListItem> mListItems = new ArrayList<>();
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
        /*
        Log.d(TAG, "before");
        for (ListItem listItem : mListItems) {
            Log.d(TAG, listItem.id + "->" + listItem.name);
        }
        for (ListItem listItem : mListItems) {
            listItem.name = "修改后的name";
        }
        Log.d(TAG, "after");
        for (ListItem listItem : mListItems) {
            Log.d(TAG, listItem.id + "->" + listItem.name);
        }
        */
        /*
        for (ListItem listItem : mListItems) {
            if (listItem.id == 1) {
                //遍历时删除 只有在使用 copyOnWrite 时才可以
                mListItems.remove(listItem);
            }
        }
        Log.d(TAG, "after");
        for (ListItem listItem : mListItems) {
            Log.d(TAG, listItem.id + "->" + listItem.name);
        }
        */
        //迭代器
        Iterator<ListItem> iterator = mListItems.iterator();
        while (iterator.hasNext()) {

            if (iterator.next().id == 1) {
                iterator.remove();
            }

        }

        for (ListItem listItem : mListItems) {
            Log.d(TAG, listItem.id + "->" + listItem.name);
        }

    }
}
