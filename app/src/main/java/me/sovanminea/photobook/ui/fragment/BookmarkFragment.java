package me.sovanminea.photobook.ui.fragment;

import android.util.Log;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.mvp.BookmarkFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.BookmarkFragmentPresenterImpl;

public class BookmarkFragment extends BasePhotoListFragment implements BookmarkFragmentVP.BookmarkFragmentView {

    private BookmarkFragmentVP.BookmarkFragmentPresenter bookmarkFragmentPresenter;

    public void onDBUpdated() {
        mPhotoListAdapter.clear();
        resetPage();
        bookmarkFragmentPresenter.getBookmarkData(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        bookmarkFragmentPresenter = new BookmarkFragmentPresenterImpl(this);
        bookmarkFragmentPresenter.getBookmarkData(page);
    }

    @Override
    public void onLoadMore() {
        mPhotoListAdapter.enableLoadingBottom();
        bookmarkFragmentPresenter.getBookmarkData(page);
    }

    @Override
    public void onBookmarkResponse(List<PhotoModel> photoModelList) {
        swipeRefreshLayout.setRefreshing(false);
        mPhotoListAdapter.addItems(photoModelList);
        page++;
    }

    @Override
    public void onBookmarkFail(String message) {
        mPhotoListAdapter.removeBottomPb();
    }

    @Override
    public void onItemClick(PhotoListAdapter.PhotoListViewHolder view, int position) {
        super.onItemClick(view, position);
        presenter.getBookmarkClicked(view, mPhotoListAdapter.getItem(position));
    }
}
