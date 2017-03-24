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
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.utilities.OnItemClickListener;
import com.yoprogramo.photoviewer.utilities.PhotosAdapter;
import com.yoprogramo.photoviewer.utilities.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {


    List<Photo> photosList = new ArrayList<>();

    PhotosAdapter photoAdapter;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        downloadPhotos();

        photoAdapter = new PhotosAdapter(photosList, this);
        photoAdapter.setClickListener(this);
        RecyclerView.LayoutManager mGridManagerGrid = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mGridManagerGrid);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(photoAdapter);


    }

    private void downloadPhotos() {


        Observable<List<Photo>> resultObservablePhotos = RetrofitHelper.Factory.createPhotoObservable();
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

                photoAdapter.notifyDataSetChanged();

            }
        };

        resultObservablePhotos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPhotos);

    }

    @Override
    public void onClick(View view, int position) {

        Intent intentStartActivity = new Intent(this,DetailActivity.class);
        Photo picture = photosList.get(position);
        intentStartActivity.putExtra("picture_detail",picture.getUrl());
        startActivity(intentStartActivity);

    }
}
