package me.sovanminea.photobook.ui.mvp.interactor;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import me.sovanminea.photobook.listener.BookmarkOperationListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.BookmarkFragmentVP;

public class BookmarkFragmentInteractorImpl implements BookmarkFragmentVP.BookmarkFragmentInteractor {

    private final int LIMIT = 30;

    private Realm realm;
    private List<PhotoModel> allPhotoModelList;

    public BookmarkFragmentInteractorImpl() {
        realm = Realm.getDefaultInstance();
    }

    private List<PhotoModel> getListWithPage(int page) {
        int start = (page - 1) * LIMIT;
        int end = page * LIMIT;
        if (allPhotoModelList.size() < start) return null;
        else if (allPhotoModelList.size() < end) {
            return allPhotoModelList.subList(start, allPhotoModelList.size());
        } else return allPhotoModelList.subList(start, end);
    }

    @Override
    public void getBookmarkData(int page, BookmarkOperationListener listener) {
        if (allPhotoModelList == null) {
            RealmResults<PhotoModel> realmResults = realm.where(PhotoModel.class)
                    .equalTo("bookmark", true)
                    .findAll();
            if (realmResults.size() == 0) listener.onGetBookmarkFailed("not found");
            else {
                allPhotoModelList = realmResults;
            }
        }
        List<PhotoModel> photoModels = getListWithPage(page);
        if(photoModels == null) listener.onGetBookmarkFailed("No more data");
        else listener.onGetBookmarkList(photoModels);
    }

    @Override
    public void resetData() {
        allPhotoModelList = null;
    }
}
