package me.sovanminea.photobook.restclient;

import me.sovanminea.photobook.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Retrofit.Builder sInstance;

    public static Retrofit.Builder getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitManager.class) {
                if (sInstance == null) {
                    sInstance = new Retrofit.Builder();
                }
            }
        }
        return sInstance;
    }

    private static Retrofit getRetrofit(String url) {
        return RetrofitManager.getInstance()
                .client(OKHttpManager.getInstance())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RestApiService getApiService() {
        return RetrofitManager.getRetrofit(BuildConfig.baseUrl).create(RestApiService.class);
    }
}