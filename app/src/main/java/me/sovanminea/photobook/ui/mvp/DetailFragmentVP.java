package me.sovanminea.photobook.ui.mvp;

import me.sovanminea.photobook.model.PhotoModel;

public class DetailFragmentVP {

    public interface DetailFragmentPresenter {
        void createOrDeleteBookmark(boolean shouldCreate, PhotoModel model);
    }

    public interface  DetailFragmentInteractor {
        void createBookmark(PhotoModel model);
        void deleteBookmark(PhotoModel model);
    }

    public interface DetailFragmentView{
    }

    public interface OnFragmentInteractionListener{
        void onExit();
    }

}
