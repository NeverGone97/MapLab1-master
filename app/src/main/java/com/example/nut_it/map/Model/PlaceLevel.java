package com.example.nut_it.map.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HoangLe on 3/23/2017.
 */

public class PlaceLevel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("place_id")
    @Expose
    private Integer placeId;
    @SerializedName("level_id")
    @Expose
    private Integer levelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
