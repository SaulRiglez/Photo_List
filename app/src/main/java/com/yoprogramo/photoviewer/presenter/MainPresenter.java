package com.yoprogramo.photoviewer.presenter;

import android.view.View;


import com.yoprogramo.photoviewer.dagger.components.DaggerIComponent;
import com.yoprogramo.photoviewer.dagger.components.IComponent;
import com.yoprogramo.photoviewer.dagger.modules.Utilitiesmodules;
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.utilities.RetrofitHelper;
import com.yoprogramo.photoviewer.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by User on 3/24/2017.
 */

public class MainPresenter implements IPresenter.IMainPresenter {

    @Inject
    RetrofitHelper retrofitHelper;

    IView.ImainView iMainView;

    List<Photo>  picturesList = new ArrayList<>();

    public MainPresenter(IView.ImainView imainView) {
        this.iMainView = imainView;
    }

    @Override
    public void downloadPhotos(final List <Photo> photosList) {

        IComponent iComponent = DaggerIComponent.builder()
                .utilitiesmodules(new Utilitiesmodules())
                .build();

        retrofitHelper = iComponent.injectRetrofitHelper();


        Observable<List<Photo>> resultObservablePhotos = retrofitHelper.createPhotoObservable();
        Observer observerPhotos = new Observer<List<Photo>>() {

            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<Photo> List) {

                for (Photo picture : List) {
                    photosList.add(picture);
                }
              iMainView.notifyAdapter();

                picturesList = photosList;

            }
        };

        resultObservablePhotos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPhotos);
    }

    @Override
    public void onItemClicked(View view, int position) {
        iMainView.goToDetailActivity(position,picturesList);

    }
}
