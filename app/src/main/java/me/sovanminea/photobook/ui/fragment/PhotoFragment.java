package me.sovanminea.photobook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.listener.LoadImageListener;
import me.sovanminea.photobook.listener.OnItemClickListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.ItemOffsetDecoration;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.animator.SlideInUpAnimator;
import me.sovanminea.photobook.ui.mvp.FragmentNavigationVP;
import me.sovanminea.photobook.ui.mvp.PhotoFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.PhotoFragmentPresenterImpl;

public class PhotoFragment extends Fragment implements LoadImageListener, OnItemClickListener, PhotoFragmentVP.PhotoFragmentView, FragmentNavigationVP.View {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private PhotoListAdapter mPhotoListAdapter;
    private List<PhotoModel> mItemList = new ArrayList<>();

    private int page = 1;

    private PhotoFragmentVP.PhotoFragmentPresenter photoFragmentPresenter;
    private FragmentNavigationVP.Presenter presenter;

    private void setupRecycler(View v) {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mPhotoListAdapter = new PhotoListAdapter(v.getContext(), mItemList, recyclerView, this, this);
        recyclerView.setAdapter(mPhotoListAdapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(10));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        photoFragmentPresenter = new PhotoFragmentPresenterImpl(this);

        swipeRefreshLayout = view.findViewById(R.id.swp);
        recyclerView = view.findViewById(R.id.photo_list);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(true);
        setupRecycler(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RESUME", "onResume: ");
    }

    @Override
    public void onLoadFirst() {
        photoFragmentPresenter.requestGetImages(page);
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
