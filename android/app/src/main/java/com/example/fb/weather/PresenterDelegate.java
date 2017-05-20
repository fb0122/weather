package com.example.fb.weather;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by fb on 2017/5/14.
 */
public class PresenterDelegate<T extends ResponseListener> implements Presenter {

    private Presenter presenter;

    public PresenterDelegate(@NonNull Presenter presenter) {
        this.presenter = presenter;
    }

    @Override public void onStart() {

    }

    @Override public void onStop() {

    }

    @Override public void onPause() {

    }

    @Override public void attachView(ResponseListener v) {
        presenter.attachView(v);
    }

    @Override public void attachIncomingIntent(Intent intent) {

    }

    @Override public void onCreate() {

    }

    @Override public void unattachView() {
        presenter.unattachView();
    }
}
