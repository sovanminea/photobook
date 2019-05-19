package me.sovanminea.photobook.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;

public final class ViewHelper {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void clear(View v) {
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
//        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        v.setPivotY(v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null);
    }
}