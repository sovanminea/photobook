package me.sovanminea.photobook.ui.mvp.interactor;

import java.util.List;

import me.sovanminea.photobook.listener.BaseResponseListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.restclient.RetrofitManager;
import me.sovanminea.photobook.ui.mvp.PhotoFragmentVP;

public class PhotoFragmentInteractorImpl extends RequestInteractorImpl<List<PhotoModel>> implements PhotoFragmentVP.PhotoFragmentInteractor {

    public PhotoFragmentInteractorImpl(BaseResponseListener<List<PhotoModel>> mListener) {
        super(mListener);
    }

    @Override
    public void onRequestGetImages(int page) {
        mCall = RetrofitManager.getApiService().getPhotoList(page);
        mCall.enqueue(this);
    }

}
