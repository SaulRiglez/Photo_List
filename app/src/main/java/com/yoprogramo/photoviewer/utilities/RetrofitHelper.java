package com.yoprogramo.photoviewer.utilities;

import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.model.PhotoService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import static com.yoprogramo.photoviewer.utilities.RetrofitHelper.Factory.create;

/**
 * Created by User on 3/24/2017.
 */

public class RetrofitHelper {


    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    static Retrofit retrofit = create();
    static PhotoService service = retrofit.create(PhotoService.class);

    public static class Factory{

        public static Retrofit create(){
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }


        public static Observable<List<Photo>> createPhotoObservable( ){
            return service.getPhotos();
        }

    }


}
