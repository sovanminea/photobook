package me.sovanminea.photobook.ui.mvp.interactor;

import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.fragment.BookmarkFragment;
import me.sovanminea.photobook.ui.fragment.HomeFragment;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeInteractorImpl implements Home.HomeInteractor {

    public interface OnPagerAdapterSetup {
        void onAdapterFinish(TabAdapter adapter);
    }

    public HomeInteractorImpl() {
    }

    @Override
    public void onSetupPagerAdapter(FragmentManager fm, HomePresenterImpl presenter, OnPagerAdapterSetup onPagerAdapterSetup) {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.attachPresenter(presenter);
        TabAdapter tabAdapter = new TabAdapter(fm);
        tabAdapter.addFragment(homeFragment, "Photos");
        tabAdapter.addFragment(new BookmarkFragment(), "Bookmarks");
        onPagerAdapterSetup.onAdapterFinish(tabAdapter);
    }
}
