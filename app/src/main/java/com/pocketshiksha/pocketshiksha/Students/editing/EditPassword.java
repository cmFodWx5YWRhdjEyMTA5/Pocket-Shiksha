package com.pocketshiksha.pocketshiksha.Students.editing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.pocketshiksha.pocketshiksha.Server.Config;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pocketshiksha.pocketshiksha.Server.Config.CHECK_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PASS;

public class EditPassword extends AppCompatActivity {

    private static final String TAG = "EditPassword";
    TextView jhig;
    String usn, user_pass;
    @BindView(R.id.new_password)
    EditText _new_password;
    @BindView(R.id.confirm_new_password)
    EditText _confirm_new_password;
    @BindView(R.id.current_password)
    EditText _current_password;
    @BindView(R.id.update_pass)
    Button _update_pass;
    @BindView(R.id.cancel_pass)
    Button _cancel_pass;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_password_activity);

        ButterKnife.bind(this);

        usn = getIntent().getExtras().getString(KEY_STUDENTS_PID);
        user_pass = getIntent().getExtras().getString(KEY_STUDENT_PASS);
        jhig = (TextView) findViewById(R.id.jhig);
        jhig.setText(usn);


        _update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword();
            }
        });

        _cancel_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ChangePassword() {
        Log.d(TAG, "ChangePassword");
        if (!validate()) {

            return;
        } else {

            _update_pass.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(EditPassword.this,
                    R.style.AppDialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Changing Password...");
            progressDialog.show();

            String new_pass = _new_password.getText().toString();
            String con_new_pass = _confirm_new_password.getText().toString();
            String old_pass = _current_password.getText().toString();

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onChangeSuccess();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }

    public void onChangeSuccess() {
        passCheck();
        _update_pass.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    private void updateEmployee() {
        final String new_pass = _new_password.getText().toString().trim();
        String up_pass = Config.UPDATE_STUDENT_PASS + usn;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, up_pass,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(EditPassword.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditPassword.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(KEY_STUDENTS_PID, usn);
                params.put(KEY_STUDENT_PASS, new_pass);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public boolean validate() {
        boolean valid = true;

        String new_pass = _new_password.getText().toString();
        String con_new_pass = _confirm_new_password.getText().toString();
        String old_pass = _current_password.getText().toString();

        if (old_pass.isEmpty()) {
            _current_password.setError("Enter the Password");
            valid = false;
        } else {
            _current_password.setError(null);
        }

        if (new_pass.isEmpty()) {
            _new_password.setError("Please enter a new Password");
            valid = false;
        } else if (new_pass.equals(old_pass)) {
            _new_password.setError("Password cannot be equal to the last used Password");
            valid = false;
        } else {
            _new_password.setError(null);
        }

        if (con_new_pass.isEmpty()) {
            _confirm_new_password.setError("Re-enter the New Password");
            valid = false;
        } else if (!(con_new_pass.equals(new_pass))) {
            _confirm_new_password.setError("Passwords doesn't Match");
            valid = false;
        } else {
            _confirm_new_password.setError(null);
        }

        return valid;
    }

    public void passCheck() {
        final String pass = _current_password.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, CHECK_PASS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            updateEmployee();
                        } else {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_STUDENTS_PID, usn);
                map.put(KEY_STUDENT_PASS, pass);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
