package me.sovanminea.photobook.ui.mvp.interactor;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.listener.BaseResponseListener;

import me.sovanminea.photobook.restclient.RetrofitManager;
import me.sovanminea.photobook.ui.mvp.Home;

public class HomeInteractorImpl extends RequestInteractorImpl<List<PhotoModel>> implements Home.HomeInteractor {

    public HomeInteractorImpl(BaseResponseListener<List<PhotoModel>> listener) {
        super(listener);
    }

    @Override
    public void onGetImages() {
        mCall = RetrofitManager.getApiService().getPhotoList();
        mCall.enqueue(this);
    }
}
