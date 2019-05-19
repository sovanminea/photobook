package me.sovanminea.photobook.ui.mvp.presenter;

import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.interactor.HomeInteractorImpl;

public class HomePresenterImpl implements Home.HomePresenter, HomeInteractorImpl.OnPagerAdapterSetup {

    private Home.HomeInteractor mHomeInteractor;
    private Home.HomeView mHomeView;

    public HomePresenterImpl(Home.HomeView homeView) {
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void setupPagerAdapter(FragmentManager fm) {
        mHomeInteractor.onSetupPagerAdapter(fm, this);
    }

    @Override
    public void onAdapterFinish(TabAdapter adapter) {
        mHomeView.onPagerAdapterReady(adapter);
    }
}
