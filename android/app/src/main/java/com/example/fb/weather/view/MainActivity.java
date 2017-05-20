package com.example.fb.weather.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.fb.weather.BaseActivity;
import com.example.fb.weather.R;
import com.example.fb.weather.ResponseListener;
import com.example.fb.weather.network.response.DailyWeatherResponse;
import com.example.fb.weather.network.response.ResponseCommon;
import com.example.fb.weather.presenter.WeatherPresenter;
import com.example.fb.weather.utils.Utils;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements SensorEventListener, ResponseListener<ResponseCommon> {

  SensorManager sensorManager = null;
  Sensor sensor = null;
  //    TextView tvContent;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.toolbar_titile) TextView toolbarTitile;
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
  @BindView(R.id.toolbar_share) ImageView toolbarShare;
  @BindView(R.id.button_community) TextView buttonCommunity;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initView();
    getData();
    setToolbar();
    initSensor();
  }

  private void initView() {
    textCity.setText("北京");
  }

  private void getData() {
    layoutWeather.setVisibility(View.INVISIBLE);
    showLoaing(loading);
    presenter.getWeatherDetail("北京");
  }

  private void setToolbar() {
    initToolbar(toolbar);
    toolbarTitile.setText("天气");
    toolbarTitile.setTextColor(getResources().getColor(R.color.white));
    toolbarShare.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startShare(MainActivity.this);          //打开分享页面
      }
    });
  }

  private void initSensor() {
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
  }

  @Override protected void onResume() {
    super.onResume();
    sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
  }

  @Override protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this, sensor);
  }

  @Override public void onSensorChanged(SensorEvent event) {

    float[] value = event.values;

    if (Math.abs(value[2]) > 1) {
      //            tvContent.setTextScaleX(value[2]);
      Log.e("---fb---x---", value[0] + "");
      Log.e("---fb---y---", value[1] + "");
      Log.e("---fb---z---", value[2] + "");
    }
  }

  @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
    Log.e("---fb---other---", (sensor + "") + accuracy + "");
  }

  //打开讨论页面
  @OnClick(R.id.button_community)
  public void onOpenCommunity(){

  }

  @Override public void onSuccessed(ResponseCommon data) {
    layoutWeather.setVisibility(View.VISIBLE);
    hideLoading(loading);

    todayTem.setText(data.HeWeather5.now.tmp);
    todayWea.setText(data.HeWeather5.now.cond.txt);
    imageWeatherNow.setImageResource(dealWeatherCode(data.HeWeather5.now.cond.code));

    for (int i = 0; i < 3; i++) {
      if (data.HeWeather5.daily_forecast.size() > 1) {
        DailyWeatherResponse daily = data.HeWeather5.daily_forecast.get(i);
        switch (i) {
          case 0:
            firstDayFro.setText(daily.date);
            firstDayTem.setText(daily.tmp.min + "℃" + " ~ " + daily.tmp.max + "℃");
            firstDayIcon.setImageResource(dealWeatherCode(daily.cond.con_d));
            compareTwo(firstDayWea, daily.cond.txt_d, daily.cond.txt_n);
            break;
          case 1:
            secondDayFro.setText(daily.date);
            secondDayTem.setText(daily.tmp.min + "℃" + " ~ " + daily.tmp.max + "℃");
            secondDayIcon.setImageResource(dealWeatherCode(daily.cond.con_d));
            compareTwo(secondDayWea, daily.cond.txt_d, daily.cond.txt_n);
            break;
          case 2:
            thirdDayFro.setText(daily.date);
            thirdDayTem.setText(daily.tmp.min + "℃" + " ~ " + daily.tmp.max + "℃");
            thirdDayIcon.setImageResource(dealWeatherCode(daily.cond.con_d));
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

  @Override public void onFailed(String msg) {
    Utils.showShortToast(getApplicationContext(), msg);
  }
}
