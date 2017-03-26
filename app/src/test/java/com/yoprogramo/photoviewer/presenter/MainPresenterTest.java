package com.yoprogramo.photoviewer.presenter;

import android.view.View;

import com.yoprogramo.photoviewer.view.MainActivity;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by User on 3/26/2017.
 */
public class MainPresenterTest {

    MainActivity myMainActivty = mock(MainActivity.class);
    MainPresenter myPresenterTest = new MainPresenter(myMainActivty);
    View view = mock(View.class);


    @Test
    public void onItemClicked()
    {
        myPresenterTest.onItemClicked(view , 1);
        verify(myMainActivty).goToDetailActivity(1,myPresenterTest.picturesList);
    }

}