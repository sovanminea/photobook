package me.sovanminea.photobook.ui.mvp;

import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.interactor.HomeFragmentInteractorImpl;
import me.sovanminea.photobook.ui.mvp.presenter.HomeFragmentPresenterImpl;

public class HomeFragmentVP {

    public interface HomeFragmentPresenter {
        void setupPagerAdapter(FragmentManager fm);
    }

    public interface HomeFragmentInteractor {
        void onSetupPagerAdapter(FragmentManager fm, HomeFragmentPresenterImpl presenter, HomeFragmentInteractorImpl.OnPagerAdapterSetup onPagerAdapterSetup);
    }

    public interface HomeFragmentView {
        void onPagerAdapterReady(TabAdapter tabAdapter);
        void getPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel model);
    }
}
