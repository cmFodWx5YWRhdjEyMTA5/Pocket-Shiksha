package com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.StudentsMainActivity;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.MyBounceInterpolator;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.LOGIN_URL;

public class StudentLoginActivity extends Activity {

    private static final String TAG = "LoginActivityFireBase";
    public static String PREFS_NAME = "mypre";
    public static String PREF_USERNAME = "username";
    public static String PREF_PASSWORD = "password";
    public String pid, pass;

    @BindView(R.id.login_title)
    TextView login_title;
    @BindView(R.id.sigIn) TextView s_login;
    @BindView(R.id.content_place)
    RelativeLayout content_place;
    @BindView(R.id.user_profile_photo)
    ImageView user_profile_photo;
    @BindView(R.id.student_pid)
    EditText students_pid;
    @BindView(R.id.input_password)
    EditText input_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_student_login);
        ButterKnife.bind(StudentLoginActivity.this);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Comfortaa-Bold.ttf");
        s_login.setTypeface(face);


        final Animation myAnim = AnimationUtils.loadAnimation(StudentLoginActivity.this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
        myAnim.setInterpolator(interpolator);
        content_place.startAnimation(myAnim);
        user_profile_photo.startAnimation(myAnim);
        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLogin();
                //Intent startparent = new Intent(StudentLoginActivity.StudentLoginActivity.this,StudentsMainActivity.class);
                //startActivity(startparent);
            }
        });
    }
    public void studentLogin() {
        Log.d(TAG, "StudentLogin");
        if (!validate()) {
            return;
        } else {

            s_login.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(StudentLoginActivity.this,
                    R.style.AppDialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loging in\nPlease wait..");
            progressDialog.show();

            pid = students_pid.getText().toString().trim();
            pass = input_password.getText().toString().trim();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onChangeSuccess();
                            progressDialog.dismiss();
                        }
                    }, 10000);

        }
    }

    private boolean validate() {
        boolean valid = true;
        pid = students_pid.getText().toString().trim();
        pass = input_password.getText().toString().trim();
        if (pid.isEmpty()) {
            students_pid.setError("Enter the PID");
            valid = false;

        } else {
            students_pid.setError(null);
        }

        if (pass.isEmpty()) {
            input_password.setError("Enter the Password");
            valid = false;
        } else if (pass.length() < 8 || pass.length() > 16) {
            input_password.setError("Between 8 and 16 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }
        return valid;
    }

    private void onChangeSuccess() {
        loginStudent();
        s_login.setEnabled(true);
        this.setResult(RESULT_OK, null);

    }

    public void loginStudent() {
        pid = students_pid.getText().toString().trim();
        pass = input_password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            openProfile(pid);
                        } else {
                            Toast.makeText(StudentLoginActivity.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(StudentLoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_STUDENTS_PID, pid);
                map.put(KEY_STUDENT_PASS, pass);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        rememberMe(pid, pass);//save username and password
    }

    public void getUser() {
        SharedPreferences pref = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if (username != null || password != null) {
            //directly show logout form
            openProfile(username);
        }
    }

    private void openProfile(String users_name) {
        Intent main = new Intent(StudentLoginActivity.this, StudentsMainActivity.class);
        main.putExtra(KEY_STUDENTS_PID, users_name);
        startActivity(main);
    }

    public void rememberMe(String users_name, String user_pass) {
        //save username and password in SharedPreferences
        this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_USERNAME, users_name)
                .putString(PREF_PASSWORD, user_pass)
                .apply();
    }
}
