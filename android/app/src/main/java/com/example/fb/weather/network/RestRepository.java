package com.example.fb.weather.network;

import android.util.Log;

import com.example.fb.weather.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;

public class RestRepository implements Repository {

    private final Get_Api get_api;
    private final Post_Api post_api;
    private Action1<Throwable> doOnError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            if (throwable != null)
                Log.e("RestRepository", "retrofit:"+throwable.getMessage());
        }
    };

    @Inject
    public RestRepository() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .build();
        Gson customGsonInstance = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .create();
        Retrofit getApiAdapter = new Retrofit.Builder()
                .baseUrl(Constants.server)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Retrofit postApiAdapter = new Retrofit.Builder()
                .baseUrl(Constants.server)
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        get_api = getApiAdapter.create(Get_Api.class);
        post_api = postApiAdapter.create(Post_Api.class);
    }

    public Get_Api getGet_api() {
        return get_api;
    }

    public Post_Api getPost_api() {
        return post_api;
    }

}
