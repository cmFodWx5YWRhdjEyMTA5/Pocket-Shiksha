package com.pocketshiksha.pocketshiksha.Server;


import java.util.SimpleTimeZone;

/**
 * Created by AIS on 20-Jul-17.
 */

public class Config {
    public static final String ip = "192.168.0.5";
    //TODO Parents Login
    public static final String KEY_PARENTS_LOGIN = "student_pid";
    public static final String KEY_PARENT_PASSWORD = "parent_password";
    public static final String PARENT_LOGIN_URL= "http://" + ip + "/pocketShiksha/parents/parent_signin.php";
    //TODO Student
    public static final String KEY_STUDENT_USN = "student_usn";
    public static final String KEY_STUDENT_FULLNAME= "student_fullname";
    public static final String KEY_STUDENT_ROLL = "student_roll";
    public static final String KEY_STUDENT_PH = "student_phone";
    public static final String KEY_STUDENT_FNAME = "student_fname";
    public static final String KEY_STUDENT_LNAME = "student_lname";
    public static final String KEY_PARENT_PH = "parent_phone";
    public static final String KEY_STUDENT_PASS = "student_password";
    public static final String KEY_PARENT_PASS = "parent_password";
    public static final String KEY_STUDENTS_EMAIL  = "student_email";
    public static final String KEY_STUDENT_ADD = "student_address";
    public static final String KEY_STUDENT_ADHAR="student_adhar";
    public static final String KEY_STUDENT_GENDER ="student_gender";
    public static final String KEY_STUDENT_DOB = "dob";
    public static final String KEY_STUDENT_FATHERS_NAME="student_fathername";
    public static final String KEY_STUDENT_MOTHER_NAME="student_mothername";

    public static final String KEY_STUDENTS_PID = "student_pid";
    public static final String KEY_STUDENT_COLLEGE ="college_id";
    public static final String KEY_STUDENT_STREAM = "stream_id";
    public static final String KEY_STUDENT_BRANCH = "branch_id";

    public static final String KEY_STUDENT_DP = "student_image";
    public static final String KEY_STUDENT_DP_NAME = "img_name";


    public static final String STUDENT_REGISTER_URL = "http://" + ip + "/pocketShiksha/students/student_registration.php";

    //TODO GETTING STUDENT DATA
    public static final String STUDENT_DATA = "http://" + ip + "/pocketShiksha/students/student_data.php?student_pid=";
    public static final String DATA_JSON_ARRAY = "result";

    //Login DATA
    public static final String LOGIN_URL = "http://" + ip + "/pocketShiksha/students/student_signin.php";
    public static final String LOGIN_JSON_ARRAY = "result_login";

    //Checking passwordword

    public static final String CHECK_PASS = "http://" + ip + "/pocketShiksha/students/student_getPass.php";

    //Update Password
    public static final String UPDATE_STUDENT_PASS = "http://" + ip + "/pocketShiksha/students/student_updateData.php?student_usn=";

    public static final String UPLOAD_DP = "http://" + ip + "/pocketShiksha/students/student_DP.php";

    public static final String GET_DP = "http://" + ip + "/pocketShiksha/students/student_getDP.php?student_pid=";



    //COLLEGE DATA
    public static final String DATA_COLLEGE_URL="http://" + ip + "/pocketShiksha/database/get_college.php";
    public static final String JSON_COLLEGE_ARRAY = "result" ;
    public static final String TAG_COLLEGE_NAME = "college_name";
    public static final String TAG_COLLEGE_ID= "college_code";
    public static final String TAG_STREAM_NAME = "stream_name";
    public static final String TAG_STREAM_ID = "stream_id";
    public static final String TAG_BRANCH_NAME = "branch_name";
    public static final String TAG_BRANCH_ID ="branch_id";
    public static final String TAG_SUBJECT_NAME = "subject_name";
    public static final String TAG_SUBJECT_ID = "subject_id";

    //STREAMS
    public static final String DATA_STREAM_URL = "http://" + ip + "/pocketShiksha/database/get_streams.php";
    public static final String JSON_STREAM_ARRAY = "result";
    //Branchers
    public static final String DATA_BRANCHES_URL = "http://" + ip + "/pocketShiksha/database/get_branchs.php?stream_id=";
    public static final String JSON_BRANCH_ARRAY = "branches";


    //TODO get GET USN numbers
    public static final String DATA_USN_URL = "http://" + ip + "/pocketShiksha/college/get_usn.php?institute_student_pid=";
    public static final String JSON_USN_ARRAY = "student_college_details";
    public static final String TAG_USN_NAME="student_usn";



    //TODO college registration form
    public static final String COLLEGE_REGISTRATION_URL="http://" + ip + "/pocketShiksha/college/student_registration.php";



}

