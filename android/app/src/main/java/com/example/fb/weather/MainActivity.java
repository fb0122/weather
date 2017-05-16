package com.example.fb.weather;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SensorEventListener {

    SensorManager sensorManager = null;
    Sensor sensor = null;
//    TextView tvContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_titile)
    TextView toolbarTitile;
    @BindView(R.id.recycle_weather)
    RecyclerView recycleWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();
        initSensor();
    }

    private void setToolbar(){
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
}
