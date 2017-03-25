package com.example.nut_it.map.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallService {
    @GET("/api/4.2/category")
    Call<ListCategory> getListCategory();

    @GET("/api/4.2/place")
    Call<ListPlace> getListPlace();
}
