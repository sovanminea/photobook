package me.sovanminea.photobook.ui.mvp;

import me.sovanminea.photobook.listener.BookmarkOperationListener;
import me.sovanminea.photobook.model.PhotoModel;

public class DetailFragmentVP {

    public interface DetailFragmentPresenter {
        void createOrDeleteBookmark(boolean shouldCreate, PhotoModel model);
    }

    public interface DetailFragmentInteractor {
        void createBookmark(PhotoModel model, BookmarkOperationListener bookmarkOperationListener);

        void deleteBookmark(PhotoModel model, BookmarkOperationListener bookmarkOperationListener);
    }

    public interface DetailFragmentView {
        void onBookmarkDeleted();

        void onBookmarkCreated();
    }

    public interface OnFragmentInteractionListener {
        void onExit();

        void onBookmarkDeleted(int position);

        void onBookmarkCreated(int position);
    }

}
