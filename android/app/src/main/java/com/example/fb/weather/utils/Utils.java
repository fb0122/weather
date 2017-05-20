package com.example.fb.weather.utils;

import android.content.Context;
import android.widget.Toast;

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

}
