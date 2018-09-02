package com.pocketshiksha.pocketshiksha.Students.homeScreen.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pocketshiksha.pocketshiksha.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryForm extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.cancel_query)
    Button cancel_query;
    @BindView(R.id.update_query)
    Button update_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_query_activity);
        ButterKnife.bind(this);

        cancel_query.setOnClickListener(this);
        update_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your query has been submitted.\nCheck mail kore more updates.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
