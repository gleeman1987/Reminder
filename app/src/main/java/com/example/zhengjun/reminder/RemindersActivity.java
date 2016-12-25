package com.example.zhengjun.reminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RemindersActivity extends AppCompatActivity {

    @Bind(R.id.lv_reminders)
    ListView lvReminders;
    private ReminderDbAdapter reminderDbAdapter;
    private RemindersSimpleCursorAdapter remindersSimpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        ButterKnife.bind(this);
        lvReminders.setDivider(null);
        reminderDbAdapter = new ReminderDbAdapter(this);
        try {
            reminderDbAdapter.activate();
            if (savedInstanceState != null) {
                reminderDbAdapter.deleteAllReminders();
                insertSomeReminders();
                remindersSimpleCursorAdapter = new RemindersSimpleCursorAdapter(this, R.id.lv_reminders, reminderDbAdapter.fetchAllReminders(), null, null, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertSomeReminders() {
        reminderDbAdapter.createReminder("购买AndroidStudio实战",true);
        reminderDbAdapter.createReminder("准备元旦节红包准备金",true);
        reminderDbAdapter.createReminder("与覃俊杰共进晚餐",false);
        reminderDbAdapter.createReminder("预备Perl语言学习计划",true);
        reminderDbAdapter.createReminder("预备Python语言学习计划",true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*使用MenuInflater才能将自定义的菜单布置到Activity的菜单栏中去*/
        getMenuInflater().inflate(R.menu.menu_reminders,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new:
                Log.d(TAG, "onOptionsItemSelected: create new Reminder");
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:

                return false;
        }
    }

    private static final String TAG = "RemindersActivity";
}
