package com.example.zhengjun.reminder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RemindersActivity extends AppCompatActivity {

    ListView lvReminders;
    private ReminderDbAdapter reminderDbAdapter;
    private RemindersSimpleCursorAdapter remindersSimpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        lvReminders = (ListView) findViewById(R.id.lv_reminders);
        lvReminders.setDivider(null);
        reminderDbAdapter = new ReminderDbAdapter(this);
        try {
            reminderDbAdapter.activate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reminderDbAdapter.deleteAllReminders();
        insertSomeReminders();
        Cursor c = reminderDbAdapter.fetchAllReminders();
        String[] from = new String[]{ReminderDbAdapter.COL_CONTENT};
        int[] to = new int[]{R.id.row_text};
        remindersSimpleCursorAdapter = new RemindersSimpleCursorAdapter(this, R.layout.reminders_row, c, from, to, 0);
        lvReminders.setAdapter(remindersSimpleCursorAdapter);

        lvReminders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent1, View view, int position1, long id1) {
                System.out.println("项目点击事件：parent = [" + parent1 + "], view = [" + view + "], position = [" + position1 + "], id = [" + id1 + "]");
                AlertDialog.Builder builder = new AlertDialog.Builder(RemindersActivity.this);
                ListView listView = new ListView(RemindersActivity.this);
                String[] strings = {"编辑", "删除"};
                listView.setAdapter(new ArrayAdapter<String>(RemindersActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,strings));
                builder.setView(listView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                System.out.println("项目点击事件：parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
                                System.out.println("此处需要开始编辑该处备忘记录");
                                break;
                            case 1:
                                System.out.println("项目点击事件：parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
                                System.out.println("点击删除该条备忘录");
                                break;
                        }
                        alertDialog.dismiss();
                    }
                });
            }
        });
    }

    private void insertSomeReminders() {
        reminderDbAdapter.createReminder("购买AndroidStudio实战", true);
        reminderDbAdapter.createReminder("准备元旦节红包准备金", true);
        reminderDbAdapter.createReminder("预备Perl语言学习计划", true);
        reminderDbAdapter.createReminder("预备Python语言学习计划", false);
        reminderDbAdapter.createReminder("预备正则表达式学习计划", true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*使用MenuInflater才能将自定义的菜单布置到Activity的菜单栏中去*/
        getMenuInflater().inflate(R.menu.menu_reminders, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
