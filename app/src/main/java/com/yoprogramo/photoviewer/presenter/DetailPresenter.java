package com.yoprogramo.photoviewer.presenter;

import android.net.Uri;

/**
 * Created by User on 3/24/2017.
 */

public class DetailPresenter implements IPresenter.IDetailPresenter {


    final String PHOTO_BASE_URL = "https://placeholdit.imgix.net/";
    final String PARAM_TEXT_SIZE = "textSize";
    final String PARAM_BG = "bg";
    final String PARAM_TXT = "txt";
    final String PARAM_W = "w";
    final String PARAM_H = "h";
    final String path = "~text";

    @Override
    public String getUrlString(String url) {

        String myBg = url.substring(24,url.length());
        Uri uri = Uri.parse(PHOTO_BASE_URL)
                .buildUpon()
                .appendPath(path)
                .appendQueryParameter(PARAM_TEXT_SIZE,"14")
                .appendQueryParameter(PARAM_BG, myBg)
                .appendQueryParameter(PARAM_TXT,"150%C3%97150")
                .appendQueryParameter(PARAM_W,"150")
                .appendQueryParameter(PARAM_H,"150").build();
        return uri.toString();
    }
}
