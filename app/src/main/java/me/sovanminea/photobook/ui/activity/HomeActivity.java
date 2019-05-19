package me.sovanminea.photobook.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.listener.LoadImageListener;
import me.sovanminea.photobook.listener.OnItemClickListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.ItemOffsetDecoration;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.animator.SlideInUpAnimator;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements Home.HomeView, LoadImageListener, OnItemClickListener {

    private Home.HomePresenter mHomePresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private PhotoListAdapter mPhotoListAdapter;
    private List<PhotoModel> mItemList = new ArrayList<>();

    private int page = 1;

    private void setupRecycler() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mPhotoListAdapter = new PhotoListAdapter(getApplicationContext(), mItemList, recyclerView, this, this);
        recyclerView.setAdapter(mPhotoListAdapter);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(5));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenterImpl(this);

        swipeRefreshLayout = findViewById(R.id.swp);
        recyclerView = findViewById(R.id.photo_list);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);
        swipeRefreshLayout.setRefreshing(true);

        setupRecycler();

    }

    @Override
    public void onLoadFirst() {
        mHomePresenter.getImages(page);
    }

    @Override
    public void onImagesResponse(List<PhotoModel> photoListResponse) {
        swipeRefreshLayout.setRefreshing(false);
        mPhotoListAdapter.addItems(photoListResponse);
        page++;
    }

    @Override
    public void onError(String message) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        mPhotoListAdapter.enableLoadingBottom();
        mHomePresenter.getImages(page);
    }


    @Override
    public void onItemClick(View view, int position) {

    }
}
