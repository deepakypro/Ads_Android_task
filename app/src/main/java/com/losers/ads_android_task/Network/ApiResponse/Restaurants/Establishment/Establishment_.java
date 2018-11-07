
package com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Establishment_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;


    private boolean isSelectedByUser;

    public boolean isSelectedByUser() {
        return isSelectedByUser;
    }

    public void setSelectedByUser(boolean selectedByUser) {
        isSelectedByUser = selectedByUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}