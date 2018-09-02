package com.pocketshiksha.pocketshiksha.Students.misc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.storeFragments.booksFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.storeFragments.essentialsFragment;

/**
 * Created by AIS on 17-May-17.
 */

public class StorePageAdapter extends FragmentPagerAdapter {

    public StorePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new essentialsFragment();
            case 1:
                return new booksFragment();
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

