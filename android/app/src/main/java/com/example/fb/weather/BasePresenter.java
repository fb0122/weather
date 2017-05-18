package com.example.fb.weather;

import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.example.fb.weather.event.RxBus;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;

public class BasePresenter implements Presenter {

    private List<Subscription> sps = new ArrayList<>();
    private List<Pair<String, Observable>> observables  = new ArrayList<>();


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(ResponseListener v) {

    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void onCreate() {

    }

    @CallSuper
    @Override
    public void unattachView() {
        for (int i = 0; i < sps.size(); i++) {
            sps.get(i).unsubscribe();
        }
        for (int i = 0; i < observables.size(); i++) {
            RxBus.getBus().unregister(observables.get(i).first,observables.get(i).second);
        }
    }


    public Subscription RxRegiste(Subscription subscription) {
        sps.add(subscription);
        return subscription;
    }

    public <T> Observable<T> RxBusAdd(@NonNull Class<T> clazz){
        Observable ob = RxBus.getBus().register(clazz);
        observables.add(new Pair<String, Observable>(clazz.getName(),ob));
        return ob;
    }


}
