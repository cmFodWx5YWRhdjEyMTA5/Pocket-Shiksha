package com.pocketshiksha.pocketshiksha.Students.misc.animator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by AIS on 10-May-17.
 */

public class ColorFragment extends Fragment {

    private static final String KEY_COLOR = "colorfragment:color";

    /**
     * Empty constructor as per the {@link Fragment} docs
     */
    public ColorFragment() {
    }

    public static ColorFragment newInstance(int color) {
        final Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        final ColorFragment fragment = new ColorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FrameLayout rootView = new FrameLayout(getActivity());
        rootView.setBackgroundColor(getArguments().getInt(KEY_COLOR));
        return rootView;
    }

    public int getColor() {
        return getArguments().getInt(KEY_COLOR);
    }

}