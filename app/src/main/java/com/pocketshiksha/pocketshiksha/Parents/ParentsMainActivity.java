package com.pocketshiksha.pocketshiksha.Parents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.chat.MailActivityMain;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.GridHomeAdapter;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginActivityFireBase;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes.StudentLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentsMainActivity extends AppCompatActivity {

    @BindView(R.id.parent_mainGrid)
    GridView parent_grid;
    public int[] imageIDs = {
            R.drawable.ic_library,
            R.drawable.ic_events,
            R.drawable.ic_store,
            R.drawable.ic_fees,
            R.drawable.ic_books,
            R.drawable.ic_chat_box,
            R.drawable.ic_exams_white,
            R.drawable.ic_tracker_white
    };
    public String[] gridcolor ={
            "#1E8B95",
            "#D84C4C",
            "#E06919",
            "#4CD8BF",
            "#1E8B95",
            "#D84C4C",
            "#E06919",
            "#4CD8BF"};
    public String[] gridTitle = {
            "Marks",
            "Events",
            "Attendance",
            "Fees",
            "Activities",
            "Reports",
            "Schedules",
            "Assignments"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parents_activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPrefs = getSharedPreferences(StudentLogin.PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.clear();
                editor.apply();
                Intent logout = new Intent(ParentsMainActivity.this, LoginActivityFireBase.class);
                startActivity(logout);
            }
        });

       if(savedInstanceState == null){
           parent_grid.setAdapter(new GridHomeAdapter(this, imageIDs, gridcolor, gridTitle));
           parent_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   switch (position) {
                       case 0:
                           Intent marksActivity = new Intent(ParentsMainActivity.this, ParentMarks.class);
                           startActivity(marksActivity);
                           break;
                       case 1:
                           Toast.makeText(ParentsMainActivity.this, "Events", Toast.LENGTH_SHORT).show();
                           break;
                       case 2:
                           Toast.makeText(ParentsMainActivity.this, "Attendance", Toast.LENGTH_SHORT).show();
                           break;
                       case 3:
                           Toast.makeText(ParentsMainActivity.this, "Fees", Toast.LENGTH_SHORT).show();
                           break;
                   }
               }
           });
       }
       else{
           parent_grid.setAdapter(null);
           parent_grid.setAdapter(new GridHomeAdapter(this, imageIDs, gridcolor, gridTitle));
           parent_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   switch (position) {
                       case 0:
                           Intent marksActivity = new Intent(ParentsMainActivity.this, ParentMarks.class);
                           startActivity(marksActivity);
                           break;
                       case 1:
                           Toast.makeText(ParentsMainActivity.this, "Events", Toast.LENGTH_SHORT).show();
                           break;
                       case 2:
                           Toast.makeText(ParentsMainActivity.this, "Attendance", Toast.LENGTH_SHORT).show();
                           break;
                       case 3:
                           Toast.makeText(ParentsMainActivity.this, "Fees", Toast.LENGTH_SHORT).show();
                           break;
                   }
               }
           });
       }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }if (id == R.id.action_chats){
            Intent chatt= new Intent(ParentsMainActivity.this, MailActivityMain.class);
            startActivity(chatt);
        }if(id == R.id.action_notification ){

            Toast.makeText(this, "Shows Notification", Toast.LENGTH_SHORT).show();
        }if(id == R.id.action_logout){
            //SharedPreferences sharedPrefs = getSharedPreferences(StudentLogin.PREFS_NAME, MODE_PRIVATE);
            //SharedPreferences.Editor editor = sharedPrefs.edit();
            //editor.clear();
            // editor.apply();
            //pid = "";
            Intent logout = new Intent(this, LoginActivityFireBase.class);
            startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }

}
