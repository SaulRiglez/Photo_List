package com.yoprogramo.photoviewer.view;

import android.view.View;

import com.yoprogramo.photoviewer.entities.Photo;

import java.util.List;

/**
 * Created by User on 3/24/2017.
 */

public interface IView {

    public interface ImainView{
        void notifyAdapter();
        void goToDetailActivity(int position, List<Photo> photos);
    }

    public interface IDetailView{

    }
}
