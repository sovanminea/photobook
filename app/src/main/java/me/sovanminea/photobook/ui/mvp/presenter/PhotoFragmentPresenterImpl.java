package me.sovanminea.photobook.ui.mvp.presenter;

import java.util.List;

import me.sovanminea.photobook.listener.BaseResponseListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.PhotoFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.PhotoFragmentInteractorImpl;

public class PhotoFragmentPresenterImpl implements PhotoFragmentVP.PhotoFragmentPresenter, BaseResponseListener<List<PhotoModel>> {

    private PhotoFragmentVP.PhotoFragmentView photoFragmentView;
    private PhotoFragmentVP.PhotoFragmentInteractor photoFragmentInteractor;

    public PhotoFragmentPresenterImpl(PhotoFragmentVP.PhotoFragmentView homeFragmentView) {
        this.photoFragmentView = homeFragmentView;
        this.photoFragmentInteractor = new PhotoFragmentInteractorImpl(this);
    }

    @Override
    public void requestGetImages(int page) {
        photoFragmentInteractor.onRequestGetImages(page);
    }

    @Override
    public void onError(String message) {
        photoFragmentView.onError(message);
    }

    @Override
    public void onSuccess(List<PhotoModel> data) {
        photoFragmentView.onRequestGetImagesResponse(data);
    }
}
