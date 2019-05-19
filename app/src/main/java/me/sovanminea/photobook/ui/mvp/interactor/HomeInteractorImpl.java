package me.sovanminea.photobook.ui.mvp.interactor;

import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.fragment.BookmarkFragment;
import me.sovanminea.photobook.ui.fragment.HomeFragment;
import me.sovanminea.photobook.ui.mvp.Home;

public class HomeInteractorImpl implements Home.HomeInteractor {

    public interface OnPagerAdapterSetup {
        void onAdapterFinish(TabAdapter adapter);
    }

    public HomeInteractorImpl() {

    }



    @Override
    public void onSetupPagerAdapter(FragmentManager fm, OnPagerAdapterSetup onPagerAdapterSetup) {
        TabAdapter tabAdapter = new TabAdapter(fm);
        tabAdapter.addFragment(new HomeFragment(), "Photos");
        tabAdapter.addFragment(new BookmarkFragment(), "Bookmarks");
        onPagerAdapterSetup.onAdapterFinish(tabAdapter);
    }
}
