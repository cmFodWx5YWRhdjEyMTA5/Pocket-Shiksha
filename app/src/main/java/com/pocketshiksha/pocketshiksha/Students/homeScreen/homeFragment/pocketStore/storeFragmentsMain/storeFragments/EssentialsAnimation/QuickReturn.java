package com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.pocketStore.storeFragmentsMain.storeFragments.EssentialsAnimation;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ListView;

public class QuickReturn {

    private final static int ANIMATION_DURATION_MILLIS = 250;

    private final ListView listView;
    private final View quickReturnView;
    private final View headerPlaceholder;

    private int itemCount;
    private int itemOffsetY[];
    private boolean isScrollComputed;
    private int calculatedHeight;
    private int quickReturnHeight;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            quickReturnHeight = quickReturnView.getHeight();
            computeScrollY();
        }
    };
    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {

        private QuickReturnState quickReturnState = QuickReturnState.ONSCREEN;
        private int rawY;
        private int minRawY;
        private boolean isAnimationRunning;

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

            int translationY = 0;
            rawY = headerPlaceholder.getTop() - Math.min(calculatedHeight - listView.getHeight(), getScrollY());

            switch (quickReturnState) {
                case OFFSCREEN:
                    if (rawY <= minRawY) {
                        minRawY = rawY;
                    } else {
                        quickReturnState = QuickReturnState.RETURNING;
                    }
                    translationY = rawY;
                    break;
                case ONSCREEN:
                    if (rawY < -quickReturnHeight) {
                        quickReturnState = QuickReturnState.OFFSCREEN;
                        minRawY = rawY;
                    }
                    translationY = rawY;
                    break;
                case RETURNING:
                    if (translationY > 0) {
                        translationY = 0;
                        minRawY = rawY - quickReturnHeight;
                    } else if (rawY > 0) {
                        quickReturnState = QuickReturnState.ONSCREEN;
                        translationY = rawY;
                    } else if (translationY < -quickReturnHeight) {
                        quickReturnState = QuickReturnState.OFFSCREEN;
                        minRawY = rawY;
                    } else if (quickReturnView.getTranslationY() != 0
                            && !isAnimationRunning) {
                        animateToExpanding();
                    }
                    break;
                case EXPANDED:
                    if (rawY < minRawY - 2 && !isAnimationRunning) {
                        animateToOffScreen();
                    } else if (translationY > 0) {
                        translationY = 0;
                        minRawY = rawY - quickReturnHeight;
                    } else if (rawY > 0) {
                        quickReturnState = QuickReturnState.ONSCREEN;
                        translationY = rawY;
                    } else if (translationY < -quickReturnHeight) {
                        quickReturnState = QuickReturnState.ONSCREEN;
                        minRawY = rawY;
                    } else {
                        minRawY = rawY;
                    }
            }

            translateViewTo(translationY);
        }

        private void animateToOffScreen() {
            TranslateAnimation anim = new TranslateAnimation(0, 0, 0, -quickReturnHeight);
            startAnimationWithListener(anim, setStateOffScreenOnEnd());
        }

        private void animateToExpanding() {
            TranslateAnimation anim = new TranslateAnimation(0, 0, -quickReturnHeight, 0);
            startAnimationWithListener(anim, setStateExpandedOnEnd());
        }

        private void startAnimationWithListener(Animation anim, Animation.AnimationListener listener) {
            isAnimationRunning = true;
            anim.setFillAfter(true);
            anim.setDuration(ANIMATION_DURATION_MILLIS);
            anim.setAnimationListener(listener);
            quickReturnView.startAnimation(anim);
        }

        private Animation.AnimationListener setStateOffScreenOnEnd() {
            return new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnimationRunning = false;
                    quickReturnState = QuickReturnState.OFFSCREEN;
                }
            };
        }

        private Animation.AnimationListener setStateExpandedOnEnd() {
            return new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isAnimationRunning = false;
                    minRawY = rawY;
                    quickReturnState = QuickReturnState.EXPANDED;
                }
            };
        }

        private void translateViewTo(int translationY) {
            /** this can be used if the build is below honeycomb **/
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
                TranslateAnimation anim = new TranslateAnimation(0, 0, translationY,
                        translationY);
                anim.setFillAfter(true);
                anim.setDuration(0);
                quickReturnView.startAnimation(anim);
            } else {
                quickReturnView.setTranslationY(translationY);
            }
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
    };

    public QuickReturn(ListView listView, View quickReturnView, View headerPlaceholder) {
        this.listView = listView;
        this.quickReturnView = quickReturnView;
        this.headerPlaceholder = headerPlaceholder;
    }

    public ViewTreeObserver.OnGlobalLayoutListener makeOnGlobalLayoutListener() {
        return onGlobalLayoutListener;
    }

    public AbsListView.OnScrollListener makeOnScrollListener() {
        return onScrollListener;
    }

    private void computeScrollY() {
        calculatedHeight = 0;
        itemCount = listView.getAdapter().getCount();
        if (itemOffsetY == null) {
            itemOffsetY = new int[itemCount];
        }
        for (int i = 0; i < itemCount; ++i) {
            View view = listView.getAdapter().getView(i, null, listView);
            view.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            itemOffsetY[i] = calculatedHeight;
            calculatedHeight += view.getMeasuredHeight();
        }
        isScrollComputed = true;
    }

    private int getScrollY() {
        if (!isScrollComputed) {
            return 0;
        }
        int pos = listView.getFirstVisiblePosition();
        View view = listView.getChildAt(0);
        int nItemY = view.getTop();
        int nScrollY = itemOffsetY[pos] - nItemY;
        return nScrollY;
    }

    private static enum QuickReturnState {
        ONSCREEN,
        OFFSCREEN,
        RETURNING,
        EXPANDED
    }

}