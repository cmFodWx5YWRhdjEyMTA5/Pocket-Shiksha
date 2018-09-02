package com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.pocketshiksha.pocketshiksha.welcome.registration.StudentRegistrationActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.LOGIN_URL;


public class StudentLogin extends Fragment {
    private static final String TAG = "LoginActivityFireBase";
    public static String PREFS_NAME = "mypre";
    public static String PREF_USERNAME = "username";
    public static String PREF_PASSWORD = "password";
    String pid, pass;
    @BindView(R.id.student_login)
    TextView s_login;
    @BindView(R.id.student_pid)
    EditText students_pid;
    @BindView(R.id.input_password)
    EditText input_password;
    @BindView(R.id.to_reg)
    TextView to_reg;
    private long backPressedTime = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.welcome_student_login, container, false);
        ButterKnife.bind(this, view);
        to_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_student = new Intent(getActivity().getApplicationContext(), StudentRegistrationActivity.class);
                startActivity(reg_student);
            }
        });

        //getUser();
        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO ADD Conncetivity
                //studentLogin();
                //TODO REMOVE THIS LINE
                Intent main = new Intent(getActivity().getApplicationContext(), StudentsMainActivity.class);
                startActivity(main);
            }
        });
        return view;
    }



    public void studentLogin() {
        Log.d(TAG, "StudentLogin");
        if (!validate()) {
            return;
        } else {

            s_login.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
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
        getActivity().setResult(RESULT_OK, null);

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
                            Toast.makeText(getActivity().getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

            rememberMe(pid, pass);//save username and password
    }

    public void getUser() {
        SharedPreferences pref = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if (username != null || password != null) {
            //directly show logout form
            openProfile(username);
        }
    }

    private void openProfile(String users_name) {
        Intent main = new Intent(getActivity().getApplicationContext(), StudentsMainActivity.class);
        main.putExtra(KEY_STUDENTS_PID, users_name);
        startActivity(main);
    }

    public void rememberMe(String users_name, String user_pass) {
        //save username and password in SharedPreferences
        getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putString(PREF_USERNAME, users_name)
                .putString(PREF_PASSWORD, user_pass)
                .apply();
    }
}
