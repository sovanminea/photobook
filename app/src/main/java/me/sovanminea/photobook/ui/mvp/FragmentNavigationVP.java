package me.sovanminea.photobook.ui.mvp;

import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;

public class FragmentNavigationVP {

    public interface View {
        void attachPresenter(Presenter presenter);

    }

    public interface Presenter {
        void getPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel photoModel, int position);
        void getBookmarkClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel photoModel);
    }

}
