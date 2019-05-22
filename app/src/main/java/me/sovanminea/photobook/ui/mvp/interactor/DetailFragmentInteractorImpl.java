package me.sovanminea.photobook.ui.mvp.interactor;

import android.util.Log;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;

public class DetailFragmentInteractorImpl implements DetailFragmentVP.DetailFragmentInteractor {

    private Realm realm;
    private PhotoModel bookmark;

    public DetailFragmentInteractorImpl() {
        realm = Realm.getDefaultInstance();
    }

    private void logBookmark() {
        final RealmResults<PhotoModel> results = realm.where(PhotoModel.class).findAll();
        if (results.size() == 0) Log.d("Bookmark", "empty");
        else {
            for (PhotoModel photo :
                    results) {
                Log.d("Bookmark", photo.toString());
            }
        }
    }

    @Override
    public void createBookmark(PhotoModel model) {
        realm.beginTransaction();
        realm.insertOrUpdate(model);
        realm.commitTransaction();
        logBookmark();
    }

    @Override
    public void deleteBookmark(PhotoModel model) {
        bookmark = realm.where(PhotoModel.class).equalTo("id", model.getId()).findFirst();
        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                bookmark.deleteFromRealm();
            }
        });
        logBookmark();
    }
}
