package com.losers.ads_android_task.Activity;


import static com.losers.ads_android_task.Utils.Constants.USER_CHOICE_CATEGORY;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.losers.ads_android_task.Activity.Adapter.ProductListAdapter;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.Interface.ProductListPresenter;
import com.losers.ads_android_task.Model.CategoriesModel;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Restaurant;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.RestaurantsResponse;
import com.losers.ads_android_task.R;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements CommonBaseView {


  @BindView(R.id.shuffle)
  ImageButton mShuffle;
  @BindView(R.id.toolbar2)
  Toolbar mToolbar2;
  @BindView(R.id.rv)
  RecyclerView mRv;

  Unbinder mUnbinder;
  private ProductListAdapter mProductListAdapter;
  private List<Restaurant> restaurants = new ArrayList<>();
  //https://developers.zomato.com/api/v2.1/search?entity_type=city&q=Delivery&start=3&count=4&lat=28.613935&lon=77.229906&category=1&sort=cost&order=asc
  private ProductListPresenter mProductListPresenter;

  private String mUserSelectedCategory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);
    mUnbinder = ButterKnife.bind(this);
    mProductListPresenter = new ProductListPresenter(this);

    Bundle mBundle = getIntent().getExtras();
    if (mBundle == null) {
      finish();
      return;
    }
    mUserSelectedCategory = mBundle.getString(USER_CHOICE_CATEGORY);

    mProductListPresenter
        .getResataurantDetails(28.613935, 77.229906, "asc", "city", "cost", 0, 3,
            mUserSelectedCategory);
    setCategoryAdapter();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mProductListPresenter.clear();
    mUnbinder.unbind();
  }

  private void setCategoryAdapter() {
    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getApplicationContext(),
        LinearLayoutManager.VERTICAL, false);

    mRv.setLayoutManager(horizontalLayoutManagaer);
//    mRv.setLayoutManager(new GridLayoutManager(this, 2));

    //mEditorChoiceTrending.setHasFixedSize(true);
    mRv.setHasFixedSize(true);

    mProductListAdapter = new ProductListAdapter(
        getApplicationContext(), restaurants);

    mRv.setAdapter(mProductListAdapter);
  }


  @OnClick({R.id.shuffle, R.id.toolbar2})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.shuffle:
        break;
      case R.id.toolbar2:
        break;
    }
  }

  @Override
  public void success(Object object, Object object2) {

    RestaurantsResponse mRestaurantsResponse = (RestaurantsResponse) object;

    restaurants.addAll(mRestaurantsResponse.getRestaurants());
    mProductListAdapter.notifyDataSetChanged();
  }

  @Override
  public void failure(Throwable throwable) {

  }
}
