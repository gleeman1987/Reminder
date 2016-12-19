package com.example.zhengjun.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zhengjun on 16/12/20.
 */

public class ReminderDbAdapter {
    public static final String COL_ID = "_id";
    public static final String COL_CONTENT = "content";
    public static final String COL_IMPORTANCE = "importance";

    public static final int INDEX_ID = 0;
    public static final int INDEX_CONTENT = INDEX_ID + 1;
    public static final int INDEX_IMPORTANCE = INDEX_ID + 2;

    private static final String TAG = "ReminderDbAdapter";

    public static final String DATABASE_NAME = "db_reminders";
    public static final String TABLE_NAME = "table_reminders";

    public static final String DATABASE_CREATE_SQL_STATEMENT = "CREATE TABLE if not exists "
            + TABLE_NAME + " ( "
            + COL_ID +" INTEGER PRIMERY KEY AUTOINCREMENT, "
            + COL_CONTENT + " TEXT, "
            + COL_IMPORTANCE + " INTEGER );";
    public static final int VERSION = 1;

    private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDb;

}
