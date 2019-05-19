package me.sovanminea.photobook.ui.mvp;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;

public class Home {

    public interface HomePresenter {
        void getImages(int page);
    }

    public interface HomeInteractor {
        void onGetImages(int page);
    }

    public interface HomeView {
        void onImagesResponse(List<PhotoModel> photoListResponse);
        void onError(String message);
    }
}
