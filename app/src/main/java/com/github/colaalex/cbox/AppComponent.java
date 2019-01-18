package com.github.colaalex.cbox;

import android.content.Context;

import com.github.colaalex.cbox.modules.NetworkModule;
import com.github.colaalex.cbox.presentation.create.CreateActivity;
import com.github.colaalex.cbox.presentation.main.MainActivity;
import com.github.colaalex.cbox.presentation.post.PostActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(PostActivity postActivity);
    void inject(CreateActivity createActivity);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder context(Context context);
    }
}
