package me.sovanminea.photobook.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.transition.Fade;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.PhotoListAdapter;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.animator.DetailTransition;
import me.sovanminea.photobook.ui.fragment.DetailFragment;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements Home.HomeView {

    private Home.HomePresenter mHomePresenter;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenterImpl(this);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        mHomePresenter.setupPagerAdapter(getSupportFragmentManager());
    }

    @Override
    public void onPagerAdapterReady(TabAdapter tabAdapter) {
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void getPhotoClicked(PhotoListAdapter.PhotoListViewHolder viewHolder, PhotoModel model) {
        if (detailFragment == null)
            detailFragment = new DetailFragment();
        detailFragment.setPhotoModel(model);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailFragment.setSharedElementEnterTransition(new DetailTransition());
            detailFragment.setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
            detailFragment.setSharedElementReturnTransition(new DetailTransition());
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.slide_in, R.animator.slide_down, R.animator.slide_in, R.animator.slide_down)
                .addSharedElement(viewHolder.imageView, "detailTransition")
                .addToBackStack(null)
                .replace(R.id.frame_layout, detailFragment)
                .commit();
    }

}
