package com.example.fb.weather;

/**
 * Created by fb on 2017/5/18.
 */

public interface ResponseListener<T> {

    void onSuccessed(T data);

    void onFailed(String msg);

}
