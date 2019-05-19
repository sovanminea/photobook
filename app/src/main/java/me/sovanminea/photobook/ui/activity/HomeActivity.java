package me.sovanminea.photobook.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements Home.HomeView {

    private Home.HomePresenter mHomePresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private void setupRecycler(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.getImages();

        swipeRefreshLayout = findViewById(R.id.swp);
        recyclerView = findViewById(R.id.photo_list);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1, R.color.refresh_progress_2, R.color.refresh_progress_3);
        swipeRefreshLayout.setRefreshing(true);

        setupRecycler();

    }

    @Override
    public void onImagesResponse(List<PhotoModel> photoListResponse) {

    }

    @Override
    public void onError(String message) {
        Log.d("ERROR", message);
    }

}
