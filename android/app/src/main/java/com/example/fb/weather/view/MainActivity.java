package com.example.fb.weather.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fb.weather.BaseActivity;
import com.example.fb.weather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.fb.weather.ResponseListener;
import com.example.fb.weather.network.response.ResponseCommon;
import com.example.fb.weather.presenter.WeatherPresenter;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements SensorEventListener, ResponseListener<ResponseCommon> {

    SensorManager sensorManager = null;
    Sensor sensor = null;
    //    TextView tvContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_titile)
    TextView toolbarTitile;
    @BindView(R.id.todayTem)
    TextView todayTem;
    @BindView(R.id.text_city)
    TextView textCity;
    @BindView(R.id.todayWea)
    TextView todayWea;
    @BindView(R.id.firstDayFro)
    TextView firstDayFro;
    @BindView(R.id.firstDayIcon)
    ImageView firstDayIcon;
    @BindView(R.id.firstDayTem)
    TextView firstDayTem;
    @BindView(R.id.firstDayWea)
    TextView firstDayWea;
    @BindView(R.id.secondDayFro)
    TextView secondDayFro;
    @BindView(R.id.secondDayIcon)
    ImageView secondDayIcon;
    @BindView(R.id.secondDayTem)
    TextView secondDayTem;
    @BindView(R.id.secondDayWea)
    TextView secondDayWea;
    @BindView(R.id.thirdDayFro)
    TextView thirdDayFro;
    @BindView(R.id.thirdDayIcon)
    ImageView thirdDayIcon;
    @BindView(R.id.thirdDayTem)
    TextView thirdDayTem;
    @BindView(R.id.thirdDayWea)
    TextView thirdDayWea;
    @BindView(R.id.forcastLayout)
    LinearLayout forcastLayout;

    @Inject WeatherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();
        initSensor();
    }

    private void getData(){
        presenter.getWeatherDetail("北京");
    }

    private void setToolbar() {
        initToolbar(toolbar);
        toolbarTitile.setText("天气");
        toolbarTitile.setTextColor(getResources().getColor(R.color.white));
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, sensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] value = event.values;


        if (Math.abs(value[2]) > 1) {
//            tvContent.setTextScaleX(value[2]);
            Log.e("---fb---x---", value[0] + "");
            Log.e("---fb---y---", value[1] + "");
            Log.e("---fb---z---", value[2] + "");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e("---fb---other---", (sensor + "") + accuracy + "");
    }

    @Override public void onSuccessed(ResponseCommon data) {

    }

    @Override public void onFailed(String msg) {

    }
}
