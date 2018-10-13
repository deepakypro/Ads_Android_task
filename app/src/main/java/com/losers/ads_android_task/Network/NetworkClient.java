package com.losers.ads_android_task.Network;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class NetworkClient {


 private static final String BASE_URL = "https://xkcd.com/";


  public static Retrofit retrofit;

  public void NetworkClient() {

  }

  public static Retrofit getRetrofit() {

    if (retrofit == null) {

      OkHttpClient.Builder client = new OkHttpClient.Builder();
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      client.addInterceptor(loggingInterceptor);
//      OkHttpClient.Builder builder = new OkHttpClient.Builder();
//      OkHttpClient okHttpClient = builder.build();

      retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client.build())
          .build();

    }

    return retrofit;
  }
}
