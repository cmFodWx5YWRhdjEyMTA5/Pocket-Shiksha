package com.pocketshiksha.pocketshiksha.Students.homeScreen.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pocketshiksha.pocketshiksha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IssueForm extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.cancel_issue)
    Button cancel_issue;
    @BindView(R.id.update_issue)
    Button update_issue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_issue_activity);
        ButterKnife.bind(this);
        cancel_issue.setOnClickListener(this);
        update_issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Your issue has been submitted.\nOur repose team will contact you soon", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
