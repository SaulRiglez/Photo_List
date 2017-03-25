package com.yoprogramo.photoviewer.dagger.components;

import android.content.Context;

import com.yoprogramo.photoviewer.dagger.modules.Utilitiesmodules;
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.presenter.MainPresenter;
import com.yoprogramo.photoviewer.utilities.PhotosAdapter;
import com.yoprogramo.photoviewer.utilities.RetrofitHelper;

import java.util.List;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by User on 3/24/2017.
 */
@Component(modules={Utilitiesmodules.class})
public interface IComponent {

     public Retrofit injectRetrofit();
     public RetrofitHelper injectRetrofitHelper();



}
