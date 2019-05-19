package me.sovanminea.photobook.ui.activity;

import android.os.Bundle;

import me.sovanminea.photobook.R;
import me.sovanminea.photobook.ui.mvp.Home;

public class HomeActivity extends BaseActivity {

    private Home.HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        mHomePresenter
    }
}
