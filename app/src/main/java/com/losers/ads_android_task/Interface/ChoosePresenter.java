package com.losers.ads_android_task.Interface;

import android.annotation.SuppressLint;

import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment.EstablishmentsResponse;
import com.losers.ads_android_task.Network.NetworkClient;
import com.losers.ads_android_task.Network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;


interface ChooseInterface {

  void getEstablishmentList(final Double lat, final Double log);
}

public class ChoosePresenter implements ChooseInterface {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private CommonBaseView mCommonBaseView;

  public ChoosePresenter(CommonBaseView mCommonBaseView) {
    this.mCommonBaseView = mCommonBaseView;
  }

  //lat=28.613935&lon=77.229906&category=1&sort=cost&order=asc
  private Observable<EstablishmentsResponse> getComicObservable(final Double lat, final Double log) {
    return NetworkClient
        .getRetrofit()
        .create(NetworkInterface.class)
        .getEstablishmentList(lat, log)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }


  public void clear() {
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }

  @SuppressLint("CheckResult")
  @Override
  public void getEstablishmentList(final Double lat, final Double log) {

    getComicObservable(lat, log)
        .subscribeWith(new DisposableObserver<EstablishmentsResponse>() {
          @Override
          public void onNext(EstablishmentsResponse restaurantsResponse) {
            mCommonBaseView.success(restaurantsResponse, null);
          }

          @Override
          public void onError(Throwable e) {
            mCommonBaseView.failure(e);
          }

          @Override
          public void onComplete() {

          }
        });
//    Observable.range(1, getListCount())
//        .concatMap((Integer index) ->
//            {
//              return getComicObservable(index)
//                  .map((EstablishmentsResponse response) -> {
//                    return mObjectList.add(response);
//                  });
//            },
//            1
//        )
//        .subscribeWith(new DisposableObserver<Boolean>() {
//          @Override
//          public void onNext(Boolean aBoolean) {
//
//          }
//
//          @Override
//          public void onError(Throwable e) {
//
//          }
//
//          @Override
//          public void onComplete() {
//            mCommonBaseView.success(mObjectList, null);
//
//          }
//        });

  }
}

