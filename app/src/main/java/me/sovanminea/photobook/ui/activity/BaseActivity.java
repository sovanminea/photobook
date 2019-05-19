package me.sovanminea.photobook.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import me.sovanminea.photobook.PhotoBookApplication;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        PhotoBookApplication.setContext(this);
    }
}
