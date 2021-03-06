package com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.storeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.storeFragments.EssentialsAnimation.QuickReturn;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.EssentialListAdapter;

/**
 * Created by AIS on 17-May-17.
 */

public class booksFragment extends Fragment {
    private static final int STATE_ONSCREEN = 0;
    private static final int STATE_OFFSCREEN = 1;
    private static final int STATE_RETURNING = 2;
    private static final int STATE_EXPANDED = 3;
    private QuickReturn mListView;
    private View mHeader;
    private TextView mQuickReturnView;
    private View mPlaceHolder;
    private int mCachedVerticalScrollRange;
    private int mQuickReturnHeight;
    private int mState = STATE_ONSCREEN;
    private int mScrollY;
    private int mMinRawY = 0;
    private int rawY;
    private boolean noAnimation = false;
    private EssentialListAdapter essentialListAdapter;
    private TranslateAnimation anim;
    public View view;
    public int[] textTite = {R.drawable.demo1,R.drawable.demo2,R.drawable.demo3,R.drawable.demo4,R.drawable.demo5,R.drawable.demo6,R.drawable.demo7};

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.store_fragment_books, container, false);
        mHeader = inflater.inflate(R.layout.essentials_header, null);
        mPlaceHolder = mHeader.findViewById(R.id.placeholder);
        HorizontalScrollView quickReturnView = (HorizontalScrollView) view.findViewById(R.id.buttom_placeholder);

        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.addHeaderView(mHeader);


        essentialListAdapter = new EssentialListAdapter(textTite, getActivity().getApplicationContext());
        listView.setAdapter(essentialListAdapter);

        // All the magic it's here
        QuickReturn quickReturn = new QuickReturn(listView, quickReturnView, mPlaceHolder);
        listView.getViewTreeObserver().addOnGlobalLayoutListener(quickReturn.makeOnGlobalLayoutListener());
        listView.setOnScrollListener(quickReturn.makeOnScrollListener());
        return view;
    }
}
