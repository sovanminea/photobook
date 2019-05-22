package me.sovanminea.photobook.ui.mvp;

public class DetailFragmentVP {

    public interface DetailFragmentPresenter {
        void setFragmentLoaded();
    }

    public interface DetailFragmentView{
        void onFragmentLoaded();
    }

    public interface OnFragmentInteractionListener{
        void onExit();
    }

}
