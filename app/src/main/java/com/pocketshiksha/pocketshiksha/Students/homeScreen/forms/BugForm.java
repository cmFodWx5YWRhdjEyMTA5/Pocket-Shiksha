package com.pocketshiksha.pocketshiksha.Students.homeScreen.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pocketshiksha.pocketshiksha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BugForm extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.cancel_bug)
    Button cancel_bug;
    @BindView(R.id.update_bug)
    Button update_bug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_bug_activity);
        ButterKnife.bind(this);
        cancel_bug.setOnClickListener(this);
        update_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Thank you for submitting the bug.\nYour contribution is precious to us", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
