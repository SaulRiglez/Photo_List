package com.yoprogramo.photoviewer.dagger.modules;


import com.yoprogramo.photoviewer.utilities.RetrofitHelper;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by User on 3/24/2017.
 */
@Module
public class Utilitiesmodules {

 private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @Provides
    public Retrofit providesRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    public RetrofitHelper providesRetrofitHelper(){
        return new RetrofitHelper();
    }




}
