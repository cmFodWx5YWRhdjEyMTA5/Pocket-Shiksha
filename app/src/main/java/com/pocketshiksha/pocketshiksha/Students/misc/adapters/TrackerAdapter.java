package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment.ActivityFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment.AttendanceFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment.MarksFragment;

/**
 * Created by AIS on 16-May-17.
 */

public class TrackerAdapter extends FragmentPagerAdapter {

    public TrackerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {

            case 0:
                return new AttendanceFragment();
            case 1:
                return new ActivityFragment();
            case 2:
                return new MarksFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}