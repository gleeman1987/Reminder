package com.example.zhengjun.reminder;

import android.content.Context;
import android.database.Cursor;
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

        ViewHolder tag = (ViewHolder) view.getTag();
        if (tag == null) {
            tag = new ViewHolder();
            tag.colIndex = cursor.getColumnIndexOrThrow(ReminderDbAdapter.COL_IMPORTANCE);
            tag.listTab = view.findViewById(R.id.row_tab);
            view.setTag(tag);
        }

    }

    static class ViewHolder{
        int colIndex;
        View listTab;
    }
}
