package me.sovanminea.photobook.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.ui.adapter.TabAdapter;
import me.sovanminea.photobook.ui.mvp.Home;
import me.sovanminea.photobook.ui.mvp.presenter.HomePresenterImpl;

public class HomeActivity extends BaseActivity implements Home.HomeView {

    private Home.HomePresenter mHomePresenter;

    private TabLayout tabLayout;
    private ViewPager viewPager;

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

}
