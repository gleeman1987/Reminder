package com.example.zhengjun.reminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

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
        File file = new File(getFilesDir(), "a.jpg");
        if (!file.exists()) {
            file.mkdir();
        }
        lvReminders.setAdapter(new ArrayAdapter<String>(this,R.layout.reminders_row,R.id.row_text,new String[]{"first record","second record","third record"}));
        System.out.println("fileList:"+fileList().length);
        for (String s : fileList()) {
            Log.d(TAG, "onOptionsItemSelected: "+s);
        }
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
