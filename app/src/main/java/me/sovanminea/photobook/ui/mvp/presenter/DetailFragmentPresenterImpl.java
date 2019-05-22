package me.sovanminea.photobook.ui.mvp.presenter;

import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;

public class DetailFragmentPresenterImpl implements DetailFragmentVP.DetailFragmentPresenter {

    DetailFragmentVP.DetailFragmentView detailFragmentView;

    public DetailFragmentPresenterImpl(DetailFragmentVP.DetailFragmentView detailFragmentView) {
        this.detailFragmentView = detailFragmentView;
    }

    @Override
    public void setFragmentLoaded() {
        detailFragmentView.onFragmentLoaded();
    }

}
