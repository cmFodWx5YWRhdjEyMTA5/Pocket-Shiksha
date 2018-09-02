package com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.StorePageAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.DepthPageTransformer;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    public View view;
    public ViewPager mViewPager;
    public StorePageAdapter adapter;
    public TabLayout tabLayout;

    public StoreFragment() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_fragment_store);

        tabLayout = (TabLayout) findViewById(R.id.tracker_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Essentials"));
        tabLayout.addTab(tabLayout.newTab().setText("Books"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.FOCUSABLES_TOUCH_MODE);
        mViewPager = (ViewPager) findViewById(R.id.tracker_viewPager);
        adapter = new StorePageAdapter (getSupportFragmentManager());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
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
