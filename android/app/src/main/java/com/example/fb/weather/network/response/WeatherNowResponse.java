package com.example.fb.weather.network.response;

/**
 * Created by fb on 2017/5/18.
 */

public class WeatherNowResponse {

    public ConNowResponse cond;

    public String fl;      //体感温度
    public String hum;     //相对湿度
    public String pcpn;    //降水量
    public String pres;    //气压
    public String tmp;     //温度
    public String vis;     //能见度
    public WindResponse wind;      //风



}
