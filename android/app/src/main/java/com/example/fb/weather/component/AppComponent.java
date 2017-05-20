package com.example.fb.weather.component;

import android.app.Activity;

import android.support.v4.app.Fragment;
import com.example.fb.weather.MyApplication;
import com.example.fb.weather.view.activity.MainActivity;
import com.example.fb.weather.moudle.AppModel;

import com.example.fb.weather.view.fragment.WeatherFragment;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * Created by fb on 2017/5/13.
 */

@Component(modules = {
    AppModel.class, AndroidInjectionModule.class, AndroidSupportInjectionModule.class,
    AppComponent.MainModule.class, AppComponent.WeatherFragmentModule.class
}) public interface AppComponent {

  @Subcomponent() public interface MainSubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder public abstract class Builder
        extends AndroidInjector.Builder<MainActivity> {
    }
  }

  @Module(subcomponents = MainSubcomponent.class) abstract class MainModule {
    @Binds @IntoMap @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindYourFragmentInjectorFactory(
        MainSubcomponent.Builder builder);
  }

  @Subcomponent() public interface WeatherFragmentSubcomponent
      extends AndroidInjector<WeatherFragment> {
    @Subcomponent.Builder public abstract class Builder
        extends AndroidInjector.Builder<WeatherFragment> {
    }
  }

  @Module(subcomponents = WeatherFragmentSubcomponent.class) abstract class WeatherFragmentModule {
    @Binds @IntoMap @FragmentKey(WeatherFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindYourFragmentInjectorFactory(
        WeatherFragmentSubcomponent.Builder builder);
  }

  void inject(MyApplication activity);
}
