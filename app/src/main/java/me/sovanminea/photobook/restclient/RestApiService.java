package me.sovanminea.photobook.restclient;

import java.util.List;

import me.sovanminea.photobook.model.PhotoModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {

    @GET("v2/list")
    Call<List<PhotoModel>> getPhotoList(@Query("page") int page);

}
