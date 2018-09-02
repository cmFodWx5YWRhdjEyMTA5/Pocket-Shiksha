package com.pocketshiksha.pocketshiksha.Parents;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment.MarksFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.trackerFragment.PageMarksFragment;

public class ParentMarks extends AppCompatActivity {
    public int touchX, touchY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_marks);

        GraphView graph = (GraphView) findViewById(R.id.parent_graph_marks);
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
        series.setDataPointsRadius(10);

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

        ViewPager viewPagerMarks = (ViewPager) findViewById(R.id.parent_pager);
        viewPagerMarks.setAdapter(new ParnetMarksFragmentPagerAdapter());
    }
    private class ParnetMarksFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 6;

        public ParnetMarksFragmentPagerAdapter() {
            super(getSupportFragmentManager());
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
