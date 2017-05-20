package com.example.fb.weather.presenter;

import com.example.fb.weather.BasePresenter;
import com.example.fb.weather.Constants;
import com.example.fb.weather.ResponseListener;
import com.example.fb.weather.network.NetWorkThrowable;
import com.example.fb.weather.network.RestRepository;
import com.example.fb.weather.network.response.AQIResponse;
import com.example.fb.weather.network.response.CityResponse;
import com.example.fb.weather.network.response.DailyWeatherResponse;
import com.example.fb.weather.network.response.ResponseCommon;
import com.example.fb.weather.network.response.WeatherAllResponse;
import com.example.fb.weather.network.response.WeatherNowResponse;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by fb on 2017/5/18.
 */

public class WeatherPresenter extends BasePresenter {

  private ResponseListener<WeatherAllResponse> listener;
  @Inject RestRepository restRepository;

  @Inject public WeatherPresenter() {
  }

  @Override public void attachView(ResponseListener v) {
    listener = (ResponseListener<WeatherAllResponse>) v;
  }

  @Override public void unattachView() {
    super.unattachView();
    listener = null;
  }

  public void getWeatherDetail(String city) {
    HashMap<String, Object> params = new HashMap<>();
    params.put("city", city);
    params.put("key", Constants.key);
    RxRegiste(restRepository.getGet_api()
        .qcGetWeatherDetail(Constants.version, params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<ResponseCommon>() {
          @Override public void call(ResponseCommon responseCommon) {
            if (responseCommon.HeWeather5 != null && listener != null) {
              listener.onSuccessed(responseCommon.HeWeather5.get(0));
            } else {
              listener.onFailed(Constants.ERROR_MSG);
            }
          }
        }, new NetWorkThrowable()));
  }

  private WeatherAllResponse handlerData(ResponseCommon common) {
    JSONObject jsonObject = null;
    WeatherAllResponse weatherAllResponse = new WeatherAllResponse();
    try {
      //JSONArray jsonArray = common.getJSONArray("HeWeather5");
      weatherAllResponse.aqi = (AQIResponse) jsonObject.get("aqi");
      weatherAllResponse.basic = (CityResponse) jsonObject.get("basic");
      weatherAllResponse.daily_forecast =
          (List<DailyWeatherResponse>) jsonObject.get("daily_forecast");

      weatherAllResponse.now = (WeatherNowResponse) jsonObject.get("now");
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return weatherAllResponse;
  }
}
