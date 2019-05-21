package me.sovanminea.photobook.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.DetailFragmentPresenterImpl;

public class DetailFragment extends Fragment implements View.OnClickListener, DetailFragmentVP.DetailFragmentView {

    private View back;
    private View bookmark;

    private DetailFragmentVP.DetailFramentPresenter detailFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookmark = view.findViewById(R.id.bookmark);
        back = view.findViewById(R.id.back);

        bookmark.setOnClickListener(this);
        back.setOnClickListener(this);

        detailFragmentPresenter = new DetailFragmentPresenterImpl(this);
        detailFragmentPresenter.setFragmentLoaded();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bookmark) {
            bookmark.setActivated(!bookmark.isActivated());
        } else if (view.getId() == R.id.back) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentLoaded() {

    }
}
