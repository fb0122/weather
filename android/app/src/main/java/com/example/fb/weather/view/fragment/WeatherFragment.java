package com.example.fb.weather.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.fb.weather.BaseFragment;
import com.example.fb.weather.R;
import com.example.fb.weather.ResponseListener;
import com.example.fb.weather.network.response.DailyWeatherResponse;
import com.example.fb.weather.network.response.WeatherAllResponse;
import com.example.fb.weather.presenter.WeatherPresenter;
import com.example.fb.weather.utils.Utils;
import javax.inject.Inject;

/**
 * Created by fb on 2017/5/20.
 */

public class WeatherFragment extends BaseFragment implements ResponseListener<WeatherAllResponse>, TitleFragment {


  @BindView(R.id.todayTem) TextView todayTem;
  @BindView(R.id.text_city) TextView textCity;
  @BindView(R.id.todayWea) TextView todayWea;
  @BindView(R.id.firstDayFro) TextView firstDayFro;
  @BindView(R.id.firstDayIcon) ImageView firstDayIcon;
  @BindView(R.id.firstDayTem) TextView firstDayTem;
  @BindView(R.id.firstDayWea) TextView firstDayWea;
  @BindView(R.id.secondDayFro) TextView secondDayFro;
  @BindView(R.id.secondDayIcon) ImageView secondDayIcon;
  @BindView(R.id.secondDayTem) TextView secondDayTem;
  @BindView(R.id.secondDayWea) TextView secondDayWea;
  @BindView(R.id.thirdDayFro) TextView thirdDayFro;
  @BindView(R.id.thirdDayIcon) ImageView thirdDayIcon;
  @BindView(R.id.thirdDayTem) TextView thirdDayTem;
  @BindView(R.id.thirdDayWea) TextView thirdDayWea;
  @BindView(R.id.forcastLayout) LinearLayout forcastLayout;
  @BindView(R.id.image_weather_now) ImageView imageWeatherNow;

  @Inject WeatherPresenter presenter;

  @BindView(R.id.layout_weather) LinearLayout layoutWeather;
  @BindView(R.id.loading) ProgressBar loading;
  @BindView(R.id.button_community) TextView buttonCommunity;


  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_weather, container, false);
    unbinder = ButterKnife.bind(this, view);
    delegatePresenter(presenter, this);

    getData();
    initView();

    return view;
  }

  private void initView() {
    textCity.setText("北京");
  }

  private void getData() {
    layoutWeather.setVisibility(View.INVISIBLE);
    showLoaing(loading);
    presenter.getWeatherDetail("北京");
  }

  //打开讨论页面
  @OnClick(R.id.button_community)
  public void onOpenCommunity(){

  }

  @Override public void onSuccessed(WeatherAllResponse data) {
    layoutWeather.setVisibility(View.VISIBLE);
    hideLoading(loading);

    todayTem.setText(data.now.tmp);
    todayWea.setText(data.now.cond.txt);
    imageWeatherNow.setImageResource(dealWeatherCode(data.now.cond.code));

    for (int i = 0; i < 3; i++) {
      if (data.daily_forecast.size() > 1) {
        DailyWeatherResponse daily = data.daily_forecast.get(i);
        switch (i) {
          case 0:
            firstDayFro.setText(daily.date);
            firstDayTem.setText(daily.tmp.min + "℃" + " ~ " + daily.tmp.max + "℃");
            firstDayIcon.setImageResource(dealWeatherCode(daily.cond.code_d));
            compareTwo(firstDayWea, daily.cond.txt_d, daily.cond.txt_n);
            break;
          case 1:
            secondDayFro.setText(daily.date);
            secondDayTem.setText(daily.tmp.min + "℃" + " ~ " + daily.tmp.max + "℃");
            secondDayIcon.setImageResource(dealWeatherCode(daily.cond.code_d));
            compareTwo(secondDayWea, daily.cond.txt_d, daily.cond.txt_n);
            break;
          case 2:
            thirdDayFro.setText(daily.date);
            thirdDayTem.setText(daily.tmp.min + "℃" + " ~ " + daily.tmp.max + "℃");
            thirdDayIcon.setImageResource(dealWeatherCode(daily.cond.code_d));
            compareTwo(thirdDayWea, daily.cond.txt_d, daily.cond.txt_n);
            break;
        }
      }
    }
  }

  private int dealWeatherCode(String code) {
    int drawable;
    switch (code) {
      case "100":       //晴
        drawable = R.drawable.sun;
        break;
      case "101":       //多云
        drawable = R.drawable.mulitycloudy;
        break;
      case "102":      //少云
        drawable = R.drawable.less_cloudy;
        break;
      case "103":     //晴间多云
        drawable = R.drawable.sun_cloudy;
        break;
      case "104":     //阴
        drawable = R.drawable.overcast;
        break;
      case "306":     //雨
        drawable = R.drawable.rain;
        break;
      default:
        drawable = R.drawable.sun;
        break;
    }
    return drawable;
  }

  private void compareTwo(TextView tv, String str1, String str2) {
    if (!(str1.equals(str2))) {
      tv.setText(str1 + " 转 " + str2);
    } else {
      tv.setText(str1);
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) @Override public void onFailed(String msg) {
    if (! getActivity().isDestroyed())
      Utils.showShortToast(getActivity(), msg);
  }

  @Override public String getTitle() {
    return "天气";
  }
}
