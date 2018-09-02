package com.pocketshiksha.pocketshiksha.Students.homeScreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.TrackerAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.DepthPageTransformer;

@SuppressLint("NewApi")

public class TrackerFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    public View view;
    public ViewPager mViewPager;
    public TrackerAdapter adapter;
    public TabLayout tabTrackerLayout;

    //TODO GRAPH TRY


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tracker_layout, container, false);



        tabTrackerLayout = (TabLayout) view.findViewById(R.id.tracker_tab_layout);
        tabTrackerLayout.addTab(tabTrackerLayout.newTab().setText("Attendance"));
        tabTrackerLayout.addTab(tabTrackerLayout.newTab().setText("Activities"));
        tabTrackerLayout.addTab(tabTrackerLayout.newTab().setText("Marks"));
        tabTrackerLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabTrackerLayout.setTabMode(TabLayout.FOCUSABLES_TOUCH_MODE);
        mViewPager = (ViewPager) view.findViewById(R.id.tracker_viewPager);
        adapter = new TrackerAdapter
                (getActivity().getSupportFragmentManager());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabTrackerLayout));
        tabTrackerLayout.setOnTabSelectedListener(this);
        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}