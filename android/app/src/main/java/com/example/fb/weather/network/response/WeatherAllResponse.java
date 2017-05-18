package com.example.fb.weather.network.response;

import com.example.fb.weather.component.DaggerAppComponent;

/**
 * Created by fb on 2017/5/18.
 */

public class WeatherAllResponse {

    public AQIResponse aqi;
    public DailyWeatherResponse daily_forecast;
    public WeatherNowResponse now;
    public CityResponse basic;

}
