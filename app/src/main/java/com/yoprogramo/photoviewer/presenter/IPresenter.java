package com.yoprogramo.photoviewer.presenter;

import android.view.View;

import com.yoprogramo.photoviewer.entities.Photo;

import java.util.List;

/**
 * Created by User on 3/24/2017.
 */

public class IPresenter {

    public interface IMainPresenter{
        void downloadPhotos(List<Photo> photosList);
        void onItemClicked(View view, int position);
    }

    public interface IDetailPresenter{

        String getUrlString(String url);

    }

}
