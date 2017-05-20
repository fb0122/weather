package com.example.fb.weather.view.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.fb.weather.BaseActivity;
import com.example.fb.weather.R;
import com.example.fb.weather.view.fragment.WeatherFragment;

public class MainActivity extends BaseActivity implements SensorEventListener {

  SensorManager sensorManager = null;
  Sensor sensor = null;
  @BindView(R.id.frag_main) FrameLayout fragMain;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    initView();
    initSensor();
  }

  private void initView(){
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.frag_main, new WeatherFragment())
        .commit();
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
      //Log.e("---fb---x---", value[0] + "");
      //Log.e("---fb---y---", value[1] + "");
      //Log.e("---fb---z---", value[2] + "");
    }
  }

  @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {
    //Log.e("---fb---other---", (sensor + "") + accuracy + "");
  }
}
