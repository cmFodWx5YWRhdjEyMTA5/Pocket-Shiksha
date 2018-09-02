package com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pocketshiksha.pocketshiksha.Parents.ParentsMainActivity;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.MyBounceInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParensLoginActivity extends Activity {
    @BindView(R.id.login_title) TextView login_title;
    @BindView(R.id.sigInParent) TextView sigIn;
    @BindView(R.id.content_place) RelativeLayout content_place;
    @BindView(R.id.user_profile_photo) ImageView user_profile_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_parens_login);
        ButterKnife.bind(this);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Comfortaa-Bold.ttf");
        sigIn.setTypeface(face);


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.1, 10);
        myAnim.setInterpolator(interpolator);
        content_place.startAnimation(myAnim);
        user_profile_photo.startAnimation(myAnim);
        sigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startparent = new Intent(ParensLoginActivity.this,ParentsMainActivity.class);
                startActivity(startparent);
            }
        });
    }
}
