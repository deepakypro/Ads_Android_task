package com.losers.ads_android_task.Interface;

import static com.losers.ads_android_task.Utils.MiscUtils.getListCount;

import android.annotation.SuppressLint;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.RestaurantsResponse;
import com.losers.ads_android_task.Network.NetworkClient;
import com.losers.ads_android_task.Network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

interface ProductListInterface {
//@Query("lat") Double lat,
//      @Query("long") Double log, @Query("order") String order,
//      @Query("entity_type") String entity_type, @Query("sort") String sort,
//      @Query("start") int start, @Query("count") int count, @Query("category") String category);
  void getResataurantDetails(final Double lat,final Double log,final String order,final String entity_type,final String sort,final int start,final int count,final String category);
}

public class ProductListPresenter implements ProductListInterface {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private CommonBaseView mCommonBaseView;

  public ProductListPresenter(CommonBaseView mCommonBaseView) {
    this.mCommonBaseView = mCommonBaseView;
  }

  //lat=28.613935&lon=77.229906&category=1&sort=cost&order=asc
  private Observable<RestaurantsResponse> getComicObservable(final Double lat,final Double log,final String order,final String entity_type,final String sort,final int start,final int count,final String category) {
    return NetworkClient
        .getRetrofit()
        .create(NetworkInterface.class)
        .getRestaurantDetails(lat,log,order,entity_type,sort,start,count,category)
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
  public void getResataurantDetails(final Double lat,final Double log,final String order,final String entity_type,final String sort,final int start,final int count,final String category) {
    List<RestaurantsResponse> mObjectList = new ArrayList<>();

    getComicObservable(lat,log,order,entity_type,sort,start,count,category).subscribeWith(new DisposableObserver<RestaurantsResponse>() {
      @Override
      public void onNext(RestaurantsResponse restaurantsResponse) {
        mCommonBaseView.success(restaurantsResponse, null);
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
//    Observable.range(1, getListCount())
//        .concatMap((Integer index) ->
//            {
//              return getComicObservable(index)
//                  .map((RestaurantsResponse response) -> {
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
