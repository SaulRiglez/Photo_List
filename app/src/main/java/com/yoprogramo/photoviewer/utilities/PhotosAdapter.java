package com.yoprogramo.photoviewer.utilities;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yoprogramo.photoviewer.R;
import com.yoprogramo.photoviewer.entities.Photo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by User on 3/24/2017.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    Context context;

    private List<Photo> photosList;
    OnItemClickListener onItemClickListener;





    public PhotosAdapter(List<Photo> photos, Context context) {
        this.context = context;
        this.photosList = photos;

    }

    @Override
    public PhotosAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_row_data, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotosAdapter.PhotoViewHolder holder, int position) {

        Photo photo = this.photosList.get(position);
        holder.tv.setText(photo.getId().toString());
        holder.progressBarRecycler.setVisibility(View.VISIBLE);

        Log.d("url", "onBindViewHolder: " + photo.getThumbnailUrl());

        String Bg = photo.getThumbnailUrl().substring(24,photo.getThumbnailUrl().length());

        Picasso.with(context).load(buildUrl(Bg))
                .transform(new CircleTransformation())
                .into(holder.avatarImageview, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBarRecycler.setVisibility(View.GONE);
                        Log.d("exitoso", "onSuccess: ");

                    }

                    @Override
                    public void onError( ) {
                        Log.d("picasso", "onError: "+  "picasso");


                    }
                });




    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView avatarImageview;
        TextView tv;
        ProgressBar progressBarRecycler;



        public PhotoViewHolder(View itemView) {
            super(itemView);

            avatarImageview = (ImageView) itemView.findViewById(R.id.image_view);
            tv = (TextView) itemView.findViewById(R.id.text_view);
            progressBarRecycler = (ProgressBar) itemView.findViewById(R.id.progressBarRecycler) ;


            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view,getAdapterPosition());
        }
    }


    public void setClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public String buildUrl(String bg){

        final String PHOTO_BASE_URL = "https://placeholdit.imgix.net/";
        final String PARAM_TEXT_SIZE = "textSize";
        final String PARAM_BG = "bg";
        final String PARAM_TXT = "txt";
        final String PARAM_W = "w";
        final String PARAM_H = "h";
        final String path = "~text";


        Uri uri = Uri.parse(PHOTO_BASE_URL)
                .buildUpon()
                .appendPath(path)
                .appendQueryParameter(PARAM_TEXT_SIZE,"14")
                .appendQueryParameter(PARAM_BG, bg)
                .appendQueryParameter(PARAM_TXT,"150%C3%97150")
                .appendQueryParameter(PARAM_W,"150")
                .appendQueryParameter(PARAM_H,"150").build();


        return uri.toString();
    }


}
