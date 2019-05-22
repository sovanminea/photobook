package me.sovanminea.photobook.ui.mvp.presenter;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.DetailFragmentInteractorImpl;

public class DetailFragmentPresenterImpl implements DetailFragmentVP.DetailFragmentPresenter {

    DetailFragmentVP.DetailFragmentView detailFragmentView;
    DetailFragmentInteractorImpl detailFragmentInteractor;

    public DetailFragmentPresenterImpl(DetailFragmentVP.DetailFragmentView detailFragmentView) {
        this.detailFragmentView = detailFragmentView;
        detailFragmentInteractor = new DetailFragmentInteractorImpl();
    }

    @Override
    public void createOrDeleteBookmark(boolean shouldCreate, PhotoModel model) {
        if (shouldCreate) detailFragmentInteractor.createBookmark(model);
        else detailFragmentInteractor.deleteBookmark(model);
    }

}
