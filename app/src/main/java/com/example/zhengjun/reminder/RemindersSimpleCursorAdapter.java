package com.example.zhengjun.reminder;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhengjun on 16/12/23.
 */

public class RemindersSimpleCursorAdapter extends SimpleCursorAdapter {
    public RemindersSimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        System.out.println("RemindersSimpleCursorAdapter.bindView");
        System.out.println("view = [" + view + "], context = [" + context + "], cursor = [" + cursor + "]");
        ViewHolder tag = (ViewHolder) view.getTag();
        if (tag == null) {
            tag = new ViewHolder();
            tag.colIndex = cursor.getColumnIndexOrThrow(ReminderDbAdapter.COL_IMPORTANCE);
            tag.listTab = view.findViewById(R.id.row_tab);
            view.setTag(tag);
        }
        tag.listTab.setBackgroundColor(context.getResources().getColor(cursor.getInt(tag.colIndex) > 0?R.color.orange:R.color.green));
    }

    static class ViewHolder{
        int colIndex;
        View listTab;
    }
}
