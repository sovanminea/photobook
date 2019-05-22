package me.sovanminea.photobook.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class PhotoModel extends RealmObject {

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

//    public PhotoModel(String id, String author, int width, int height, String url, String downloadUrl) {
//        this.id = id;
//        this.author = author;
//        this.width = width;
//        this.height = height;
//        this.url = url;
//        this.downloadUrl = downloadUrl;
//    }

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

    @Override
    public String toString() {
        return "PhotoModel{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", url='" + url + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
