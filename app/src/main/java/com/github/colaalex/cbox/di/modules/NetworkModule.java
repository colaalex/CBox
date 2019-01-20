package com.github.colaalex.cbox.di.modules;

import com.github.colaalex.cbox.data.api.PostApi;
import com.github.colaalex.cbox.data.repository.Repository;
import com.github.colaalex.cbox.domain.repository.IPostRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String URL = "https://jsonplaceholder.typicode.com/";

    @Provides
    Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    IPostRepository provideIPostRepository() {
        return new Repository(retrofit().create(PostApi.class));
    }

}
