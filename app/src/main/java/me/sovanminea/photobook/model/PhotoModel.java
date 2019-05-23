package me.sovanminea.photobook.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhotoModel extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("url")
    private String url;

    @SerializedName("download_url")
    private String downloadUrl;

    private boolean bookmark = false;

    public PhotoModel() {
    }

    public PhotoModel(String id, String author, int width, int height, String url, String downloadUrl, boolean bookmark) {
        this.id = id;
        this.author = author;
        this.width = width;
        this.height = height;
        this.url = url;
        this.downloadUrl = downloadUrl;
        this.bookmark = bookmark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
    }

    @Override
    public String toString() {
        return "PhotoModel{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", bookmark=" + bookmark + "" +
                '}';
    }
}
