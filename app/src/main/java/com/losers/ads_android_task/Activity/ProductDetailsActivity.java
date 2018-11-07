package com.losers.ads_android_task.Activity;


import static com.losers.ads_android_task.Utils.Constants.RESTAURANT_DETAILS;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Restaurant;
import com.losers.ads_android_task.R;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

  @BindView(R.id.back_btn)
  ImageButton mBackBtn;
  @BindView(R.id.rest_name_tv)
  TextView mRestNameTv;
  @BindView(R.id.viewPager)
  ViewPager mViewPager;
  @BindView(R.id.short_adr_tv)
  TextView mShortAdrTv;
  @BindView(R.id.cousine_tv)
  TextView mCousineTv;
  @BindView(R.id.call_btn)
  ImageButton mCallBtn;
  @BindView(R.id.maps_btn)
  ImageButton mMapsBtn;
  @BindView(R.id.uber_btn)
  ImageButton mUberBtn;
  @BindView(R.id.share_btn)
  ImageButton mShareBtn;
  @BindView(R.id.complete_addr_tv)
  TextView mCompleteAddrTv;
  @BindView(R.id.cost_tv)
  TextView mCostTv;
  @BindView(R.id.menu_tv)
  TextView mMenuTv;
  @BindView(R.id.menu_rv)
  RecyclerView mMenuRv;

  Unbinder mUnbinder;


  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private Restaurant mRestaurant;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_details);
    mUnbinder = ButterKnife.bind(this);

    Bundle mBundle = getIntent().getExtras();
    if (mBundle != null) {
      getQuestionData(mBundle);
    }


  }


  private void getQuestionData(Bundle mBundle) {
    mCompositeDisposable.add(getQuestionDataFromDbObserable(mBundle)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<Restaurant>() {
          @Override
          public void onSuccess(Restaurant restaurant) {
            setRestaurant(restaurant);
            setData();
          }

          @Override
          public void onError(Throwable e) {

          }
        }));
  }


  public Single<Restaurant> getQuestionDataFromDbObserable(Bundle mBundle) {
    return Single.create(emitter -> {
      try {
        Restaurant data1 = (Restaurant) getIntent()
            .getSerializableExtra(RESTAURANT_DETAILS); //mBundle.getString(TEST_COMPLETE_DATA);

        emitter.onSuccess(data1);

      } catch (Exception e) {
        emitter.onError(e);
      }
    });
  }

  public void clear() {
    if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }

  private void setData() {
    mRestNameTv.setText(getRestaurant().getRestaurant().getName());
    mShortAdrTv.setText(getRestaurant().getRestaurant().getLocation().getLocalityVerbose());
    mCompleteAddrTv.setText(getRestaurant().getRestaurant().getLocation().getAddress());
    mCousineTv.setText(getRestaurant().getRestaurant().getCuisines());
    mCostTv.setText(
        getRestaurant().getRestaurant().getCurrency() + " " + getRestaurant().getRestaurant()
            .getAverageCostForTwo() + " for two people (approx)");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    clear();
    mUnbinder.unbind();
  }

  @OnClick(R.id.back_btn)
  public void onViewClicked() {
    finish();
  }

  public Restaurant getRestaurant() {
    return mRestaurant;
  }

  public void setRestaurant(
      Restaurant restaurant) {
    mRestaurant = restaurant;
  }
}
