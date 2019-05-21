package me.sovanminea.photobook.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import me.sovanminea.photobook.R;
import me.sovanminea.photobook.model.PhotoModel;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.fragment.DetailFragment;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements Home.HomeView {

    private Home.HomePresenter mHomePresenter;

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePresenter = new HomePresenterImpl(this);

        appBarLayout = findViewById(R.id.app_bar);
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
    public void getPhotoClicked(PhotoModel model) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.slide_in, R.animator.slide_down, R.animator.slide_in, R.animator.slide_down)
                .addToBackStack("Detail")
                .replace(R.id.frame_layout, new DetailFragment())
                .commit();

    }

}
