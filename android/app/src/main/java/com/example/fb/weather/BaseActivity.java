package com.example.fb.weather;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.fb.weather.component.AppComponent;
import com.example.fb.weather.component.DaggerAppComponent;
import com.example.fb.weather.moudle.AppModel;
import com.facebook.react.ReactActivity;

/**
 * Created by fb on 2017/5/14.
 */

public class BaseActivity extends ReactActivity {

    AppComponent appComponent;

    @Override
    protected String getMainComponentName() {
        return "weather";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        initIject();
    }

    public void initToolbar(Toolbar toolbar){
        toolbar.setNavigationIcon(R.drawable.ic_titlebar_back);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return false;
            }
        });
    }

    //依赖注入
    private void initIject(){
        appComponent = DaggerAppComponent.builder()
                .appModel(new AppModel())
                .build();
    }

}
