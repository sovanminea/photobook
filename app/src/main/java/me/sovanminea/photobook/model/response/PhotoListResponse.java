package me.sovanminea.photobook.model.response;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;

public class PhotoListResponse {

    private List<PhotoModel> mPhotoList;

    public PhotoListResponse(List<PhotoModel> data) {
        mPhotoList = data;
    }

    public List<PhotoModel> getPhotoList() {
        return mPhotoList;
    }
}
