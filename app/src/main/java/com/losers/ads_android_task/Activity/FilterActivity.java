package com.losers.ads_android_task.Activity;


import static com.losers.ads_android_task.Utils.Constants.USER_CHOICE_CATEGORY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.R;

public class FilterActivity extends AppCompatActivity implements CommonBaseView {

  Unbinder mUnbinder;
  @BindView(R.id.price_tv)
  TextView mPriceTv;
  @BindView(R.id.seekBar)
  SeekBar mSeekBar;
  @BindView(R.id.percentage_sb_tv)
  TextView mPercentageTv;


  private String mUserSelectedCategory;
//  private ProductListPresenter mProductListPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_filter);
    mUnbinder = ButterKnife.bind(this);
    Bundle mBundle = getIntent().getExtras();
    if (mBundle == null) {
      finish();
      return;
    }
    mUserSelectedCategory = mBundle.getString(USER_CHOICE_CATEGORY);
    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        int val =
            (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
        mPercentageTv.setText("" + progress);
        mPercentageTv.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
        setPriceTvText(progress);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
        mPercentageTv.setVisibility(View.VISIBLE);

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        mPercentageTv.setVisibility(View.GONE);
      }
    });
  }

  private void setPriceTvText(int progress) {

    if (progress > 0 && progress <= 25) {

      mPriceTv.setText("Price matter");
    } else if (progress > 25 && progress <= 50) {
      mPriceTv.setText("Affordable");
    } else if (progress > 50 && progress <= 75) {
      mPriceTv.setText("Are you Serious ?");
    } else if (progress > 75 && progress <= 100) {
      mPriceTv.setText("Price doesn't matter");
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
  }

  @OnClick(R.id.skip_btn)
  public void onViewClicked() {
    startActivity(new Intent(getApplicationContext(), ProductListActivity.class));
  }

  @Override
  public void success(Object object, Object object2) {

  }

  @Override
  public void failure(Throwable throwable) {

  }

  @OnClick({R.id.skip_btn, R.id.continue_btn})
  public void onViewClicked(View view) {
    Intent mIntent = new Intent(getApplicationContext(), FilterActivity.class);
    switch (view.getId()) {
      case R.id.skip_btn:

        mIntent.putExtra(USER_CHOICE_CATEGORY, mUserSelectedCategory);
        startActivity(mIntent);
        break;
      case R.id.continue_btn:

        mIntent.putExtra(USER_CHOICE_CATEGORY, mUserSelectedCategory);
        startActivity(mIntent);
        break;
    }
  }
}
