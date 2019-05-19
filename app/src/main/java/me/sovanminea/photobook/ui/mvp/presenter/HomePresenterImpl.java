package me.sovanminea.photobook.ui.mvp.presenter;

import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.interactor.HomeInteractorImpl;

public class HomePresenterImpl implements Home.HomePresenter {

    private Home.HomeInteractor mHomeInteractor;
    private Home.HomeView mHomeView;

    public HomePresenterImpl(Home.HomeView homeView) {
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void getImages() {
        mHomeInteractor.onGetImages();
    }
}
