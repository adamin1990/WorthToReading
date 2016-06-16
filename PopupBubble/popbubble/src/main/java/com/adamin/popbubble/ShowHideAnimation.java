package com.adamin.popbubble;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

/**
 * Created by adamlee on 2016/6/16.
 */
public class ShowHideAnimation {
    private  static final Interpolator INTERPOLATER=new FastOutSlowInInterpolator();
    private static final Interpolator INTEPOLATER_OUT=new LinearOutSlowInInterpolator();

    /**
     * 隐藏 popupbubble
     * @param view  popupbubble
     */
    public void animateOut(final RelativeLayout view){
        Animation animation= AnimationUtils.loadAnimation(view.getContext(),R.anim.anim_out);
        animation.setInterpolator(INTEPOLATER_OUT);
        animation.setDuration(250L);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animation);

    }

    /**
     * 展示 popupbubble
     * @param view
     */
    public void animateIn(RelativeLayout view){
        view.setVisibility(View.VISIBLE);
        Animation animation=AnimationUtils.loadAnimation(view.getContext(),R.anim.anim_in);
        animation.setInterpolator(INTERPOLATER);
        animation.setDuration(300L);
        view.startAnimation(animation);
    }
}
