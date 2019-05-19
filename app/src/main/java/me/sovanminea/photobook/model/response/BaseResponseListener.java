package me.sovanminea.photobook.model.response;

public interface BaseResponseListener<T> {

    void onError(String message);

    void onSuccess(T data);

}
