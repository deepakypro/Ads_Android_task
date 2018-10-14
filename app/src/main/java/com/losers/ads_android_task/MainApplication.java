package com.losers.ads_android_task;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.ads.MobileAds;

public class MainApplication extends Application {

  private static MainApplication enableMultiDex;
  public static Context context;

  //private DatabaseReference mDatabase;


  public MainApplication() {
    enableMultiDex = this;
  }

  public static MainApplication getInstance() {
    return enableMultiDex;
  }


  @Override
  public void onCreate() {
    super.onCreate();
    //Branch.getAutoInstance(this);
    // mDatabase = FirebaseDatabase.getInstance().getReference();

    MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

  }
}


