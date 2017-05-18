package com.example.fb.weather.network;

import com.example.fb.weather.network.response.ResponseCommon;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Get_Api {

    @GET("/weather")
    rx.Observable<ResponseCommon> qcGetWeatherDetail(@QueryMap HashMap<String, Object> params);



    @GET("/search")
    rx.Observable<ResponseCommon> qcGetCity(@QueryMap HashMap<String, Object> params);
}
