
package com.losers.ads_android_task.Network.ApiResponse.Restaurants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class R  implements Serializable {

    @SerializedName("res_id")
    @Expose
    private Integer resId;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

}
