package me.sovanminea.photobook.ui.mvp.presenter;

import android.support.v4.app.FragmentManager;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.FragmentNavigationVP;
import me.sovanminea.photobook.ui.mvp.HomeFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.HomeFragmentInteractorImpl;

public class HomeFragmentPresenterImpl implements HomeFragmentVP.HomeFragmentPresenter, HomeFragmentInteractorImpl.OnPagerAdapterSetup, FragmentNavigationVP.Presenter {

    private HomeFragmentVP.HomeFragmentInteractor mHomeFragmentInteractor;
    private HomeFragmentVP.HomeFragmentView mHomeFragmentView;

    public HomeFragmentPresenterImpl(HomeFragmentVP.HomeFragmentView homeView) {
        mHomeFragmentView = homeView;
        mHomeFragmentInteractor = new HomeFragmentInteractorImpl();
    }

    @Override
    public void setupPagerAdapter(FragmentManager fm) {
        mHomeFragmentInteractor.onSetupPagerAdapter(fm, this, this);
    }

    @Override
    public void onAdapterFinish(TabAdapter adapter) {
        mHomeFragmentView.onPagerAdapterReady(adapter);
    }

    @Override
    public void getPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel photoModel, int position) {
        mHomeFragmentView.getPhotoClicked(viewHolder, photoModel, position);
    }

    @Override
    public void getBookmarkClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel photoModel) {
        mHomeFragmentView.getBookmarkClicked(viewHolder, photoModel);
    }
}
