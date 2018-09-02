package com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.pocketshiksha.pocketshiksha.Parents.ParentsMainActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_PARENTS_LOGIN;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_PARENT_PASSWORD;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.LOGIN_URL;
import static com.pocketshiksha.pocketshiksha.Server.Config.PARENT_LOGIN_URL;


public class ParentsLogin extends Fragment {
    private static final String TAG = "LoginActivityFireBase";
    public static String PREFS_NAME_PARENTS = "parents";
    public static String PREF_USERNAME_PARENTS = "username";
    public static String PREF_PASSWORD_PARENTS = "password";
    String pid, pass;
    @BindView(R.id.parents_login)
    TextView _login;
    @BindView(R.id.parents_pid)
    EditText parents_pid;
    @BindView(R.id.parents_input_password)
    EditText _password;
    private long backPressedTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.welcome_parents_login, container, false);
        ButterKnife.bind(this,view);
        getUser();
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parnetActivity = new Intent(getActivity().getApplicationContext(),ParentsMainActivity.class);
                startActivity(parnetActivity);
                //parentLogin();
            }
        });
        return view;
    }
    public void parentLogin() {
        Log.d(TAG, "ParentLogin");
        if (!validate()) {
            return;
        } else {

            _login.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppDialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loging in\nPlease wait..");
            progressDialog.show();

            pid = parents_pid.getText().toString().trim();
            pass = _password.getText().toString().trim();

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
        pid = parents_pid.getText().toString().trim();
        pass = _password.getText().toString().trim();
        if (pid.isEmpty()) {
            parents_pid.setError("Enter the PID");
            valid = false;

        } else {
            parents_pid.setError(null);
        }

        if (pass.isEmpty()) {
            _password.setError("Enter the Password");
            valid = false;
        } else if (pass.length() < 8 || pass.length() > 16) {
            _password.setError("Between 8 and 16 alphanumeric parents_characters");
            valid = false;
        } else {
            _password.setError(null);
        }
        return valid;
    }

    private void onChangeSuccess() {
        loginStudent();
        _login.setEnabled(true);
        getActivity().setResult(RESULT_OK, null);

    }

    public void loginStudent() {
        pid = parents_pid.getText().toString().trim();
        pass = _password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PARENT_LOGIN_URL,
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
                        Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_PARENTS_LOGIN, pid);
                map.put(KEY_PARENT_PASSWORD, pass);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

            parents_remember_login(pid, pass);//save username and password
    }

    public void getUser() {
        SharedPreferences pref = getActivity().getSharedPreferences(PREFS_NAME_PARENTS, MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME_PARENTS, null);
        String password = pref.getString(PREF_PASSWORD_PARENTS, null);

        if (username != null || password != null) {
            //directly show logout form
            openProfile(username);
        }
    }

    private void openProfile(String users_name) {
        Intent main = new Intent(getActivity().getApplicationContext(), ParentsMainActivity.class);
        main.putExtra(KEY_PARENTS_LOGIN, users_name);
        startActivity(main);
    }

    public void parents_remember_login(String users_name, String user_pass) {
        //save username and password in SharedPreferences
        getActivity().getSharedPreferences(PREFS_NAME_PARENTS, MODE_PRIVATE)
                .edit()
                .putString(PREF_USERNAME_PARENTS, users_name)
                .putString(PREF_PASSWORD_PARENTS, user_pass)
                .apply();
    }

}
