package me.sovanminea.photobook.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.transition.Fade;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.animator.DetailTransition;
import me.sovanminea.photobook.ui.fragment.DetailFragment;
import me.sovanminea.photobook.ui.fragment.HomeFragment;
import me.sovanminea.photobook.ui.mvp.DetailFragmentVP;

public class HomeHomeFragmentActivity extends BaseActivity implements HomeFragment.OnHomeFragmentInteractionListener, DetailFragmentVP.DetailFragmentPresenter, DetailFragmentVP.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
    }

    @Override
    public void onPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel model) {
        if (detailFragment == null)
            detailFragment = new DetailFragment();
        detailFragment.setPhotoModel(model);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailFragment.setSharedElementEnterTransition(new DetailTransition());
            detailFragment.setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
            detailFragment.setSharedElementReturnTransition(new DetailTransition());
        }

        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.animator.slide_in, R.animator.slide_down, R.animator.slide_in, R.animator.slide_down)
                .addSharedElement(viewHolder.imageView, "detailTransition")
                .addToBackStack(null)
                .add(R.id.frame_layout, detailFragment)
                .commit();
    }

    @Override
    public void setFragmentLoaded() {

    }

    @Override
    public void onExit() {
//        fragmentManager.beginTransaction().remove(detailFragment).commit();
        fragmentManager.popBackStack();
    }
}