package com.losers.ads_android_task.Interface;

import android.annotation.SuppressLint;
import android.util.Log;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.Network.NetworkClient;
import com.losers.ads_android_task.Network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;



interface ComicGridInterface {

  void comicList(final int comicNumber,final boolean isRefresh);
}

public class ComicGridPresenter implements ComicGridInterface {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private CommonBaseView mCommonBaseView;

  public ComicGridPresenter(CommonBaseView mCommonBaseView) {
    this.mCommonBaseView = mCommonBaseView;
  }

  public Observable<ComicResponse> getComicObservable(final int comicNumber) {
    return NetworkClient
        .getRetrofit()
        .create(NetworkInterface.class)
        .getComicDetails(comicNumber)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @SuppressLint("CheckResult")
  @Override
  public void comicList(int comicNumber,final boolean isRefresh) {
    int lastcomicCount = comicNumber + 10;
    List<ComicResponse> mObjectList = new ArrayList<>();



    Observable.range(comicNumber, lastcomicCount)
        .concatMap(index ->
                getComicObservable(index)

                    .map(response -> mObjectList.add(response)),
            1
        )
        .subscribeWith(new DisposableObserver<Boolean>() {
          @Override
          public void onNext(Boolean aBoolean) {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {
            mCommonBaseView.onSuccess(mObjectList,isRefresh);

          }
        });



  }


  public void clear() {
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }
}
