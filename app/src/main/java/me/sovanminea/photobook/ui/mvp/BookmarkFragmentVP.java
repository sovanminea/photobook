package me.sovanminea.photobook.ui.mvp;

import java.util.List;

import me.sovanminea.photobook.listener.BookmarkOperationListener;
import me.sovanminea.photobook.model.PhotoModel;

public class BookmarkFragmentVP {

    public interface BookmarkFragmentPresenter {
        void getBookmarkData(int page);

        void getBookmarkData(boolean shouldReset);
    }

    public interface BookmarkFragmentInteractor {
        void resetData();
        void getBookmarkData(int page, BookmarkOperationListener listener);
    }

    public interface BookmarkFragmentView{
        void onBookmarkResponse(List<PhotoModel> photoModelList);
        void onBookmarkFail(String message);
    }

}
