package me.sovanminea.photobook.ui.mvp;

import me.sovanminea.photobook.model.PhotoModel;

public class FragmentNavigationVP {

    public interface View {
        void attachPresenter(Presenter presenter);

    }

    public interface Presenter {
        void getPhotoClicked(PhotoModel photoModel);
    }

}
