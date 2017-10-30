package com.cky.learnandroiddetails.SQliteTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cuikangyuan on 2017/10/30.
 */

public class MySQliteOpenHelper extends SQLiteOpenHelper {


    public static final String CREATE_BOOK_SQL = "create table Book ( "
                                            + "id integer primary key autoincrement,"
                                            + "author text,"
                                            + "price real, "
                                            + "pages integer,"
                                            + "name text)";

    public static final String CREATE_CATEGORY_SQL = "create table Category ( "
                                            + "id integer primary key autoincrement,"
                                            + "category_name text,"
                                            + "category_code integer) ";

    private Context mContext;

    private static final String TAG = "MySQliteOpenHelper";

    public MySQliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_SQL);
        db.execSQL(CREATE_CATEGORY_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");

        onCreate(db);
        Log.d(TAG, "onUpgrade: " + "newVersion " + newVersion + " oldVersion " +oldVersion);
    }
}
