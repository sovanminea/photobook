package me.sovanminea.photobook.ui.mvp.presenter;

import java.util.List;

import me.sovanminea.photobook.listener.BookmarkOperationListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.BookmarkFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.BookmarkFragmentInteractorImpl;

public class BookmarkFragmentPresenterImpl implements BookmarkFragmentVP.BookmarkFragmentPresenter {

    private BookmarkFragmentVP.BookmarkFragmentView bookmarkFragmentView;
    private BookmarkFragmentInteractorImpl bookmarkFragmentInteractor;

    public BookmarkFragmentPresenterImpl(BookmarkFragmentVP.BookmarkFragmentView bookmarkFragmentView) {
        this.bookmarkFragmentView = bookmarkFragmentView;
        bookmarkFragmentInteractor = new BookmarkFragmentInteractorImpl();
    }

    @Override
    public void getBookmarkData(int page) {
        bookmarkFragmentInteractor.getBookmarkData(page, new BookmarkOperationListener() {
            @Override
            public void onBookmarkDeleted() {

            }

            @Override
            public void onBookmarkCreated() {

            }

            @Override
            public void onGetBookmarkList(List<PhotoModel> photoModelList) {
                bookmarkFragmentView.onBookmarkResponse(photoModelList);
            }

            @Override
            public void onGetBookmarkFailed(String message) {
                bookmarkFragmentView.onBookmarkFail(message);
            }
        });
    }

    @Override
    public void getBookmarkData(boolean shouldReset) {
        bookmarkFragmentInteractor.resetData();
        getBookmarkData(1);
    }
}
