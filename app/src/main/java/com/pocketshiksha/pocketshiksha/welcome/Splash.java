package com.pocketshiksha.pocketshiksha.welcome;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.welcome.Intro.WelcomeActivity;

public class Splash extends AppCompatActivity {
    private static final float BYTES_PX = 4.0f;
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    ImageView logo_disp, bg, gyroView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_splash);


        logo_disp = (ImageView) findViewById(R.id.logo_disp);
        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_anim);
        gyroView = (ImageView) findViewById(R.id.loading_animation);
        gyroView.setBackgroundResource(R.drawable.loading_animation);


        AnimationDrawable gyroAnimation = (AnimationDrawable) gyroView.getBackground();
        gyroAnimation.start();
        // logo_disp.startAnimation(startAnimation);





        


/* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this, WelcomeActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }



/*
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    } */

}
