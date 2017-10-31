package com.cky.learnandroiddetails.ContentResolverTest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by cuikangyuan on 2017/10/31.
 */

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;

    private static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI("com.cky.learnandroiddetails.ContentResolverTest.provider", "table1", TABLE1_DIR);
        matcher.addURI("com.cky.learnandroiddetails.ContentResolverTest.provider", "table1/#", TABLE1_ITEM);
        matcher.addURI("com.cky.learnandroiddetails.ContentResolverTest.provider", "table2", TABLE2_DIR);
        matcher.addURI("com.cky.learnandroiddetails.ContentResolverTest.provider", "table2/#", TABLE2_ITEM);

    }


    /*
    只有当存在ContentResolver尝试访问我们程序的数据时
    内容提供器才会被初始化
    */
    @Override
    public boolean onCreate() {
        return false;
    }

    /*
    *     * 匹配任意长度的任意字符
    *     # 匹配任意长度的数字
    * */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (matcher.match(uri)) {
            case TABLE1_DIR:
                //查询1表所有数据
                break;
            case TABLE1_ITEM:
                //查询1表单条数据
                break;
            case TABLE2_DIR:
                //查询2表所有数据
                break;
            case TABLE2_ITEM:
                //查询2表单条数据
                break;
        }

        return null;

    }

    //用于获取Uri对象所对应的MINE类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (matcher.match(uri)) {
            case TABLE1_DIR:
                //查询1表所有数据
                return "vnd.android.cursor.dir/vnd.com.cky.learnandroiddetails.ContentResolverTest.provider.table1";
            case TABLE1_ITEM:
                //查询1表单条数据
                return "vnd.android.cursor.item/vnd.com.cky.learnandroiddetails.ContentResolverTest.provider.table1";
            case TABLE2_DIR:
                //查询2表所有数据
                return "vnd.android.cursor.dir/vnd.com.cky.learnandroiddetails.ContentResolverTest.provider.table2";
            case TABLE2_ITEM:
                //查询2表单条数据
                return "vnd.android.cursor.item/vnd.com.cky.learnandroiddetails.ContentResolverTest.provider.table2";
        }


        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
