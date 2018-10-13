package com.losers.ads_android_task.Interface;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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

  void comicList(final int comicNumber);
}

public class ComicListPresenter implements ComicListInterface {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private CommonBaseView mCommonBaseView;

  public ComicListPresenter(CommonBaseView mCommonBaseView) {
    this.mCommonBaseView = mCommonBaseView;
  }

  public Observable<Object> getComicObservable(final int comicNumber) {
    return NetworkClient
        .getRetrofit()
        .create(NetworkInterface.class)
        .getComicDetails(comicNumber)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

//  private void fa(){
//    List<Object> mObjectList=new ArrayList<>();
//    Observable.range(1, 50)
//        .flatMap(index ->      // for every index make new request
//            getComicObservable(index) // this shall return Observable<Response>
//                .retry(2)      // on error => retry this request N times
//        )
//        .subscribe(response -> mCommonBaseView.onSuccess(response,null,null));
//  }


  @SuppressLint("CheckResult")
  @Override
  public void comicList(int comicNumber) {
    List<Object> mObjectList = new ArrayList<>();
    Observable.range(1, 50)
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

            Log.d("TAGDEepak", mObjectList.size() + " ");
          }
        });

//    getComicObservable(comicNumber).subscribeWith(new DisposableObserver<Object>() {
//      @Override
//      public void onNext(Object o) {
//        Log.d("TAGDEepak", o + " ");
//      }
//
//      @Override
//      public void onError(Throwable e) {
//
//      }
//
//      @Override
//      public void onComplete() {
//
//      }
//    });
  }


  public void clear() {
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }
}
