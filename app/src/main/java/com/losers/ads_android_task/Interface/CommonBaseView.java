package com.losers.ads_android_task.Interface;

public interface CommonBaseView {

  void onUnknownError(String error);

  void onTimeout();

  void onSuccess(final Object object, final Object object2, final Object object3);

  void onError(Throwable e);
}

