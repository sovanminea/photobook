package me.sovanminea.photobook.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;
import me.sovanminea.photobook.ui.mvp.presenter.DetailFragmentPresenterImpl;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DetailFragment extends Fragment implements View.OnClickListener, DetailFragmentVP.DetailFragmentView {

    private PhotoModel model;
    private View bookmark;
    private DetailFragmentVP.DetailFragmentPresenter detailFragmentPresenter;
    private DetailFragmentVP.OnFragmentInteractionListener onFragmentInteractionListener;

    public void setPhotoModel(PhotoModel model) {
        this.model = model;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.image_view);
        Glide.with(this)
                .load(model.getDownloadUrl())
                .thumbnail(/*sizeMultiplier=*/ 0.25f)
                .transition(withCrossFade())
                .apply(RequestOptions
                        .placeholderOf(R.color.image_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .dontAnimate()
                        .dontTransform()
                )
                .into(imageView);

        bookmark = view.findViewById(R.id.bookmark);
        View back = view.findViewById(R.id.back);

        bookmark.setOnClickListener(this);
        back.setOnClickListener(this);

        detailFragmentPresenter = new DetailFragmentPresenterImpl(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailFragmentVP.OnFragmentInteractionListener) {
            onFragmentInteractionListener = (DetailFragmentVP.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bookmark) {
            bookmark.setActivated(!bookmark.isActivated());
        } else if (view.getId() == R.id.back) {
            onFragmentInteractionListener.onExit();
        }
    }

    @Override
    public void onFragmentLoaded() {

    }

}
