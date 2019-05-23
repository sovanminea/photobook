package me.sovanminea.photobook.ui.fragment;

import java.util.List;

import me.sovanminea.photobook.listener.LoadImageListener;
import me.sovanminea.photobook.listener.OnItemClickListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.mvp.FragmentNavigationVP;
import me.sovanminea.photobook.ui.mvp.PhotoFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.PhotoFragmentPresenterImpl;

public class PhotoFragment extends BasePhotoListFragment implements LoadImageListener, OnItemClickListener, PhotoFragmentVP.PhotoFragmentView, FragmentNavigationVP.View {

    private PhotoFragmentVP.PhotoFragmentPresenter photoFragmentPresenter;

    @Override
    public void onResume() {
        super.onResume();
        photoFragmentPresenter = new PhotoFragmentPresenterImpl(this);
        photoFragmentPresenter.requestGetImages(page);
    }

    @Override
    public void onLoadFirst() {
//        photoFragmentPresenter.requestGetImages(page);
    }

    @Override
    public void onLoadMore() {
        mPhotoListAdapter.enableLoadingBottom();
        photoFragmentPresenter.requestGetImages(page);
    }

    @Override
    public void onRequestGetImagesResponse(List<PhotoModel> photoModelList) {
        swipeRefreshLayout.setRefreshing(false);
        mPhotoListAdapter.addItems(photoModelList);
        page++;
    }

    @Override
    public void onError(String message) {
        mPhotoListAdapter.removeBottomPb();
    }

    @Override
    public void onItemClick(PhotoListAdapter.PhotoListViewHolder holder, int position) {
        presenter.getPhotoClicked(holder, mPhotoListAdapter.getItem(position), position);
    }

    @Override
    public void attachPresenter(FragmentNavigationVP.Presenter presenter) {
        this.presenter = presenter;
    }

    public void updateItem(int position) {
        PhotoModel photoModel = mPhotoListAdapter.getItem(position);
        photoModel.setBookmark(!photoModel.isBookmark());
        mPhotoListAdapter.updateItem(position, photoModel);
    }
}
