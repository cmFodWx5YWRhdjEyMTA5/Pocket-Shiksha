package com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class MarksFragment extends Fragment {

    public int touchX, touchY;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tracker_fragment_results, container, false);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
                    touchY= (int)event.getY();
                    touchX= (int)event.getX();
                }
                return true;
            }
        });


        GraphView graph = (GraphView) view.findViewById(R.id.graph_marks);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 95.3),
                new DataPoint(2, 83.7),
                new DataPoint(3, 61.5),
                new DataPoint(4, 85.5),
                new DataPoint(5, 71.8),
                new DataPoint(6, 85.9)
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

                Toast toast= Toast.makeText(getActivity().getApplicationContext(), "You Marks: " +
                        dataPoint.getY()+ "%", Toast.LENGTH_SHORT);
                toast.setGravity(0, touchX, touchY);
                toast.show();
            }
        });*/

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

        ViewPager viewPagerMarks = (ViewPager) view.findViewById(R.id.pager);
        viewPagerMarks.setAdapter(new MarksFragmentPagerAdapter());
        return view;
    }




    private class MarksFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 6;

        public MarksFragmentPagerAdapter() {
            super(getActivity().getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return PageMarksFragment.create(position + 1);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "Semester " + (position + 1);
        }
    }


}