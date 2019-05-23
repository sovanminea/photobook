package me.sovanminea.photobook.listener;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;

public interface BookmarkOperationListener {
    void onBookmarkDeleted();
    void onBookmarkCreated();
    void onGetBookmarkList(List<PhotoModel> photoModelList);
    void onGetBookmarkFailed(String message);
}
