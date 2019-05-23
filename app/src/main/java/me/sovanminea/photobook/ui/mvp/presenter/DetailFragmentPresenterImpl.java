package me.sovanminea.photobook.ui.mvp.presenter;

import java.util.List;

import me.sovanminea.photobook.listener.BookmarkOperationListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.DetailFragmentInteractorImpl;

public class DetailFragmentPresenterImpl implements DetailFragmentVP.DetailFragmentPresenter, BookmarkOperationListener {

    DetailFragmentVP.DetailFragmentView detailFragmentView;
    DetailFragmentInteractorImpl detailFragmentInteractor;

    public DetailFragmentPresenterImpl(DetailFragmentVP.DetailFragmentView detailFragmentView) {
        this.detailFragmentView = detailFragmentView;
        detailFragmentInteractor = new DetailFragmentInteractorImpl();
    }

    @Override
    public void createOrDeleteBookmark(boolean shouldCreate, PhotoModel model) {
        if (shouldCreate)
            detailFragmentInteractor.createBookmark(model, this);
        else detailFragmentInteractor.deleteBookmark(model, this);
    }

    @Override
    public void onBookmarkDeleted() {
        detailFragmentView.onBookmarkDeleted();
    }

    @Override
    public void onBookmarkCreated() {
        detailFragmentView.onBookmarkCreated();
    }

    @Override
    public void onGetBookmarkList(List<PhotoModel> photoModelList) {

    }

    @Override
    public void onGetBookmarkFailed(String message) {

    }
}
