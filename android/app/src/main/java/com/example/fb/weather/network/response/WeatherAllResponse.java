package com.example.fb.weather.network.response;

import java.util.List;

/**
 * Created by fb on 2017/5/18.
 */

public class WeatherAllResponse {

    public AQIResponse aqi;
    public List<DailyWeatherResponse> daily_forecast;
    public WeatherNowResponse now;
    public CityResponse basic;

}
