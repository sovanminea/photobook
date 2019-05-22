package me.sovanminea.photobook.ui.mvp;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;

public class PhotoFragmentVP {

    public interface PhotoFragmentPresenter {
        void requestGetImages(int page);
    }

    public interface PhotoFragmentInteractor {
        void onRequestGetImages(int page);
    }

    public interface PhotoFragmentView {
        void onRequestGetImagesResponse(List<PhotoModel> photoModelList);

        void onError(String message);
    }

}
