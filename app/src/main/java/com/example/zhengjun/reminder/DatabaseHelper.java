package com.example.zhengjun.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhengjun on 16/12/20.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, ReminderDbAdapter.DATABASE_NAME, null, ReminderDbAdapter.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate() called with: sqLiteDatabase = [" + sqLiteDatabase + "]");
        sqLiteDatabase.execSQL(ReminderDbAdapter.DATABASE_CREATE_SQL_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists " + ReminderDbAdapter.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private static final String TAG = "DatabaseHelper";
}
