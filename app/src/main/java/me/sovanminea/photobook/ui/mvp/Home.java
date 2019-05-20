package me.sovanminea.photobook.ui.mvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.interactor.HomeInteractorImpl;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class Home {

    public interface HomePresenter {
        void setupPagerAdapter(FragmentManager fm);
    }

    public interface HomeInteractor {
        void onSetupPagerAdapter(FragmentManager fm, HomePresenterImpl presenter, HomeInteractorImpl.OnPagerAdapterSetup onPagerAdapterSetup);
    }

    public interface HomeView {
        void onPagerAdapterReady(TabAdapter tabAdapter);
        void getPhotoClicked(PhotoModel model);
    }
}
