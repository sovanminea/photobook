package me.sovanminea.photobook.ui.activity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements Home.HomeView {

    private Home.HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenterImpl(this);
        mHomePresenter.getImages();
    }

    @Override
    public void onImagesResponse(List<PhotoModel> photoListResponse) {

    }

    @Override
    public void onError(String message) {
        Log.d("ERROR", message);
    }
}
