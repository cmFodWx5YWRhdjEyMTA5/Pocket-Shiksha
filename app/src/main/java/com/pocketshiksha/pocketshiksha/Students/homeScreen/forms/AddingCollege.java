package com.pocketshiksha.pocketshiksha.Students.homeScreen.forms;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Server.Config;
import com.pocketshiksha.pocketshiksha.welcome.registration.StudentRegistrationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pocketshiksha.pocketshiksha.Server.Config.COLLEGE_REGISTRATION_URL;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_PARENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_PARENT_PH;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_EMAIL;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_FNAME;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_FULLNAME;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_LNAME;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PASS;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_PH;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENT_USN;
import static com.pocketshiksha.pocketshiksha.Server.Config.STUDENT_REGISTER_URL;
import static com.pocketshiksha.pocketshiksha.Server.Config.TAG_BRANCH_ID;
import static com.pocketshiksha.pocketshiksha.Server.Config.TAG_COLLEGE_ID;
import static com.pocketshiksha.pocketshiksha.Server.Config.TAG_STREAM_ID;

public class AddingCollege extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    @BindView(R.id.submit_reg) Button submit;
    @BindView(R.id.fullname) EditText student_fullname;
    @BindView(R.id.student_usn) EditText student_usn;
    @BindView(R.id.college_names) Spinner colleges;
    @BindView(R.id.name_stream) Spinner result_spinner;
    @BindView(R.id.names_branch) Spinner result_branch;

    //

    public ArrayList<String> college_array;
    public ArrayList<String> branch_array;
    public ArrayList<String> stream_array;
    private JSONArray college,branches,streams;
    public String stream_id, branch_id, college_id,pid;

    //Registration Elements

    private static final String TAG = "Registrationy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_college);
        ButterKnife.bind(this);
        pid = getIntent().getExtras().getString(KEY_STUDENTS_PID);
        college_array = new ArrayList<String>();
        stream_array = new ArrayList<String>();
        branch_array = new ArrayList<String >();
        result_spinner.setPrompt("Select Your Stream");
        result_spinner.setOnItemSelectedListener(this);
        result_branch.setPrompt("Select You Branch");
        result_branch.setOnItemSelectedListener(this);
        colleges.setPrompt("Select Your college");
        colleges.setOnItemSelectedListener(this);
        getCollegeData();
        getStreamData();
        submit.setOnClickListener(this);


    }

    private void getCollegeData(){
        StringRequest stringRequest = new StringRequest(Config.DATA_COLLEGE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            college = j.getJSONArray(Config.JSON_COLLEGE_ARRAY);
                            getCollege(college);
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

    private void getCollege(JSONArray j){

        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                college_array.add(json.getString(Config.TAG_COLLEGE_NAME));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        colleges.setAdapter(new ArrayAdapter<String>(AddingCollege.this,
                android.R.layout.simple_spinner_dropdown_item, college_array));
    }
    private String getCollegeName(int position){
        String college_code="";
        try {
            JSONObject json = college.getJSONObject(position);

            college_code = json.getString(TAG_COLLEGE_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return college_code;
    }
    // TODO GET STREAM DATA
    private void getStreamData(){
        StringRequest stringRequest = new StringRequest(Config.DATA_STREAM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            streams = j.getJSONArray(Config.JSON_STREAM_ARRAY);
                            getStream(streams);
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

    private void getStream(JSONArray j){

        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);
                stream_array.add(json.getString(Config.TAG_STREAM_NAME));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        result_spinner.setAdapter(new ArrayAdapter<String>(AddingCollege.this,
                android.R.layout.simple_spinner_dropdown_item, stream_array));
    }

    private String getID(int position){
        String stream_id="";
        try {
            JSONObject json = streams.getJSONObject(position);

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
                            branches = obj.getJSONArray(Config.JSON_BRANCH_ARRAY);
                            getBranches(branches);
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
        result_branch.setAdapter(null);
        branch_array.clear();
        for(int i=0;i<bra.length();i++){
            try {
                JSONObject json = bra.getJSONObject(i);
                branch_array.add(json.getString(Config.TAG_BRANCH_NAME));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        result_branch.setAdapter(new ArrayAdapter<String>(AddingCollege.this,
                android.R.layout.simple_spinner_dropdown_item, branch_array));
    }

    private String getBranchID(int position){
        String branch_id="";
        try {
            JSONObject json = branches.getJSONObject(position);

            branch_id = json.getString(TAG_BRANCH_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return branch_id;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.name_stream:
                stream_id = getID(i);
                getBranchData();
                break;
            case R.id.names_branch:
                branch_id= getBranchID(i);
                break;
            case R.id.college_names:
                college_id = getCollegeName(i);
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if(view==submit){
           // Toast.makeText(this,"Query Will be submited",Toast.LENGTH_SHORT).show();
            registerUser();
            finish();
        }
    }


    private void registerUser(){
        final String fname = student_fullname.getText().toString().trim();
        final String usn = student_usn.getText().toString().toUpperCase().trim();
        final String streams_id = stream_id;
        final String branches_id = branch_id;
        final String colleges_id = college_id;
        final String student_pid = pid ;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, COLLEGE_REGISTRATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddingCollege.this,response,Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddingCollege.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_STUDENT_FULLNAME,fname);
                params.put(KEY_STUDENT_USN,usn);
                params.put(TAG_STREAM_ID, streams_id);
                params.put(TAG_BRANCH_ID,branches_id);
                params.put(TAG_COLLEGE_ID, colleges_id);
                params.put(KEY_STUDENTS_PID,student_pid);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
