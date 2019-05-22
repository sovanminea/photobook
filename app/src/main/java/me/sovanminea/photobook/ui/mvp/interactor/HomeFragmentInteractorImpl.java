package me.sovanminea.photobook.ui.mvp.interactor;

import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.fragment.BookmarkFragment;
import me.sovanminea.photobook.ui.fragment.PhotoFragment;
import me.sovanminea.photobook.ui.mvp.HomeFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.HomeFragmentPresenterImpl;

public class HomeFragmentInteractorImpl implements HomeFragmentVP.HomeFragmentInteractor {

    public interface OnPagerAdapterSetup {
        void onAdapterFinish(TabAdapter adapter);
    }

    public HomeFragmentInteractorImpl() {
    }

    @Override
    public void onSetupPagerAdapter(FragmentManager fm, HomeFragmentPresenterImpl presenter, OnPagerAdapterSetup onPagerAdapterSetup) {
        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.attachPresenter(presenter);
        TabAdapter tabAdapter = new TabAdapter(fm);
        tabAdapter.addFragment(photoFragment, "Photos");
        tabAdapter.addFragment(new BookmarkFragment(), "Bookmarks");
        onPagerAdapterSetup.onAdapterFinish(tabAdapter);
    }
}
