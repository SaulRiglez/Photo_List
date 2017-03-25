package com.yoprogramo.photoviewer.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yoprogramo.photoviewer.R;
import com.yoprogramo.photoviewer.dagger.components.IComponent;
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.presenter.IPresenter;
import com.yoprogramo.photoviewer.presenter.MainPresenter;
import com.yoprogramo.photoviewer.utilities.OnItemClickListener;
import com.yoprogramo.photoviewer.utilities.PhotosAdapter;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements OnItemClickListener, IView.ImainView {



    List<Photo> photosList = new ArrayList<>();

    PhotosAdapter photoAdapter;
    IPresenter.IMainPresenter iMainPresenter;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);





        photoAdapter = new PhotosAdapter(photosList, this);
        photoAdapter.setClickListener(this);
        RecyclerView.LayoutManager mGridManagerGrid = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mGridManagerGrid);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(photoAdapter);

        iMainPresenter = new MainPresenter(this);
        iMainPresenter.downloadPhotos(photosList);

    }


    @Override
    public void onClick(View view, int position) {

        iMainPresenter.onItemClicked(view, position);
    }

    @Override
    public void notifyAdapter() {
        photoAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToDetailActivity(int position, List<Photo> Pictures) {

        Intent intentStartActivity = new Intent(this, DetailActivity.class);
        Photo picture = Pictures.get(position);
        intentStartActivity.putExtra("picture_detail", picture);
        startActivity(intentStartActivity);
    }
}
