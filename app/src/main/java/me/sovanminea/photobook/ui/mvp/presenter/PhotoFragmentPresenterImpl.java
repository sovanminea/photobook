package me.sovanminea.photobook.ui.mvp.presenter;

import java.util.List;

import io.realm.Realm;
import me.sovanminea.photobook.listener.BaseResponseListener;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.PhotoFragmentVP;
import me.sovanminea.photobook.ui.mvp.interactor.PhotoFragmentInteractorImpl;

public class PhotoFragmentPresenterImpl implements PhotoFragmentVP.PhotoFragmentPresenter, BaseResponseListener<List<PhotoModel>> {
    private Realm realm;
    private PhotoModel result;

    private PhotoFragmentVP.PhotoFragmentView photoFragmentView;
    private PhotoFragmentVP.PhotoFragmentInteractor photoFragmentInteractor;

    public PhotoFragmentPresenterImpl(PhotoFragmentVP.PhotoFragmentView homeFragmentView) {
        this.photoFragmentView = homeFragmentView;
        this.photoFragmentInteractor = new PhotoFragmentInteractorImpl(this);
        realm = Realm.getDefaultInstance();
    }

    private List<PhotoModel> syncBookmark(List<PhotoModel> photoModelList) {
        for (int i = 0; i < photoModelList.size(); i++) {
            result = realm.where(PhotoModel.class).equalTo("id", photoModelList.get(i).getId()).findFirst();
            if (result != null)
                photoModelList.get(i).setBookmark(true);
        }
        return photoModelList;
    }

    @Override
    public void requestGetImages(int page) {
        photoFragmentInteractor.onRequestGetImages(page);
    }

    @Override
    public void onError(String message) {
        photoFragmentView.onError(message);
    }

    @Override
    public void onSuccess(List<PhotoModel> data) {
        photoFragmentView.onRequestGetImagesResponse(syncBookmark(data));
    }
}
