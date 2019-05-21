package me.sovanminea.photobook.ui.mvp.presenter;

import android.support.v4.app.FragmentManager;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.FragmentNavigationVP;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.interactor.HomeInteractorImpl;

public class HomePresenterImpl implements Home.HomePresenter, HomeInteractorImpl.OnPagerAdapterSetup, FragmentNavigationVP.Presenter {

    private Home.HomeInteractor mHomeInteractor;
    private Home.HomeView mHomeView;

    public HomePresenterImpl(Home.HomeView homeView) {
        mHomeView = homeView;
        mHomeInteractor = new HomeInteractorImpl();
    }

    @Override
    public void setupPagerAdapter(FragmentManager fm) {
        mHomeInteractor.onSetupPagerAdapter(fm, this, this);
    }

    @Override
    public void onAdapterFinish(TabAdapter adapter) {
        mHomeView.onPagerAdapterReady(adapter);
    }

    @Override
    public void getPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel photoModel) {
        mHomeView.getPhotoClicked(viewHolder,photoModel);
    }
}
