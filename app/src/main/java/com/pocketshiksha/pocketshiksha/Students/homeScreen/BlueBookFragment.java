package com.pocketshiksha.pocketshiksha.Students.homeScreen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.BlueBookAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.DepthPageTransformer;

public class BlueBookFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    public View view;
    public ViewPager mViewPager;
    public TabLayout tableBlueBookLayout;
    public BlueBookAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blue_book_layout, container, false);
        tableBlueBookLayout = (TabLayout) view.findViewById(R.id.bluebook_tab_layout);
        tableBlueBookLayout.addTab(tableBlueBookLayout.newTab().setText("Time Table"));
        tableBlueBookLayout.addTab(tableBlueBookLayout.newTab().setText("Results"));
        tableBlueBookLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tableBlueBookLayout.setTabMode(TabLayout.FOCUSABLES_TOUCH_MODE);
        mViewPager = (ViewPager) view.findViewById(R.id.blueBook_viewPager);
        adapter = new BlueBookAdapter
                (getActivity().getSupportFragmentManager(), tableBlueBookLayout.getTabCount());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tableBlueBookLayout));
        tableBlueBookLayout.setOnTabSelectedListener(this);
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
