package com.pocketshiksha.pocketshiksha.welcome.logins;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.DepthPageTransformer;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginAdapters.LoginAdapter;
import com.pocketshiksha.pocketshiksha.welcome.registration.StudentRegistrationActivity;

public class LoginActivityFireBase extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    public ViewPager mViewPager;
    public TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_login);
        tabLayout = (TabLayout)findViewById(R.id.login_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Student"));
        tabLayout.addTab(tabLayout.newTab().setText("Parents"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.FOCUSABLES_TOUCH_MODE);
        mViewPager = (ViewPager) findViewById(R.id.login_viewPager);
        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.registration_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.register_student:
                Intent reg_student = new Intent(this, StudentRegistrationActivity.class);
                startActivity(reg_student);
            case R.id.forgot_pass:
                    Toast.makeText(this,"To main kya karu be?", Toast.LENGTH_LONG).show();
            case R.id.what_is:
                Toast.makeText(this,"Tera Baap",Toast.LENGTH_SHORT).show();;
            default:
                return super.onContextItemSelected(item);

        }
    }

}