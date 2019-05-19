package me.sovanminea.photobook.restclient;

import android.support.annotation.NonNull;

import java.io.IOException;

import me.sovanminea.photobook.PhotoBookApplication;
import me.sovanminea.photobook.util.NetworkUtil;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpManager {
    private static OkHttpClient sInstance;

    public static OkHttpClient getInstance() {
        if (sInstance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            sInstance = new OkHttpClient().newBuilder()
                    .addInterceptor(logging)
                    .cache(new Cache(PhotoBookApplication.getInstance().getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                    .addInterceptor(new RewriteRequestInterceptor())
                    .addNetworkInterceptor(new RewriteResponseCacheControlInterceptor())
                    .build();
        }
        return sInstance;
    }

    private static class RewriteResponseCacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            int maxStale = 60 * 60 * 24 * 5;
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder().header("Cache-Control", "public, max-age=120, max-stale=" + maxStale).build();
        }
    }

    private static class RewriteRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            int maxStale = 60 * 60 * 24 * 5;
            Request request;
            if (NetworkUtil.isNetworkAvailable(PhotoBookApplication.getInstance().getBaseContext())) {
                request = chain.request();
            } else {
                request = chain.request().newBuilder().header("Cache-Control", "max-stale=" + maxStale).build();
            }
            return chain.proceed(request);
        }
    }
}
