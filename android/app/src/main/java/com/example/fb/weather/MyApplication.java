package com.example.fb.weather;

import android.app.Activity;
import android.app.Application;

import com.example.fb.weather.component.AppComponent;
import com.example.fb.weather.component.DaggerAppComponent;
import com.example.fb.weather.moudle.AppModel;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import dagger.android.support.HasDispatchingSupportFragmentInjector;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by fb on 2017/5/15.
 */

public class MyApplication extends Application implements ReactApplication,
    HasDispatchingActivityInjector, HasDispatchingSupportFragmentInjector {

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject DispatchingAndroidInjector<android.support.v4.app.Fragment> dispatchingFragmentInjector;

    AppComponent appComponent;

    protected final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initIject();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            //Timber.plant(new Timber.());
        }
        SoLoader.init(this, /* native exopackage */ false);
    }

    private void initIject(){
        appComponent = DaggerAppComponent.builder()
            .appModel(new AppModel())
            .build();

        appComponent.inject(this);
    }

    @Override public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    @Override public DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }

}
