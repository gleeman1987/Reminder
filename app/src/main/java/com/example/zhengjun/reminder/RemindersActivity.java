package com.example.zhengjun.reminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RemindersActivity extends AppCompatActivity {

    @Bind(R.id.lv_reminders)
    ListView lvReminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        ButterKnife.bind(this);
        lvReminders.setAdapter(new ArrayAdapter<String>(this,R.layout.reminders_row,R.id.row_text,new String[]{"first record","second record","third record"}));
    }
}
