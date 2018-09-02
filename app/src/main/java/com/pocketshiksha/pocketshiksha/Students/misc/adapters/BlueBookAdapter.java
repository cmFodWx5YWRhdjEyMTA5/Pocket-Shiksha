package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pocketshiksha.pocketshiksha.Students.homeScreen.blueBookFragment.ResultsFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.blueBookFragment.TimeTableFragment;

/**
 * Created by AIS on 16-May-17.
 */

public class BlueBookAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;


    public BlueBookAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new TimeTableFragment();
            case 1:
                return new ResultsFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}