package com.losers.ads_android_task.Network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class NetworkClient {


 private static final String BASE_URL = "https://developers.zomato.com/";


  public static Retrofit retrofit;

  public void NetworkClient() {

  }

  public static Retrofit getRetrofit() {

    if (retrofit == null) {


      OkHttpClient.Builder client = new OkHttpClient.Builder();
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      client.addInterceptor(loggingInterceptor);


      client.addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
          Request request = chain.request().newBuilder().addHeader("user-key", "a9a7380677025f5b302c3aa587e7877d").build();
          return chain.proceed(request);
        }
      });


      retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .client(client.build())
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();



    }

    return retrofit;
  }
}
