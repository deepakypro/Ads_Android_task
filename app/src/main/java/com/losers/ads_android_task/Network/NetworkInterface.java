package com.losers.ads_android_task.Network;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkInterface {
  //http://xkcd.com/614/info.0.json

  @GET("/{comic_number}/info.0.json")
  Observable<Object> getComicDetails(@Path("comic_number") int comic_number);
}
