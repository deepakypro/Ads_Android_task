package com.losers.ads_android_task.Network;

import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment.EstablishmentsResponse;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.RestaurantsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {
  //http://xkcd.com/614/info.0.json

  //order : asc,desc
  //sort: cost,rating,real_distance
  //https://developers.zomato.com/api/v2.1/search?entity_type=city&q=Delivery&start=0&count=2&lat=28.613935&lon=77.229906&category=1&sort=cost&order=asc
  @GET("/api/v2.1/search")
  Observable<RestaurantsResponse> getRestaurantDetails(@Query("lat") Double lat,
      @Query("lon") Double lon, @Query("order") String order,
      @Query("entity_type") String entity_type, @Query("sort") String sort,
      @Query("start") int start, @Query("count") int count, @Query("category") String category);

  ///api/v2.1/establishments?lat=28.613935&lon=77.229906
  @GET("/api/v2.1/establishments")
  Observable<EstablishmentsResponse> getEstablishmentList(@Query("lat") Double lat,
      @Query("lon") Double log);
}
