package com.pocketshiksha.pocketshiksha.Students.homeScreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Server.Config;
import com.pocketshiksha.pocketshiksha.Students.editing.EditAddress;
import com.pocketshiksha.pocketshiksha.Students.editing.EditEmail;
import com.pocketshiksha.pocketshiksha.Students.editing.EditError;
import com.pocketshiksha.pocketshiksha.Students.editing.EditPassword;
import com.pocketshiksha.pocketshiksha.Students.editing.EditPhoneNumber;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.forms.AddingCollege;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.pocketshiksha.pocketshiksha.Server.Config.KEY_STUDENTS_PID;
import static com.pocketshiksha.pocketshiksha.Server.Config.UPLOAD_DP;

/**
 * Created by adilsoomro on 8/19/16.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    public static final String KEY_STUDENT_USERNAME = Config.KEY_STUDENTS_PID;
    public static final String KEY_STUDENT_PASSWORD = Config.KEY_STUDENT_PASS;
    View.OnClickListener OnErrorClick;
    public String pid,firstname,branch_name,stream_name,college_name,lastname, father_name, mother_name, dob, ph, email, add, user_pass, image_url,college,stream,branch;
    TextView user_profile_name, father, mother, date_ob, user_ph, user_email, user_add, user_password,col_name,be_name,st_name;
    ActionMode mActionMode;
    int PICK_IMAGE_REQUEST = 1;
    @BindView(R.id.edit_add) RelativeLayout edit_add;
    @BindView(R.id.edit_baap) RelativeLayout edit_baap;
    @BindView(R.id.edit_maki) RelativeLayout edit_maki;
    @BindView(R.id.edit_email) RelativeLayout edit_email;
    @BindView(R.id.edit_ph) RelativeLayout edit_ph;
    @BindView(R.id.edit_dob)  RelativeLayout edit_dob;
    @BindView(R.id.user_profile_photo) CircleImageView user_profile_photo;
    @BindView(R.id.mainView) RelativeLayout mainView;
    //USN
   // @BindView(R.id.user_profile_USN) Spinner user_profile_USN;
   // public ArrayList<String> USN_array;
    //private JSONArray student_college_usn;

    public static final int Galary_count =1;
    private String KEY_STUDENT_IMAGE = Config.KEY_STUDENT_DP;

    private Bitmap bitmap;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_change_dp, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.remove_dp:
                    Toast.makeText(getActivity().getApplicationContext(), "Does nothing", Toast.LENGTH_SHORT).show();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.upload_new:
                    showFileChooser();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);


        OnErrorClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ee = new Intent(getActivity().getApplicationContext(), EditError.class);
                startActivity(ee);
            }
        };

        ButterKnife.bind(this, view);

        //USN_array=new ArrayList<String>();

       // user_password = (TextView) view.findViewById(R.id.user_password);
        user_profile_name = (TextView) view.findViewById(R.id.user_profile_name);
        father = (TextView) view.findViewById(R.id.father_name);
        mother = (TextView) view.findViewById(R.id.mother_name);
        date_ob = (TextView) view.findViewById(R.id.dob);
        user_ph = (TextView) view.findViewById(R.id.ph);
        user_email = (TextView) view.findViewById(R.id.email);
        user_add = (TextView) view.findViewById(R.id.add);
        col_name =(TextView) view.findViewById(R.id.college_name);
        be_name = (TextView) view.findViewById(R.id.Branch_name);
        st_name = (TextView) view.findViewById(R.id.stream_name);

        user_profile_photo.setImageResource(R.drawable.ic_dafault_male);
        //TODO REMOVE THIS TAG

        pid = getActivity().getIntent().getExtras().getString(KEY_STUDENTS_PID);
        String get_data = Config.STUDENT_DATA + pid;
        StringRequest stringRequest = new StringRequest(get_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(Config.DATA_JSON_ARRAY);
                    JSONObject studentData = result.getJSONObject(0);
                    firstname = studentData.getString(Config.KEY_STUDENT_FNAME);
                    lastname = studentData.getString(Config.KEY_STUDENT_LNAME);
                  //  college = studentData.getString(Config.KEY_STUDENT_COLLEGE);
                //    stream=studentData.getString(Config.KEY_STUDENT_STREAM);
                //    branch=studentData.getString(Config.KEY_STUDENT_BRANCH);
                 //   college_name = college.replaceAll("([A-Z])", " $1");
                //    stream_name = stream.replaceAll("([A-Z])", " $1");
                //    branch_name = branch.replaceAll("([A-Z])", " $1");

                    father_name = studentData.getString(Config.KEY_STUDENT_FATHERS_NAME);
                    mother_name = studentData.getString(Config.KEY_STUDENT_MOTHER_NAME);
                    dob = studentData.getString(Config.KEY_STUDENT_DOB);
                    ph = studentData.getString(Config.KEY_STUDENT_PH);
                    email = studentData.getString(Config.KEY_STUDENTS_EMAIL);
                    add = studentData.getString(Config.KEY_STUDENT_ADD);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StudentDPString();

                user_profile_name.setText(firstname + " " + lastname);
              //  col_name.setText(college_name);
               // be_name.setText(branch_name);
              //  st_name.setText(stream_name);
                // user_profile_USN.setText(pid);
                father.setText(father_name);
                mother.setText(mother_name);
                date_ob.setText(dob);
                user_ph.setText(ph);
                user_email.setText(email);
                user_add.setText(add);
                //user_password.setText(user_pass);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

        //TODO REMOVE THIS TAG


        //TODO Password edit
       // pass_edit.setOnClickListener(this);
       // pass_edit.setOnLongClickListener(new View.OnLongClickListener() {
        //    @Override
        //    public boolean onLongClick(View v) {
      //          Intent cp = new Intent(getActivity().getApplicationContext(), EditPassword.class);
      //          cp.putExtra(KEY_STUDENT_USERNAME, pid);
       //         cp.putExtra(KEY_STUDENT_PASSWORD, user_pass);
       //         startActivity(cp);
       //         return true;
      //      }
      //  });
        //TODO Edit Address
        edit_add.setOnClickListener(this);
        edit_add.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent ea = new Intent(getActivity().getApplicationContext(), EditAddress.class);
                startActivity(ea);
                return false;
            }
        });
        //TODO Edit Phone number
        edit_ph.setOnClickListener(this);
        edit_ph.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent cp = new Intent(getActivity().getApplicationContext(), EditPhoneNumber.class);
                startActivity(cp);
                return true;
            }
        });
        //TODO edit email address
        edit_email.setOnClickListener(this);
        edit_email.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent ee = new Intent(getActivity().getApplicationContext(), EditEmail.class);
                startActivity(ee);
                return true;
            }
        });
        //TODO edit profile picture
        user_profile_photo.setOnClickListener(this);
        user_profile_photo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Intent epp = new Intent(getActivity().getApplicationContext(), ProfileChanger.class );
                // startActivity(epp);
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = getActivity().startActionMode(mActionModeCallback);
                view.setSelected(true);


                return true;
            }
        });

        //TODO contents That can not be edited
        edit_baap.setOnClickListener(this);
        edit_baap.setOnLongClickListener(this);

        edit_maki.setOnClickListener(this);
        edit_maki.setOnLongClickListener(this);

      //  edit_usn.setOnClickListener(this);
       // edit_usn.setOnLongClickListener(this);

        edit_dob.setOnClickListener(this);
        edit_dob.setOnLongClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity().getApplicationContext(), "Long Click to edit details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View v) {
        Snackbar.make(mainView, "You are not authorized to edit this",
                Snackbar.LENGTH_LONG)
                .setAction("More", OnErrorClick)
                .show();
        return true;
    }

    //TODO IMAGE

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,Galary_count);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                uploadImage();
                user_profile_photo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {

        final ProgressDialog loading = ProgressDialog.show(getActivity()
                , "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_DP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();
                        //Showing toast
                        Toast.makeText(getActivity().getApplicationContext(),
                                volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);
                String name = pid.trim() + "_dp";
                Map<String, String> params = new Hashtable<String, String>();
                params.put(KEY_STUDENT_USERNAME, pid);
                params.put(KEY_STUDENT_IMAGE, image);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
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
                    RequestQueue mRequestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    ImageRequest imageRequest = new ImageRequest(image_url, new BitmapListener(), 0, 0, null, new MyErrorListener());
                    mRequestQueue.add(imageRequest);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Something Is very Wrong", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private class BitmapListener implements Response.Listener<Bitmap> {
        @Override
        public void onResponse(Bitmap response) {
            user_profile_photo.setImageBitmap(response);

        }
    }

    private class MyErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            user_profile_photo.setImageResource(R.drawable.ic_default_profile);
        }
    }


}
