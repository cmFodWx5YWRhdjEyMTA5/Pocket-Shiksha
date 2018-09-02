package com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.ActivityCertificateAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.ActivityFestAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pocketshiksha.pocketshiksha.Students.misc.adapters.Constants.FIRST_COLUMN;
import static com.pocketshiksha.pocketshiksha.Students.misc.adapters.Constants.SECOND_COLUMN;
import static com.pocketshiksha.pocketshiksha.Students.misc.adapters.Constants.THIRD_COLUMN;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fest_List) ListView fest_List;
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    ActivityFestAdapter activityFestAdapter;

    @BindView(R.id.ActivityCertif) ListView activity_cert;
    private ArrayList<HashMap<String, String>> certList = new ArrayList<HashMap<String, String>>();
    ActivityCertificateAdapter activityCertificateAdapter;
    //Fest
    @BindView(R.id.fest_text) TextView fest_text;
    @BindView(R.id.fest_body) LinearLayout fest_body;
    //Certificate
    @BindView(R.id.cert_text) TextView cert_text;
    //Workshop
    @BindView(R.id.workshop_text) TextView workshop_text;
    @BindView(R.id.workshop_body) RelativeLayout workshop_body;
    //Sport
    @BindView(R.id.sport_text) TextView sport_text;
    @BindView(R.id.sport_body) RelativeLayout sport_body;
    private Animation animShow, animHide;

    public static ActivityFragment newInstance(int param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.tracker_fragment_activity, container, false);
        ButterKnife.bind(this,view);
        animShow = AnimationUtils.loadAnimation( getActivity(), R.anim.view_show);
        animHide = AnimationUtils.loadAnimation( getActivity(), R.anim.view_hide);
        //fest_text.setOnClickListener(this);
      //  cert_text.setOnClickListener(this);
      //  workshop_text.setOnClickListener(this);
      //  sport_text.setOnClickListener(this);
        festList();
        certList();

        return view;
    }

   public void festList(){
       HashMap<String,String> temp=new HashMap<String, String>();
       temp.put(FIRST_COLUMN, "First Year");
       temp.put(SECOND_COLUMN, "Habba '16");
       temp.put(THIRD_COLUMN, "Group Dance");
       list.add(temp);

       HashMap<String,String> temp2=new HashMap<String, String>();
       temp2.put(FIRST_COLUMN, "Thirty Year");
       temp2.put(SECOND_COLUMN, "Pheonix '18");
       temp2.put(THIRD_COLUMN, "MAD Ads");
       list.add(temp2);
       activityFestAdapter = new ActivityFestAdapter(getActivity(),list);
       fest_List.setAdapter(activityFestAdapter);
       setListViewHeightBasedOnChildren(fest_List);
   }
   public void certList(){
       HashMap<String,String> cert1=new HashMap<String, String>();
       cert1.put(FIRST_COLUMN, "First Year");
       cert1.put(SECOND_COLUMN, "Habba '16");
       certList.add(cert1);

       HashMap<String,String> cert2=new HashMap<String, String>();
       cert2.put(FIRST_COLUMN, "Thirty Year");
       cert2.put(SECOND_COLUMN, "Pheonix '18");
       certList.add(cert2);
       activityCertificateAdapter = new ActivityCertificateAdapter(getActivity(),certList);
       activity_cert.setAdapter(activityCertificateAdapter);
       setListViewHeightBasedOnChildren(activity_cert);

   }

    @Override
    public void onClick(View v) {
        if(v == fest_text){
            if(fest_body.getVisibility() == View.VISIBLE)
            {
                fest_body.startAnimation( animHide );
                fest_body.setVisibility(View.GONE);
            }
            else {

                fest_body.startAnimation( animShow );
                fest_body.setVisibility(View.VISIBLE);
            }
        }
        if(v == sport_text){
            if(sport_body.getVisibility() == View.VISIBLE)
            {
                sport_body.startAnimation( animHide );
                sport_body.setVisibility(View.GONE);
            }
            else {

                sport_body.startAnimation( animShow );
                sport_body.setVisibility(View.VISIBLE);
            }
        }
        if(v == workshop_text){
            if(workshop_body.getVisibility() == View.VISIBLE)
            {
                workshop_body.startAnimation( animHide );
                workshop_body.setVisibility(View.GONE);
            }
            else {

                workshop_body.startAnimation( animShow );
                workshop_body.setVisibility(View.VISIBLE);
            }
        }
    }
//Gives Full length to Listview inside a scrollview
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
