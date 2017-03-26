package com.yoprogramo.photoviewer.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yoprogramo.photoviewer.R;
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.presenter.DetailPresenter;
import com.yoprogramo.photoviewer.presenter.IPresenter;
import com.yoprogramo.photoviewer.utilities.CircleTransformation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    IPresenter.IDetailPresenter iDetailPresenter;
    @BindView(R.id.detailImageView)
    ImageView imageView;
    @BindView(R.id.text_view_title)
    TextView tvTitle;
    @BindView(R.id.text_view_id_picture)
    TextView tvIdPicture;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Photo detailPicture = intent.getParcelableExtra("picture_detail");
        iDetailPresenter = new DetailPresenter();
        String uri = iDetailPresenter.getUrlString(detailPicture.getUrl());


        Picasso.with(this).load(uri).into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

        tvIdPicture.setText(detailPicture.getId().toString());
        tvTitle.setText(detailPicture.getTitle());

    }
}
