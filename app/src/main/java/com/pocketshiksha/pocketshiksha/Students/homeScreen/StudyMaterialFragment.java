package com.pocketshiksha.pocketshiksha.Students.homeScreen;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.StudyMaterialAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.DepthPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudyMaterialFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.main_tab_content_studyMaterial)
    public ViewPager mViewPager;
    @BindView(R.id.tab_layout_studyMaterial)
    public  TabLayout tabStudyMaterialLayout;
    public StudyMaterialAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_studymaterial_layout, container, false);
        ButterKnife.bind(this, view);
        tabStudyMaterialLayout.addTab(tabStudyMaterialLayout.newTab().setText("Syllabus"));
        tabStudyMaterialLayout.addTab(tabStudyMaterialLayout.newTab().setText("Notes"));
        tabStudyMaterialLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabStudyMaterialLayout.setTabMode(TabLayout.FOCUSABLES_TOUCH_MODE);
        adapter = new StudyMaterialAdapter
                (getActivity().getSupportFragmentManager());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabStudyMaterialLayout));
        tabStudyMaterialLayout.setOnTabSelectedListener(this);
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
