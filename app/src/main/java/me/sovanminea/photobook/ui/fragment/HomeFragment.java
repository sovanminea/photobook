package me.sovanminea.photobook.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.HomeFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.HomeFragmentPresenterImpl;

public class HomeFragment extends Fragment implements HomeFragmentVP.HomeFragmentView {
    private OnHomeFragmentInteractionListener mListener;
    private HomeFragmentVP.HomeFragmentPresenter mHomeFragmentPresenter;

    private final int PHOTO_TAB_INDEX = 0;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeFragmentPresenter = new HomeFragmentPresenterImpl(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        mHomeFragmentPresenter.setupPagerAdapter(getFragmentManager());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentInteractionListener) {
            mListener = (OnHomeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPagerAdapterReady(TabAdapter tabAdapter) {
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void getPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel model, int position) {
        mListener.onPhotoClicked(viewHolder, model, position);
    }

    public void updateCreateBookmark(int position) {
        ((PhotoFragment) ((TabAdapter) viewPager.getAdapter()).getItem(PHOTO_TAB_INDEX)).updateItem(position);
    }

    public void updateDeleteBookmark(int position) {
        ((PhotoFragment) ((TabAdapter) viewPager.getAdapter()).getItem(PHOTO_TAB_INDEX)).updateItem(position);
    }

    public interface OnHomeFragmentInteractionListener {
        void onPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel model, int position);
    }
}
