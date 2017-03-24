package com.yoprogramo.photoviewer.model;



import com.yoprogramo.photoviewer.entities.Photo;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by User on 3/24/2017.
 */

public interface PhotoService {

    @GET("photos")
    Observable<List<Photo>> getPhotos();

}
