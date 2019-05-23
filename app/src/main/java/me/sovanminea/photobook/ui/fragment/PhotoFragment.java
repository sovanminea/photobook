package me.sovanminea.photobook.ui.fragment;

import android.util.Log;

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

    public void updateItem(int position) {
        PhotoModel photoModel = mPhotoListAdapter.getItem(position);
        if (photoModel.isBookmark()) photoModel.setBookmark(false);
    }

    public void updateCreateBookmarkItem(String id) {
        PhotoModel photoModel = mPhotoListAdapter.getItemById(id);
        photoModel.setBookmark(true);
    }

    public void updateDeleteBookmarkItem(String id) {
        PhotoModel photoModel = mPhotoListAdapter.getItemById(id);
        Log.d("ddd", "updateDeleteBookmarkItem: " + photoModel.toString());
        photoModel.setBookmark(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        photoFragmentPresenter = new PhotoFragmentPresenterImpl(this);
        photoFragmentPresenter.requestGetImages(page);
    }

    @Override
    public void onLoadFirst() {
        super.onLoadFirst();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPhotoListAdapter.enableLoadingBottom();
        photoFragmentPresenter.requestGetImages(page);
    }

    @Override
    public void onRequestGetImagesResponse(List<PhotoModel> photoModelList) {
        super.onRequestGetImagesResponse(photoModelList);
        swipeRefreshLayout.setRefreshing(false);
        mPhotoListAdapter.addItems(photoModelList);
        page++;
    }

    @Override
    public void onError(String message) {
        mPhotoListAdapter.removeBottomPb();
        swipeRefreshLayout.setRefreshing(false);
        if (mPhotoListAdapter.getItemCount() == 0)
            showErrorMessage("Not found, please check your network connection.");
    }

    @Override
    public void onItemClick(PhotoListAdapter.PhotoListViewHolder holder, int position) {
        presenter.getPhotoClicked(holder, mPhotoListAdapter.getItem(position), position);
    }

}
