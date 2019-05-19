package me.sovanminea.photobook.ui.mvp.interactor;

import android.util.Log;

import me.sovanminea.photobook.listener.BaseResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RequestInteractorImpl<T> implements Callback<T> {

    protected Call<T> mCall;
    protected BaseResponseListener<T> mListener;

    public RequestInteractorImpl(BaseResponseListener<T> mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.d("DDDD",response.body().toString());
        if (response.body() == null) return;
        mListener.onSuccess(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mListener.onError(t.getMessage());
    }

}
