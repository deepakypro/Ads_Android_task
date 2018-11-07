
package com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstablishmentsResponse {

    @SerializedName("establishments")
    @Expose
    private List<Establishment> establishments = null;

    public List<Establishment> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
    }

}
