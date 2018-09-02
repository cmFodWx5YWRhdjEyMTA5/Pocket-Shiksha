package com.pocketshiksha.pocketshiksha.Students;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Server.Config;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.BlueBookFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.HomeFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.ProfileFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.StudyMaterialFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.TrackerFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.forms.AddingCollege;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.forms.BugForm;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.forms.IssueForm;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.forms.QueryForm;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.chat.MailActivityMain;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.ViewPagerAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.DepthPageTransformer;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginActivityFireBase;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes.StudentLoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;

public class StudentsMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

//    private FirebaseAnalytics mFirebaseAnalytics;
    public TabLayout tabLayout;
    public String firstname,studentemail, lastname,studentPID, pid, image_url;
    public android.os.Handler customHandler;
    public TextView nav_name, emailView;
    public Spinner student_usn;
    CircleImageView dp_image;
    int login_type;
    private ViewPager viewPager;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private static final String SHOWCASE_ID = "sequence example";

    public ArrayList<String> USN_array;
    private JSONArray student_college_usn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the FirebaseAnalytics instance.
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        pid = getIntent().getExtras().getString(KEY_STUDENTS_PID);
        USN_array=new ArrayList<String>();




        int[] icons = {
                R.drawable.tab_profile,
                R.drawable.tab_library,
                R.drawable.tab_home,
                R.drawable.tab_exams,
                R.drawable.tab_tracker,};

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /*  */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);


        nav_name = (TextView) header.findViewById(R.id.nav_name);
        student_usn = (Spinner) header.findViewById(R.id.student_usn_view);
        dp_image = (CircleImageView) header.findViewById(R.id.dp_image);
        dp_image.setImageResource(R.drawable.ic_dafault_male);
        //TODO populating USN Spinner
        //getUSNData();


        //TODO, get students details and dp
        studentString();
        StudentDPString();
        //TODO the main bottom navigation
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.main_tab_content);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ActionBar mActionBar = getActionBar();
                if (position == 0) {
                    setTitle("Profile");
                } else if (position == 1) {
                    setTitle("Study Material");
                    //mActionBar.setBackgroundDrawable(
                    //        new ColorDrawable(ContextCompat.getColor(StudentsMainActivity.this, R.color.color
                    // )));
                } else if (position == 2) {
                    setTitle("Home");
                    //mActionBar.setBackgroundDrawable(colorHome);
                } else if (position == 3) {
                    setTitle("Blue Book");
                    // mActionBar.setBackgroundDrawable(colorExams);
                } else if (position == 4) {
                    setTitle("Tracker");
                    // mActionBar.setBackgroundDrawable(colorTracker);
                }

            }
        });
        setupViewPager(viewPager);

        viewPager.setPageTransformer(true, new DepthPageTransformer());
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < icons.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(icons[i]);
            tabLayout.getTabAt(i).setCustomView(imageView);
        }
        tabLayout.getTabAt(2).select();


        viewPager.setOffscreenPageLimit(5);
        presentShowcaseSequence();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.insertNewFragment(new ProfileFragment());
        adapter.insertNewFragment(new StudyMaterialFragment());
        adapter.insertNewFragment(new HomeFragment());
        adapter.insertNewFragment(new BlueBookFragment());
        adapter.insertNewFragment(new TrackerFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            Intent chatt= new Intent(StudentsMainActivity.this, MailActivityMain.class);
            startActivity(chatt);
        }if(id == R.id.action_notification ){

            Toast.makeText(this, "Shows Notification", Toast.LENGTH_SHORT).show();
        }if(id == R.id.action_logout){
            SharedPreferences sharedPrefs = getSharedPreferences(StudentLoginActivity.PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.clear();
            editor.apply();
            pid = "";
            Intent logout = new Intent(this, LoginActivityFireBase.class);
            startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            viewPager.setCurrentItem(0);
           // Toast.makeText(this, "You have selected Profile", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_library) {
            viewPager.setCurrentItem(1);
           // Toast.makeText(this, "You have selected Study Material", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_exams) {
            viewPager.setCurrentItem(3);
            //Toast.makeText(this, "You have selected Exams", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_tracker) {
            viewPager.setCurrentItem(4);
            //Toast.makeText(this, "You have selected Tracker", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_add_college) {
            Intent add_new_college = new Intent(this, AddingCollege.class);
            add_new_college.putExtra(KEY_STUDENTS_PID, pid);
            startActivity(add_new_college);

        } else if (id == R.id.nav_logout) {

            SharedPreferences sharedPrefs = getSharedPreferences(StudentLoginActivity.PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.clear();
            editor.apply();
            pid = "";
            Intent logout = new Intent(this, LoginActivityFireBase.class);
            startActivity(logout);
        } else if (id == R.id.nav_about) {
            Toast.makeText(this, "About Pocket Shiksha", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_locate) {
            Toast.makeText(this, "This will help locate us \n On Google Maps", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_query) {
            Intent query = new Intent(this, QueryForm.class);
            startActivity(query);
        } else if (id == R.id.nav_bug) {
            Intent bug = new Intent(this, BugForm.class);
            startActivity(bug);
        } else if (id == R.id.nav_issues) {
            Intent issu = new Intent(this, IssueForm.class);
            startActivity(issu);
        }

        //TODO top notification buttons
        else if (id == R.id.action_chats){
            Intent chat = new Intent(StudentsMainActivity.this, MailActivityMain.class);
            startActivity(chat);
        }else if (id == R.id.action_notification){
            Toast.makeText(this,"This will be the notification space",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.action_logout){
            SharedPreferences sharedPrefs = getSharedPreferences(StudentLoginActivity.PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.clear();
            editor.apply();
            pid = "";
            Intent logout = new Intent(this, LoginActivityFireBase.class);
            startActivity(logout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void studentString() {
        String get_data = Config.STUDENT_DATA + pid;
        StringRequest stringRequest = new StringRequest(get_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(Config.DATA_JSON_ARRAY);
                    JSONObject collegeData = result.getJSONObject(0);
                    firstname = collegeData.getString(Config.KEY_STUDENT_FNAME);
                    lastname = collegeData.getString(Config.KEY_STUDENT_LNAME);
                    studentemail = collegeData.getString(Config.KEY_STUDENTS_EMAIL);
                    studentPID = collegeData.getString(Config.KEY_STUDENTS_PID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                nav_name.setText(firstname + " " + lastname);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentsMainActivity.this, "Error getting details", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void StudentDPString() {

        String dp_url = Config.GET_DP + pid;
        StringRequest stringRequest = new StringRequest(dp_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(Config.DATA_JSON_ARRAY);
                    JSONObject collegeData = result.getJSONObject(0);
                    image_url = collegeData.getString(Config.KEY_STUDENT_DP);
                    RequestQueue mRequestQueue = Volley.newRequestQueue(StudentsMainActivity.this);
                    ImageRequest imageRequest = new ImageRequest(image_url, new BitmapListener(), 0, 0, null, new MyErrorListener());
                    mRequestQueue.add(imageRequest);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentsMainActivity.this, "Something Is very Wrong", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
           // case R.id.fab:
                //Intent chat = new Intent(StudentsMainActivity.this, MailActivityMain.class);
              //  startActivity(chat);
                //animateFAB();
             //   break;
         //   case R.id.fab1:
        //        viewPager.setCurrentItem(0);
        //        break;
       //     case R.id.fab2:
        //        viewPager.setCurrentItem(4);
        //        break;
        }
    }

    public void animateFAB() {

        if (isFabOpen) {

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Pocket Shiksha", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Pocket Shiksha", "open");

        }
    }

    private class BitmapListener implements Response.Listener<Bitmap> {
        @Override
        public void onResponse(Bitmap response) {
            dp_image.setImageBitmap(response);

        }
    }

    private class MyErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
//store a default image if connection failed
            Toast.makeText(StudentsMainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            dp_image.setImageResource(R.drawable.ic_default_profile);
        }
    }
    private void presentShowcaseSequence() {
        TabLayout tabLayout = ButterKnife.findById(StudentsMainActivity.this, R.id.tab_layout);
        View t1,t2,t3,t4,t5 = null;
        if (tabLayout != null) {
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            if (tab != null) {

                ShowcaseConfig config = new ShowcaseConfig();
                config.setDelay(500);
                MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

                sequence.setConfig(config);
                t1 = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
                t2 = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
                t3 = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);
                t4 = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3);
                t5 = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(4);
                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(this)
                                .setTarget(t1)
                                .setDismissText("GOT IT")
                                .setContentText("Here you can view your profile")
                                .withCircleShape()
                                .build()
                );
                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(this)
                                .setTarget(t2)
                                .setDismissText("GOT IT")
                                .setContentText("Here you can see all the study materials.")
                                .withCircleShape()
                                .build()
                );
                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(this)
                                .setTarget(t3)
                                .setDismissText("GOT IT")
                                .setContentText("This is where you get to the Library and the store.")
                                .withCircleShape()
                                .build()
                );
                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(this)
                                .setTarget(t4)
                                .setDismissText("GOT IT")
                                .setContentText("This contains all your schedules related to the University")
                                .withCircleShape()
                                .build()
                );
                sequence.addSequenceItem(
                        new MaterialShowcaseView.Builder(this)
                                .setTarget(t5)
                                .setDismissText("GOT IT")
                                .setContentText("This contains your academic performance")
                                .withCircleShape()
                                .build()
                );


                sequence.start();
            }
        }

    }




    //TODO USN DATA
    private void getUSNData(){
        String data_url = Config.DATA_USN_URL + pid;
        StringRequest stringRequest = new StringRequest(data_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            student_college_usn = obj.getJSONArray(Config.JSON_USN_ARRAY);
                            getUSNes(student_college_usn);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUSNes(JSONArray bra) {
        student_usn.setAdapter(null);
        for(int i=0;i<bra.length();i++){
            try {
                JSONObject json = bra.getJSONObject(i);
                USN_array.add(json.getString(Config.TAG_USN_NAME));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        student_usn.setAdapter(new ArrayAdapter<String>(this,
                R.layout.spinner_layout, USN_array));
    }
}


