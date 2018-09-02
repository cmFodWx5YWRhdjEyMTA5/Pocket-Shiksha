package com.pocketshiksha.pocketshiksha.welcome.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Server.Config;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.CustomProgressDialog;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginActivityFireBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_PARENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_PARENT_PH;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_EMAIL;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_FNAME;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_LNAME;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PH;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_USN;
import static com.pocketshiksha.pocketshiksha.Server.Config.LOGIN_URL;
import static com.pocketshiksha.pocketshiksha.Server.Config.STUDENT_REGISTER_URL;

public class StudentRegistrationActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.fname) EditText f_name;
    @BindView(R.id.lname) EditText l_name;
  @BindView(R.id.s_dob) EditText st_usn;
  @BindView(R.id.s_gender) EditText st_gender;
  @BindView(R.id.student_email) EditText st_email;
  @BindView(R.id.input_password) EditText st_password;
  @BindView(R.id.s_phone) EditText st_phone;
  @BindView(R.id.p_phone) EditText pa_phone;
    @BindView(R.id.regComplete) TextView regComplete;
  @BindView(R.id.r_password) TextView re_pass;
    @BindView(R.id.back_to_login) TextView back_to_login;
    @BindView(R.id.student_picture) CircleImageView student_picture;
    public CharSequence[] values = {"Registering your details","Setting you PID","Getting you enrolled to Pocket Shiksha","Setting up your profile"};
    public boolean[] itemCheck = new boolean[values.length];
    private int _progress = 0;
    private Handler _progressHandlee;
    public CustomProgressDialog dialog;



    @BindView(R.id.registration_course)    Spinner stream_reg_spinner;
    @BindView(R.id.registration_branch) Spinner branch_reg_spinner;
    @BindView(R.id.registration_college) Spinner college_bra_spinner;
    public ArrayList<String> branch_reg_array;
    public ArrayList<String> stream_reg_array;
    public ArrayList<String> college_reg_array;
    public String stream_id, branch_id, college_id;
    public JSONArray reg_result,reg_branches, reg_college;


    private static final String TAG = "Registrationy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_students_registration);
        ButterKnife.bind(this);
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(StudentRegistrationActivity.this, LoginActivityFireBase.class);
                startActivity(log);
            }
        });
        assert regComplete != null;
        regComplete.setOnClickListener(this);

        stream_reg_array = new ArrayList<String>();
        branch_reg_array= new ArrayList<String >();
        college_reg_array =  new ArrayList<String>();
        stream_reg_spinner.setPrompt("Select Your Stream");
        stream_reg_spinner.setOnItemSelectedListener(this);
        branch_reg_spinner.setPrompt("Select You Branch");
        branch_reg_spinner.setOnItemSelectedListener(this);
        getAttStreamData();
    }
    private void registerUser(){

        final String fname = f_name.getText().toString().trim();
        final String lname = l_name.getText().toString().trim();
        final String usn = st_usn.getText().toString().toUpperCase().trim();
        final String spass = st_password.getText().toString().trim();
        final String s_email = st_email.getText().toString().trim();
        final String s_phone = st_phone.getText().toString().trim();
        final String p_phone = pa_phone.getText().toString().trim();

        //Toast.makeText(this,s_stream +" is the stream"+ s_branch + "is the branch",Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, STUDENT_REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(StudentRegistrationActivity.this,response,Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentRegistrationActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_STUDENT_USN,usn);
                params.put(KEY_STUDENT_PH, s_phone);
                params.put(KEY_STUDENT_FNAME,fname);
                params.put(KEY_STUDENT_LNAME,lname);
                params.put(KEY_PARENT_PH, p_phone);
                params.put(KEY_STUDENT_PASS,spass);
                params.put(KEY_STUDENTS_EMAIL,s_email);
                params.put(KEY_PARENT_PASS,p_phone);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == regComplete){
            //;
            startRegistrationProcess();
        }
    }

    private void startRegistrationProcess() {
        Log.d(TAG, "Registration");
        if (!validate()) {
            return;
        } else {

            regComplete.setEnabled(false);


           final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppDialog);
          progressDialog.setIndeterminate(true);
         progressDialog.setMessage("Loging in\nPlease wait..");
         progressDialog.setCancelable(false);
         progressDialog.show();

            //TODO Text Dialog
            dialog=new CustomProgressDialog(StudentRegistrationActivity.this, 1);

            dialog.show();


          /* String v_fname = f_name.getText().toString().trim();
            String v_lname = l_name.getText().toString().trim();
            String v_usn = st_usn.getText().toString().toUpperCase().trim();
            String v_spass = st_password.getText().toString().trim();
            String v_s_email = st_email.getText().toString().trim();
            String v_s_phone = st_phone.getText().toString().trim();
            String v_r_pass = re_pass.getText().toString().trim();
            String v_p_phone = pa_phone.getText().toString().trim();*/


            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onSuccess();
                            dialog.dismiss();
                            Intent back_to_login = new Intent(StudentRegistrationActivity.this, LoginActivityFireBase.class);
                            startActivity(back_to_login);
                        }
                    }, 10000);

        }
    }

    private boolean validate() {
        boolean valid = true;
       String v_fname = f_name.getText().toString().trim();
        String v_lname = l_name.getText().toString().trim();
        String v_usn = st_usn.getText().toString().toUpperCase().trim();
        String v_spass = st_password.getText().toString().trim();
        String v_s_email = st_email.getText().toString().trim();
        String v_s_phone = st_phone.getText().toString().trim();
        String v_r_pass = re_pass.getText().toString().trim();
        String v_p_phone = pa_phone.getText().toString().trim();

        //name Validity
        if(v_fname.isEmpty()){
            f_name.setError("Enter you details");
            valid = false;
        } else {
            f_name.setError(null);
        }

        if(v_lname.isEmpty()){
            l_name.setError("Enter you details");
            valid = false;
        } else {
            l_name.setError(null);
        }
        //Phone number Validity
        if(v_p_phone.isEmpty()){
            pa_phone.setError("Enter your Parent's phone number");
            valid = false;
        } else if(v_p_phone.length() != 10 ){
            pa_phone.setError("Enter a valid Mobile number with no code ('+91'/'0')");
            valid = false;
        }
        else {
            pa_phone.setError(null);
        }
        //email validation
        if(v_s_email.isEmpty()){
            st_email.setError("Enter your Email Address");
            valid = false;
        } else if(!isValidEmail(v_s_email)){
            st_email.setError("Enter a valid Email Adress");
            valid = false;
        }
        else {
            st_email.setError(null);
        }
        //Parent Phone validation
        if(v_s_phone.isEmpty()){
            st_phone.setError("Enter your phone number");
            valid = false;
        } else if(v_s_phone.length() != 10 ){
            st_phone.setError("Enter a valid Mobile number with no code ('+91'/'0')");
            valid = false;
        }
        else {
            st_phone.setError(null);
        }

//USN validation
        if (v_usn.isEmpty()) {
            st_usn.setError("Enter the Email");
            valid = false;

        } else {
            st_usn.setError(null);
        }
//Password Validation
        if (v_spass.isEmpty()) {
            st_password.setError("Enter the Password");
            valid = false;
        } else if (v_spass.length() < 8 || v_spass.length() > 16) {
            st_password.setError("Between 8 and 16 alphanumeric characters");
            valid = false;
        } else {
            st_password.setError(null);
        }
        //Password MAtch
        if (v_r_pass.isEmpty()) {
            re_pass.setError("Re-enter Password");
            valid = false;
        } else if(!v_r_pass.equals(v_spass)){
            re_pass.setError("Password doesnot match");
            valid = false;
        }
        else {
            re_pass.setError(null);
        }

        return valid;
    }

    private boolean isValidEmail(String emailInput) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailInput);
        return matcher.matches();
    }
    private void onSuccess() {
        registerUser();
        regComplete.setEnabled(true);
        this.setResult(RESULT_OK, null);

    }

    // TODO GET STREAM DATA
    private void getAttStreamData(){
        StringRequest stringRequest = new StringRequest(Config.DATA_STREAM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            reg_result = j.getJSONArray(Config.JSON_STREAM_ARRAY);
                            getAttStream(reg_result);
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

    private void getAttStream(JSONArray j){

        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                stream_reg_array.add(json.getString(Config.TAG_STREAM_NAME));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        stream_reg_spinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationActivity.this,
                android.R.layout.simple_spinner_dropdown_item, stream_reg_array));
    }

    private String getAttStreamID(int position){
        String stream_id="";
        try {
            JSONObject json = reg_result.getJSONObject(position);

            stream_id = json.getString(Config.TAG_STREAM_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stream_id;
    }


    //TODO Branches DATA
    private void getBranchData(){
        String data_url = Config.DATA_BRANCHES_URL + stream_id;
        StringRequest stringRequest = new StringRequest(data_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            reg_branches = obj.getJSONArray(Config.JSON_BRANCH_ARRAY);
                            getBranches(reg_branches);
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

    private void getBranches(JSONArray bra) {
        branch_reg_spinner.setAdapter(null);
        branch_reg_array.clear();
        for(int i=0;i<bra.length();i++){
            try {
                JSONObject json = bra.getJSONObject(i);
                branch_reg_array.add(json.getString(Config.TAG_BRANCH_NAME));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        branch_reg_spinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationActivity.this,
                android.R.layout.simple_spinner_dropdown_item, branch_reg_array));
    }

    private String getBranchID(int position){
        String branch_id="";
        try {
            JSONObject json = reg_branches.getJSONObject(position);

            branch_id = json.getString(Config.TAG_BRANCH_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return branch_id;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.registration_course:
                stream_id = getAttStreamID(position);
                getBranchData();
                break;
            case R.id.registration_branch:
                branch_id = getBranchID(position);
                break;
            case R.id.registration_college:
                //college_id = getSubjectID(i);
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



