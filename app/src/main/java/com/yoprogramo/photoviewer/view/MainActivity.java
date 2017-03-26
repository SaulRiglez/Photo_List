package com.yoprogramo.photoviewer.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yoprogramo.photoviewer.R;
import com.yoprogramo.photoviewer.entities.Photo;
import com.yoprogramo.photoviewer.presenter.IPresenter;
import com.yoprogramo.photoviewer.presenter.MainPresenter;
import com.yoprogramo.photoviewer.utilities.OnItemClickListener;
import com.yoprogramo.photoviewer.utilities.PhotosAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements OnItemClickListener, IView.ImainView  {


    List<Photo> photosList = new ArrayList<>();

    PhotosAdapter photoAdapter;
    RecyclerView.LayoutManager mGridManagerGrid;



    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;
    RecyclerView.State state;

    IPresenter.IMainPresenter iMainPresenter;


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        photoAdapter = new PhotosAdapter(photosList, this);
        photoAdapter.setClickListener(this);
        mGridManagerGrid = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mGridManagerGrid);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
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


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         int visiblePosition = recyclerView.getScrollState();

        Parcelable state = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY,state);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

       if(savedInstanceState != null){

           Parcelable mBundleRecyclerViewState = savedInstanceState.getParcelable(LIST_STATE_KEY);
           recyclerView.getLayoutManager().onRestoreInstanceState(mBundleRecyclerViewState);

       }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            mGridManagerGrid.onRestoreInstanceState(listState);
            photoAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_reset){

           mGridManagerGrid.scrollToPosition(0);
            Toast.makeText(this, "Initial list refreshed", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }
}
