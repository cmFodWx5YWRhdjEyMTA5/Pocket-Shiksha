package com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.pocketshiksha.pocketshiksha.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    public static AttendanceFragment newInstance(int param1, String param2) {
        AttendanceFragment fragment = new AttendanceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tracker_fragment_attendance, container, false);
        GraphView graph = (GraphView) view.findViewById(R.id.graph_att);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0,0),
                new DataPoint(1, 64.5),
                new DataPoint(2, 24.6),
                new DataPoint(3, 57.6),
                new DataPoint(4, 92.7),
                new DataPoint(5, 12.9),
                new DataPoint(6, 6.1)
        });

        graph.addSeries(series);
//Adding Round to the points
       series.setDrawDataPoints(true);
       series.setDataPointsRadius(10); // set the radius of data point
        //TODO Adding on Tap for the data circles
/*
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity().getApplicationContext(), "Your Attendance: " +
                        dataPoint.getY()+"%", Toast.LENGTH_SHORT).show();
            }
        });
*/
        //Adding Prefex to X & Y axis
    graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX) + " sem";
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " %";
                }
            }
        });


        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0); // set the min value of the viewport of x axis
        graph.getViewport().setMaxX(6); // set the max value of the viewport of x-axix</p>
        // set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);  // set the min value of the viewport of y axis
        graph.getViewport().setMaxY(100);    // set the max value of the viewport of y-axis
        graph.getViewport().setScrollable(true);



        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager_at);
        viewPager.setAdapter(new SampleFragmentPagerAdapter());
        return view;
    }

    private class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 6;

        public SampleFragmentPagerAdapter() {
            super(getActivity().getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.create(position + 1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Semester " + (position + 1);
        }
    }


}
