package com.example.fb.weather.moudle;

import com.example.fb.weather.network.Repository;
import com.example.fb.weather.network.RestRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModel {
    public AppModel() {
    }

    @Provides
    Repository provoideRepository(RestRepository restRepository) {
        return restRepository;
    }
}
