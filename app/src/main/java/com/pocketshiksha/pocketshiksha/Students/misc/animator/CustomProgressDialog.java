package com.pocketshiksha.pocketshiksha.Students.misc.animator;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pocketshiksha.pocketshiksha.R;

/**
 * Created by ais on 18/9/17.
 */

public class CustomProgressDialog extends Dialog {
    private ImageView imageview;

    public CustomProgressDialog(Context context, int resourceIdOfImage) {
        super(context, R.style.TransparentProgressDialog);
        WindowManager.LayoutParams windowmanger = getWindow().getAttributes();
        windowmanger.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(windowmanger);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
        imageview = new ImageView(context);
        imageview.setBackgroundResource(R.drawable.progress_animation);
        layout.addView(imageview, params);
        addContentView(layout, params);
    }

    @Override
    public void show() {
        super.show();
        AnimationDrawable frameAnimation = (AnimationDrawable) imageview.getBackground();
        frameAnimation.start();

    }

}