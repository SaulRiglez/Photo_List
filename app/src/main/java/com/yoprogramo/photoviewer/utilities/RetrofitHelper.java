package com.yoprogramo.photoviewer.utilities;

import com.yoprogramo.photoviewer.dagger.components.DaggerIComponent;
import com.yoprogramo.photoviewer.dagger.components.IComponent;
import com.yoprogramo.photoviewer.dagger.modules.Utilitiesmodules;
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.model.PhotoService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;



/**
 * Created by User on 3/24/2017.
 */

public class RetrofitHelper {


    @Inject
    Retrofit retrofit;


    public Observable<List<Photo>> createPhotoObservable(){

        IComponent iComponent = DaggerIComponent.builder()
                .utilitiesmodules(new Utilitiesmodules())
                .build();

        retrofit = iComponent.injectRetrofit();


        PhotoService service = retrofit.create(PhotoService.class);

        return service.getPhotos();
    }

}
