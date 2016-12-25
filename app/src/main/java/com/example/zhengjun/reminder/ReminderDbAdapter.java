package com.example.zhengjun.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

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
            + COL_ID +" INTEGER PRIMERY KEY AUTO_INCREMENT, "
            + COL_CONTENT + " TEXT, "
            + COL_IMPORTANCE + " INTEGER );";
    public static final int VERSION = 1;

    private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDb;

    public ReminderDbAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void activate() throws SQLException{
        mDatabaseHelper = new DatabaseHelper(mContext);
        mDb = mDatabaseHelper.getWritableDatabase();
    }

    public void shutdown(){
        if (mDatabaseHelper != null) {
            mDatabaseHelper.close();
        }
    }

    public void createReminder(String content, boolean important){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CONTENT,content);
        contentValues.put(COL_IMPORTANCE,important?1:0);
        long insert = mDb.insert(ReminderDbAdapter.TABLE_NAME, null, contentValues);
        System.out.println("content = [" + content + "], important = [" + important + "]  插入数据库之后返回的数值为"+ insert);
    }

    public long createReminder(Reminder reminder){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CONTENT,reminder.getmContent());
        contentValues.put(COL_IMPORTANCE,reminder.getmInportance());
        return mDb.insert(TABLE_NAME,null,contentValues);
    }

    public Reminder fetchReminderById(int _id){
        Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID, COL_CONTENT, COL_IMPORTANCE}, COL_ID + " =? ", new String[]{String.valueOf(_id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return new Reminder(cursor.getInt(INDEX_ID),cursor.getString(INDEX_CONTENT),cursor.getInt(INDEX_IMPORTANCE));
        }
        return null;
    }

    public Cursor fetchAllReminders(){
        Cursor cursor = mDb.query(TABLE_NAME, new String[]{COL_ID, COL_CONTENT, COL_IMPORTANCE}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void updateReminder(Reminder reminder){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CONTENT,reminder.getmContent());
        contentValues.put(COL_IMPORTANCE,reminder.getmInportance());
        mDb.update(TABLE_NAME, contentValues, COL_ID + " = ?", new String[]{reminder.getmId() + ""});
    }

    public void deleteReminderById(int _id){
        int delete = mDb.delete(TABLE_NAME, COL_ID + " = ?", new String[]{"" + _id});
        System.out.println("此操作删除了_id为"+_id+"的记录，返回的int值为"+delete);
    }

    public void deleteAllReminders(){
        int delete = mDb.delete(TABLE_NAME, null, null);
        System.out.println("此操作删除了所有记录，返回的int值为"+delete);
    }
}
