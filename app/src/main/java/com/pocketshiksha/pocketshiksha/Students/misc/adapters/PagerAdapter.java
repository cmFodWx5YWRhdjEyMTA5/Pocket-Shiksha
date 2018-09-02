package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pocketshiksha.pocketshiksha.Students.homeScreen.BlueBookFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.HomeFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.ProfileFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.StudyMaterialFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.TrackerFragment;

/**
 * Created by AIS on 05-Apr-17.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ProfileFragment();
                break;
            case 1:
                fragment = new StudyMaterialFragment();
                break;
            case 2:
                fragment = new HomeFragment();
                break;
            case 3:
                fragment = new BlueBookFragment();
                break;
            case 4:
                fragment = new TrackerFragment();
                break;

            default:
                return null;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}