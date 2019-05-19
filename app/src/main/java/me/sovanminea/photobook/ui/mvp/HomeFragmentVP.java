package me.sovanminea.photobook.ui.mvp;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;

public class HomeFragmentVP {

    public interface HomeFragmentPresenter {
        void requestGetImages(int page);
    }

    public interface HomeFragmentInteractor {
        void onRequestGetImages(int page);
    }

    public interface HomeFragmentView {
        void onRequestGetImagesResponse(List<PhotoModel> photoModelList);

        void onError(String message);
    }

}
