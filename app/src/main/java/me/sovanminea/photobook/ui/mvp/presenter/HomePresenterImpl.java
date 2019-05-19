package me.sovanminea.photobook.ui.mvp.presenter;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.listener.BaseResponseListener;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.interactor.HomeInteractorImpl;

public class HomePresenterImpl implements Home.HomePresenter, BaseResponseListener<List<PhotoModel>> {

    private Home.HomeInteractor mHomeInteractor;
    private Home.HomeView mHomeView;

    public HomePresenterImpl(Home.HomeView homeView) {
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl(this);
    }

    @Override
    public void getImages() {
        mHomeInteractor.onGetImages();
    }

    @Override
    public void onError(String message) {
        mHomeView.onError(message);
    }

    @Override
    public void onSuccess(List<PhotoModel> data) {
        mHomeView.onImagesResponse(data);
    }

}
