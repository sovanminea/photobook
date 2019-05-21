package me.sovanminea.photobook.ui.mvp;

public class DetailFragmentVP {

    public interface DetailFramentPresenter{
        void setFragmentLoaded();
    }

    public interface DetailFragmentView{
        void onFragmentLoaded();
    }

}
