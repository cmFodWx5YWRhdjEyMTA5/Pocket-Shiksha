package com.pocketshiksha.pocketshiksha.Students.homeScreen.studeMaterialFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pocketshiksha.pocketshiksha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class notesFaragment extends Fragment {


    public notesFaragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.notes_faragment, container, false);
    }

}
