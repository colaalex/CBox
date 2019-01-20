package com.github.colaalex.cbox.di;

import android.app.Application;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        appComponent = DaggerAppComponent.builder()
                .context(this)
                .build();
        super.onCreate();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
