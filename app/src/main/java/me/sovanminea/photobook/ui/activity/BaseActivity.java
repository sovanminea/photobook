package me.sovanminea.photobook.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import me.sovanminea.photobook.PhotoBookApplication;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        PhotoBookApplication.setContext(this);
    }
}
