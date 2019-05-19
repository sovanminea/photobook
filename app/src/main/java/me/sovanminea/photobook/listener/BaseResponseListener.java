package me.sovanminea.photobook.listener;

public interface BaseResponseListener<T> {

    void onError(String message);

    void onSuccess(T data);

}
