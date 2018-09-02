package com.pocketshiksha.pocketshiksha.welcome.logins.LoginAdapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes.ParentsLogin;
import com.pocketshiksha.pocketshiksha.welcome.logins.LoginTypes.StudentLogin;

/**
 * Created by AIS on 29-Jun-17.
 */

public class LoginAdapter extends FragmentPagerAdapter {

    public LoginAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {

            case 0:
                return new StudentLogin();
            case 1:
                return new ParentsLogin();
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