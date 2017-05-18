package com.example.fb.weather.presenter;

import android.net.NetworkInfo;
import com.example.fb.weather.BasePresenter;
import com.example.fb.weather.Constants;
import com.example.fb.weather.ResponseListener;
import com.example.fb.weather.network.RestRepository;
import com.example.fb.weather.network.response.ResponseCommon;
import java.util.HashMap;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by fb on 2017/5/18.
 */

public class WeatherPresenter extends BasePresenter {

  private ResponseListener<ResponseCommon> listener;
  @Inject RestRepository restRepository;

  @Inject
  public WeatherPresenter() {
  }

  @Override public void attachView(ResponseListener v) {
    listener = (ResponseListener<ResponseCommon>)v;
  }

  @Override public void unattachView() {
    super.unattachView();
    listener = null;
  }

  public void getWeatherDetail(String city) {
    HashMap<String, Object> params = new HashMap<>();
    params.put("city", city);
    params.put("key", Constants.key);
    RxRegiste(restRepository.getGet_api().qcGetWeatherDetail(params)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<ResponseCommon>() {
          @Override public void call(ResponseCommon responseCommon) {
            if (responseCommon.HeWeather5 != null && listener != null){
              listener.onSuccessed(responseCommon);
            }else{
              listener.onFailed(Constants.ERROR_MSG);
            }
          }
        }));
  }
}
