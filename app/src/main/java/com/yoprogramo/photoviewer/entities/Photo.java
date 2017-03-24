
package com.yoprogramo.photoviewer.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo implements Parcelable{

    @SerializedName("albumId")
    @Expose
    private Integer albumId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }




    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(albumId);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(thumbnailUrl);
    }


    protected Photo(Parcel in){
        albumId = in.readInt();
        id = in.readInt();
        title = in.readString();
        url = in.readString();
        thumbnailUrl = in.readString();
    }

    @Override
    public String toString() {
        return "Photo{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }


}
