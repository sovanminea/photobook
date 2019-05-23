package me.sovanminea.photobook.ui.mvp.interactor;

import android.util.Log;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import io.realm.Sort;
import me.sovanminea.photobook.listener.BookmarkOperationListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;

public class DetailFragmentInteractorImpl implements DetailFragmentVP.DetailFragmentInteractor {

    private Realm realm;
    private PhotoModel bookmark;
    private BookmarkOperationListener bookmarkOperationListener;

    public DetailFragmentInteractorImpl() {
        realm = Realm.getDefaultInstance();
    }

    private void logBookmark() {
        final RealmResults<PhotoModel> results = realm.where(PhotoModel.class).sort("id", Sort.ASCENDING).findAll();
        if (results.size() == 0) Log.d("Bookmark", "empty");
        else {
            for (PhotoModel photo :
                    results) {
                Log.d("Bookmark", photo.toString());
            }
        }
    }

    @Override
    public void createBookmark(PhotoModel model, BookmarkOperationListener bookmarkOperationListener) {
        PhotoModel m = model;
        m.setBookmark(true);
        realm.beginTransaction();
        realm.insertOrUpdate(m);
        realm.commitTransaction();
        logBookmark();
        if (this.bookmarkOperationListener == null)
            this.bookmarkOperationListener = bookmarkOperationListener;
        bookmarkOperationListener.onBookmarkCreated();
    }

    @Override
    public void deleteBookmark(PhotoModel model, BookmarkOperationListener bookmarkOperationListener) {
        bookmark = realm.where(PhotoModel.class).equalTo("id", model.getId()).findFirst();
        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                bookmark.deleteFromRealm();
            }
        });
        logBookmark();
        if (this.bookmarkOperationListener == null)
            this.bookmarkOperationListener = bookmarkOperationListener;
        bookmarkOperationListener.onBookmarkDeleted();
    }
}
