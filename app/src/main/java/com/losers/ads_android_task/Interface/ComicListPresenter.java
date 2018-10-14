package com.losers.ads_android_task.Interface;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.Network.NetworkClient;
import com.losers.ads_android_task.Network.NetworkInterface;
import com.losers.ads_android_task.R;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

interface ComicListInterface {

  void comicList(final int comicNumber,final boolean isRefresh);
}

public class ComicListPresenter implements ComicListInterface {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private CommonBaseView mCommonBaseView;

  public ComicListPresenter(CommonBaseView mCommonBaseView) {
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
        .flatMap(index ->      // for every index make new request
                getComicObservable(index) // this shall return Observable<Response>
                    .retry(1)      // on error => retry this request N times
                    .map(response -> mObjectList.add(response)), // save and report success
            1                  // limit concurrency to single request-save
        )
        .subscribeWith(new DisposableObserver<Boolean>() {
          @Override
          public void onNext(Boolean aBoolean) {
//            Log.d("TAGDEepak", mObjectList.size() + " "+aBoolean);
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {
            mCommonBaseView.onSuccess(mObjectList,isRefresh);
            Log.d("TAGDEepak", mObjectList.size() + " ");
          }
        });


  }


  public void clear() {
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }
}
