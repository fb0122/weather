package com.example.fb.weather;

import android.content.Intent;

public interface Presenter {
    void onStart();

    void onStop();

    void onPause();

    void attachView(ResponseListener v);

    void attachIncomingIntent (Intent intent);

    void onCreate();

    void unattachView();
}
