package com.example.fb.weather.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import timber.log.Timber;

/**
 * Created by fb on 2017/5/18.
 */

public class Utils {

  public static void showShortToast(Context context, String msg){
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }

  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }


  public static int px2dip(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  public static void hideKeyboard(Activity activity) {
    InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    View v = activity.getCurrentFocus();
    if(v != null) {
      inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
  }

  public static void d(String s) {

  }

  public static void e(String s) {
    Timber.e("qc_error:" + s);
  }

}
