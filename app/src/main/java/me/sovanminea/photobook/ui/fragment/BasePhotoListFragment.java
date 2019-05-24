package me.sovanminea.photobook.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.listener.LoadImageListener;
import me.sovanminea.photobook.listener.OnItemClickListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.ItemOffsetDecoration;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.animator.SlideInUpAnimator;
import me.sovanminea.photobook.ui.mvp.BookmarkFragmentVP;
import me.sovanminea.photobook.ui.mvp.FragmentNavigationVP;
import me.sovanminea.photobook.ui.mvp.PhotoFragmentVP;

public abstract class BasePhotoListFragment extends Fragment implements LoadImageListener, OnItemClickListener, PhotoFragmentVP.PhotoFragmentView, BookmarkFragmentVP.BookmarkFragmentView, FragmentNavigationVP.View, View.OnClickListener {

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected TextView messageTextView;

    protected PhotoListAdapter mPhotoListAdapter;
    protected List<PhotoModel> mItemList = new ArrayList<>();

    protected int page = 1;

    protected FragmentNavigationVP.Presenter presenter;

    public void setupRecycler(View v) {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mPhotoListAdapter = new PhotoListAdapter(v.getContext(), mItemList, recyclerView, this, this);
        recyclerView.setAdapter(mPhotoListAdapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(10));
    }

    public void resetPage() {
        page = 1;
    }

    public void showErrorMessage(String s) {
        messageTextView.setText(s);
        messageTextView.setVisibility(View.VISIBLE);
        messageTextView.setOnClickListener(this);
    }

    public void enableTryAgain(){
        messageTextView.setClickable(true);
        messageTextView.setFocusable(true);
    }

    public void hideErrorMessage() {
        messageTextView.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.swp);
        recyclerView = view.findViewById(R.id.photo_list);
        messageTextView = view.findViewById(R.id.message);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(true);
        setupRecycler(view);
    }

    @Override
    public void attachPresenter(FragmentNavigationVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onLoadMore() {
        hideErrorMessage();
    }

    @Override
    public void onLoadFirst() {
        hideErrorMessage();
    }

    @Override
    public void onItemClick(PhotoListAdapter.PhotoListViewHolder view, int position) {

    }

    @Override
    public void onRequestGetImagesResponse(List<PhotoModel> photoModelList) {
        hideErrorMessage();
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onBookmarkResponse(List<PhotoModel> photoModelList) {
        hideErrorMessage();
    }

    @Override
    public void onBookmarkFail(String message) {

    }

    @Override
    public void onClick(View view) {

    }
}
