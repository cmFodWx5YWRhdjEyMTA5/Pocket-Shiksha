package com.pocketshiksha.pocketshiksha.welcome.logins;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.MyBounceInterpolator;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes.ParensLoginActivity;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes.StudentLoginActivity;
import com.pocketshiksha.pocketshiksha.welcome.registration.StudentRegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginTypeSelection extends AppCompatActivity  {

//
//@BindView(R.id.text1) TextView tv1;
@BindView(R.id.text2) TextView tv2;
    @BindView(R.id.text3) TextView tv3;
    @BindView(R.id.text4) TextView tv4;
    @BindView(R.id.text5) TextView tv5;

    @BindView(R.id.signup_page) LinearLayout signup_amin;
    @BindView(R.id.student_page) LinearLayout student_anim;
    @BindView(R.id.parents_page) LinearLayout parent_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type_selection);
        ButterKnife.bind(this);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Comfortaa-Bold.ttf");

        // tv1.setTypeface(face);
        tv2.setTypeface(face);
        tv3.setTypeface(face);
        tv4.setTypeface(face);
        tv5.setTypeface(face);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        signup_amin.startAnimation(myAnim);
        student_anim.startAnimation(myAnim);
        parent_anim.startAnimation(myAnim);


        parent_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent_anim.startAnimation(myAnim);
                Intent toParentLogin = new Intent(LoginTypeSelection.this, ParensLoginActivity.class);
                startActivity(toParentLogin);
            }
        });
        student_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student_anim.startAnimation(myAnim);
                Intent toStudentLogin = new Intent(LoginTypeSelection.this, StudentLoginActivity.class);
                startActivity(toStudentLogin);
            }
        });
        signup_amin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup_amin.startAnimation(myAnim);
                Intent toStudentReg = new Intent(LoginTypeSelection.this, StudentRegistrationActivity.class);
                startActivity(toStudentReg);
            }
        });
    }


}
