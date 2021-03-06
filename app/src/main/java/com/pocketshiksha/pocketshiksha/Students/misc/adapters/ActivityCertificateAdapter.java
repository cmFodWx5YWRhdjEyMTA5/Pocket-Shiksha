package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pocketshiksha.pocketshiksha.R;

import java.util.ArrayList;
import java.util.HashMap;

import static com.pocketshiksha.pocketshiksha.Students.misc.adapters.Constants.FIRST_COLUMN;
import static com.pocketshiksha.pocketshiksha.Students.misc.adapters.Constants.SECOND_COLUMN;
import static com.pocketshiksha.pocketshiksha.Students.misc.adapters.Constants.THIRD_COLUMN;

/**
 * Created by ais on 22/8/17.
 */

public class ActivityCertificateAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtCert;
    TextView txtInst;
    Button viewCert;
    public ActivityCertificateAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.activity_certif, null);

            txtCert=(TextView) convertView.findViewById(R.id.cert_name);
            txtInst=(TextView) convertView.findViewById(R.id.int_name);
            viewCert = (Button) convertView.findViewById(R.id.view_cert);

        }

        HashMap<String, String> map=list.get(position);
        txtCert.setText(map.get(FIRST_COLUMN));
        txtInst.setText(map.get(SECOND_COLUMN));

        return convertView;
    }

}