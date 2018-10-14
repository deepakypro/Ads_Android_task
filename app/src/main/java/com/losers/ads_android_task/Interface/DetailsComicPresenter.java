package com.losers.ads_android_task.Interface;

import static com.losers.ads_android_task.Utils.MiscUtils.getListCount;

import android.annotation.SuppressLint;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.Network.NetworkClient;
import com.losers.ads_android_task.Network.NetworkInterface;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;


interface DetailsComicInterface {

  void setCount(final int count);
}

public class DetailsComicPresenter implements DetailsComicInterface {

  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  private CommonBaseView mCommonBaseView;

  public DetailsComicPresenter(CommonBaseView mCommonBaseView) {
    this.mCommonBaseView = mCommonBaseView;
  }

  public Maybe<Integer> getCountObservable(final int count) {
    return Single
        .just(count)
        .filter(integer -> integer % 5 == 0);
  }

  @SuppressLint("CheckResult")
  @Override
  public void setCount(int count) {
    getCountObservable(count).subscribeWith(new DisposableMaybeObserver<Integer>() {
      @Override
      public void onSuccess(Integer integer) {
        if (mCommonBaseView != null) {
          mCommonBaseView.onSuccess(null, null);
        }
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }


  public void clear() {
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }
}
