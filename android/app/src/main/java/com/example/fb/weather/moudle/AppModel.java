package com.example.fb.weather.moudle;

import com.example.fb.weather.network.Repository;
import com.example.fb.weather.network.RestRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fb on 2017/5/18.
 */

@Module
public class AppModel {
    public AppModel() {
    }

    @Provides
    Repository provoideRepository(RestRepository restRepository) {
        return restRepository;
    }
}
