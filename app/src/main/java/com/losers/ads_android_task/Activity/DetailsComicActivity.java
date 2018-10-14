package com.losers.ads_android_task.Activity;


import static com.losers.ads_android_task.Utils.Constants.ADS_UNIT;
import static com.losers.ads_android_task.Utils.Constants.ALT;
import static com.losers.ads_android_task.Utils.Constants.DATE;
import static com.losers.ads_android_task.Utils.Constants.IMAGE;
import static com.losers.ads_android_task.Utils.Constants.NEWS;
import static com.losers.ads_android_task.Utils.Constants.NUM;
import static com.losers.ads_android_task.Utils.Constants.TITLE;
import static com.losers.ads_android_task.Utils.Constants.TRANSCRIPT;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.adsnative.ads.ANAdListener;
import com.adsnative.ads.ANNativeAd;
import com.adsnative.ads.NativeAdUnit;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.Interface.DetailsComicPresenter;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.AdsClass;
import com.losers.ads_android_task.Utils.MiscUtils;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import java.util.concurrent.atomic.AtomicInteger;

public class DetailsComicActivity extends AppCompatActivity implements CommonBaseView {

  @BindView(R.id.num_tv)
  TextView mNumTv;
  @BindView(R.id.date_text_tv)
  TextView mDateTextTv;
  @BindView(R.id.alt_text_tv)
  TextView mAltTextTv;
  @BindView(R.id.news_text_tv)
  TextView mNewsTextTv;
  @BindView(R.id.transcript_text_tv)
  TextView mTranscriptTextTv;
  @BindView(R.id.close)
  Button mClose;
  @BindView(R.id.relativeLayout)
  RelativeLayout mRelativeLayout;
  private boolean isAdsShown = false;
  public static AtomicInteger sUserClickCountInteger = new AtomicInteger(0);
  @BindView(R.id.mainImage)
  ImageView mMainImage;
  @BindView(R.id.title)
  TextView mTitle;
  @BindView(R.id.summary)
  TextView mSummary;
  @BindView(R.id.promotedBy)
  TextView mPromotedBy;
  @BindView(R.id.callToAction)
  Button mCallToAction;
  private ANNativeAd mNativeAd;
  @BindView(R.id.leadership_back_button)
  ImageButton mLeadershipBackButton;
  @BindView(R.id.toolbar_tv)
  TextView mToolbarTv;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.imageview)
  ImageView mImageview;
  @BindView(R.id.text_tv)
  TextView mTextTv;
  @BindView(R.id.date_tv)
  TextView mDateTv;
  @BindView(R.id.alt_tv)
  TextView mAltTv;
  @BindView(R.id.news_tv)
  TextView mNewsTv;
  @BindView(R.id.transcript_tv)
  TextView mTranscriptTv;

  private final MiscUtils mMiscUtils = new MiscUtils();
  Unbinder mUnbinder;
  private View mComicView, mAdsView;
  private DetailsComicPresenter mDetailsComicPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details_comic);
    mUnbinder = ButterKnife.bind(this);
    mComicView = findViewById(R.id.layout_comic_view);
    mAdsView = findViewById(R.id.layout_ads_view);
    mDetailsComicPresenter = new DetailsComicPresenter(this);
    mDetailsComicPresenter.setCount(sUserClickCountInteger.get());
    Bundle mBundle = getIntent().getExtras();
    if (mBundle == null) {
      return;
    }

    String image = mBundle.getString(IMAGE);
    String title = mBundle.getString(TITLE);
    String date = mBundle.getString(DATE);
    String alt = mBundle.getString(ALT);
    int num = mBundle.getInt(NUM);
    String transcript = mBundle.getString(TRANSCRIPT);
    String news = mBundle.getString(NEWS);

    mToolbarTv.setText(title);
    mTextTv.setText(title);
    mDateTv.setText(date);
    mAltTv.setText(alt);
    mNewsTv.setText(news);
    mTranscriptTv.setText(transcript);
    mNumTv.setText(num + "");
    mMiscUtils.setImage(mImageview, image);

    mImageview.setOnClickListener(
        view -> Toast.makeText(getApplicationContext(), alt + "", Toast.LENGTH_SHORT).show());
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (isAdsShown) {
      mNativeAd.loadAd();
    }

  }


  private void loadAds() {
    mNativeAd = new ANNativeAd(this, ADS_UNIT);

    mNativeAd.registerViewBinder(AdsClass.getAnAdViewBinder());
    mNativeAd.setNativeAdListener(new ANAdListener() {
      @Override
      public void onAdLoaded(NativeAdUnit nativeAdUnit) {

        String title = nativeAdUnit.getTitle();
        String summary = nativeAdUnit.getSummary();
        String mPromoted = nativeAdUnit.getPromotedBy();
        String mainImage = nativeAdUnit.getMainImage();
        String cta = nativeAdUnit.getCallToAction();

        mMiscUtils.setImage(mMainImage, mainImage);
        mTitle.setText(title);
        mSummary.setText(summary);
        mPromotedBy.setText(mPromoted);

      }

      @Override
      public void onAdFailed(String message) {

      }

      @Override
      public void onAdImpressionRecorded() {
      }

      @Override
      public boolean onAdClicked(NativeAdUnit nativeAdUnit) {

        return false;
      }


    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (isAdsShown) {
      mNativeAd.destroy();
    }

    mUnbinder.unbind();
  }

  @OnClick({R.id.leadership_back_button, R.id.toolbar_tv})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.leadership_back_button:
        finish();
        break;

    }
  }

  @OnClick(R.id.close)
  public void onViewClicked() {
    mAdsView.setVisibility(View.GONE);
    mComicView.setVisibility(View.VISIBLE);
  }

  @Override
  public void onUnknownError(String error) {

  }

  @Override
  public void onTimeout() {

  }

  @Override
  public void onSuccess(Object object, Object object1) {
    isAdsShown = true;
    mComicView.setVisibility(View.GONE);
    mAdsView.setVisibility(View.VISIBLE);
    if (isAdsShown) {
      loadAds();
      mNativeAd.loadAd();
    }

  }

  @Override
  public void onError(Throwable e) {

  }
}
