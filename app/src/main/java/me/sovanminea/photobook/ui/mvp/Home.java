package me.sovanminea.photobook.ui.mvp;

public class Home {

    public interface HomePresenter {
        void getImages();
    }

    public interface HomeInteractor {
        void onGetImages();
    }

    public interface HomeView {
        void onImagesResponse(String img);
    }
}
