package com.example.fb.weather.network;

import rx.functions.Action1;
import timber.log.Timber;

public class NetWorkThrowable implements Action1<Throwable> {
    @Override public void call(Throwable throwable) {
        Timber.e(throwable.getMessage());
    }
}
