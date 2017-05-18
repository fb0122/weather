package com.example.fb.weather.component;

import android.app.Activity;

import com.example.fb.weather.view.MainActivity;
import com.example.fb.weather.moudle.AppModel;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.multibindings.IntoMap;

/**
 * Created by fb on 2017/5/13.
 */

@Component(modules = {AppModel.class, AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class, AppComponent.MainModule.class})
public interface AppComponent {

    @Subcomponent() public interface MainSubcomponent extends AndroidInjector<MainActivity> {
        @Subcomponent.Builder public abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        }
    }

    @Module( subcomponents = MainSubcomponent.class) abstract class MainModule {
        @Binds
        @IntoMap
        @ActivityKey(MainActivity.class)
        abstract AndroidInjector.Factory<? extends Activity> bindYourFragmentInjectorFactory(MainSubcomponent.Builder builder);
    }

}
