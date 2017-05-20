package com.example.fb.weather;

import android.content.Intent;

/**
 * Created by fb on 2017/5/18.
 */

public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView(ResponseListener v);

    void attachIncomingIntent (Intent intent);

    void onCreate();

    void unattachView();
}
