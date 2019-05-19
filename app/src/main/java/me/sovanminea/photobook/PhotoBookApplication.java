package me.sovanminea.photobook;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class PhotoBookApplication extends Application {

    private static Application application;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static Application getInstance() {
        if (application == null) {
            synchronized (PhotoBookApplication.class) {
                if (application == null) {
                    application = new Application();
                }
            }
        }
        return application;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context context) {
        mContext = context;
    }

}
