package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pocketshiksha.pocketshiksha.Students.homeScreen.studeMaterialFragment.notesFaragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.studeMaterialFragment.syllabusFragment;

/**
 * Created by AIS on 29-May-17.
 */

public class StudyMaterialAdapter extends FragmentPagerAdapter {

    public StudyMaterialAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new syllabusFragment();
            case 1:
                return new notesFaragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

