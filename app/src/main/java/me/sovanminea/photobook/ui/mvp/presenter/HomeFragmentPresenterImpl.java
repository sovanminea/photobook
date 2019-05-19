package me.sovanminea.photobook.ui.mvp.presenter;

import java.util.List;

import me.sovanminea.photobook.listener.BaseResponseListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.HomeFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.HomeFragmentInteractorImpl;

public class HomeFragmentPresenterImpl implements HomeFragmentVP.HomeFragmentPresenter, BaseResponseListener<List<PhotoModel>> {

    private HomeFragmentVP.HomeFragmentView homeFragmentView;
    private HomeFragmentVP.HomeFragmentInteractor homeFragmentInteractor;

    public HomeFragmentPresenterImpl(HomeFragmentVP.HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
        this.homeFragmentInteractor = new HomeFragmentInteractorImpl(this);
    }

    @Override
    public void requestGetImages(int page) {
        homeFragmentInteractor.onRequestGetImages(page);
    }

    @Override
    public void onError(String message) {
        homeFragmentView.onError(message);
    }

    @Override
    public void onSuccess(List<PhotoModel> data) {
        homeFragmentView.onRequestGetImagesResponse(data);
    }
}
