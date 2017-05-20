package com.example.fb.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.widget.ProgressBar;
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

    public void showLoaing(ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
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

    public void startShare(Context context){
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "title");//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, "shareContent");//添加分享内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "分享");
        context.startActivity(share_intent);
    }

    //依赖注入
    private void initIject(){
        appComponent = DaggerAppComponent.builder()
                .appModel(new AppModel())
                .build();
    }

}
