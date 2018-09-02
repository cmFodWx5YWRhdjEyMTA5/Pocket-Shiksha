package com.pocketshiksha.pocketshiksha.Students.homeScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.pocketshiksha.pocketshiksha.R;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.LibraryFragment;
import com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.StoreFragment;
import com.pocketshiksha.pocketshiksha.Students.misc.adapters.GridHomeAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.AndroidImageAdapter;
import com.pocketshiksha.pocketshiksha.Students.misc.animator.MyBounceInterpolator;
import com.pocketshiksha.pocketshiksha.Students.paymentGateway.PaymentGateway;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.root_layout)
    RelativeLayout root_layout;
    @BindView(R.id.NewsFeeds)
    AutoScrollViewPager mViewPager;
    @BindView(R.id.dots_indicator)
    DotsIndicator dotsIndicator;


    //TODO TEST BUTTONS
    @BindView(R.id.mainhome_grid) GridView main_grid;
    public int[] imageIDs = {R.drawable.ic_library, R.drawable.ic_events, R.drawable.ic_store, R.drawable.ic_fees};
    public String[] gridcolor ={"#1E8B95","#D84C4C","#E06919","#4CD8BF"};
    public String[] gridTitle = {"Library","Events","Store","Fees"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        ButterKnife.bind(this, view);


//TODO TEST RUN
        final Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        //to_events.startAnimation(myAnim);
        //to_fees.startAnimation(myAnim);
       // to_lib.startAnimation(myAnim);
       // to_store.startAnimation(myAnim);
        main_grid.setAdapter(new GridHomeAdapter(getActivity().getApplication(), imageIDs, gridcolor, gridTitle));
        main_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "Coming Soon", Toast.LENGTH_SHORT).show();
                      //  Intent library = new Intent(getActivity(), LibraryFragment.class);
                      //  startActivity(library);
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Events Tag", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent store = new Intent(getActivity(), StoreFragment.class);
                        startActivity(store);
                        break;
                    case 3:
                        Intent fees = new Intent(getActivity(),PaymentGateway.class);
                        startActivity(fees);
                        break;
                }
            }
        });






//END OF TEST RUN
        AndroidImageAdapter adapterView = new AndroidImageAdapter(getActivity().getApplicationContext());
        mViewPager.setAdapter(adapterView);
        dotsIndicator.setViewPager(mViewPager);
        mViewPager.startAutoScroll(8000);

        //TODO CHECH test

        return view;

    }

    @Override
    public void onClick(View v) {
        Snackbar snackbar = Snackbar
                .make(root_layout, "Nothing to Show Yet", Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

}