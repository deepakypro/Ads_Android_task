package com.losers.ads_android_task.Activity;


import static com.losers.ads_android_task.Utils.Constants.USER_CHOICE_CATEGORY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.losers.ads_android_task.Activity.Adapter.EstablishmentAdapter;
import com.losers.ads_android_task.Activity.Adapter.EstablishmentAdapter.ChooseAdapterListner;
import com.losers.ads_android_task.Interface.ChoosePresenter;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment.Establishment;
import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment.EstablishmentsResponse;
import com.losers.ads_android_task.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChoiceActivity extends AppCompatActivity implements CommonBaseView,
    ChooseAdapterListner {

  @BindView(R.id.done_btn)
  ImageButton mDoneBtn;
  private Set<Integer> mUserCategoryChoiceList = new HashSet<>();
  @BindView(R.id.back_btn)
  ImageButton mBackBtn;
  @BindView(R.id.category_rv)
  RecyclerView mCategoryRv;

  private List<Establishment> mEstablishment_s = new ArrayList<>();
  private ChoosePresenter mChoosePresenter;
  private EstablishmentAdapter mEstablishmentAdapter;
  Unbinder mUnbinder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choice);
    mUnbinder = ButterKnife.bind(this);

    setCategoryAdapter();
    mChoosePresenter = new ChoosePresenter(this);
    mChoosePresenter.getEstablishmentList(28.5291465, 76.9181154);
  }

  private void setCategoryAdapter() {
    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getApplicationContext(),
        LinearLayoutManager.VERTICAL, false);

//    mCategoryRv.setLayoutManager(horizontalLayoutManagaer);
    mCategoryRv.setLayoutManager(new GridLayoutManager(this, 2));

    //mEditorChoiceTrending.setHasFixedSize(true);
    mCategoryRv.setHasFixedSize(true);

    mEstablishmentAdapter = new EstablishmentAdapter(
        getApplicationContext(), mEstablishment_s, this);

    mCategoryRv.setAdapter(mEstablishmentAdapter);
  }


  @Override
  public void success(Object object, Object object2) {
    EstablishmentsResponse mEstablishmentsResponse = (EstablishmentsResponse) object;
    mEstablishment_s.addAll(mEstablishmentsResponse.getEstablishments());
    mEstablishmentAdapter.notifyDataSetChanged();
  }

  @Override
  public void failure(Throwable throwable) {

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
  }


  @Override
  public void onClick(int id, String name, Boolean isAdd) {
    updateEstablishementList(name, id, isAdd);
    updateUserCategoryChoiceList(id, isAdd);

    if (mUserCategoryChoiceList.size() > 0) {
      mDoneBtn.setVisibility(View.VISIBLE);
    } else {
      mDoneBtn.setVisibility(View.GONE);
    }
  }

  private void updateUserCategoryChoiceList(int id, Boolean isAdd) {
    if (isAdd) {
      mUserCategoryChoiceList.add(id);
    } else {
      if (mUserCategoryChoiceList.contains(id)) {
        mUserCategoryChoiceList.remove(id);
      }
    }

  }

  private void updateEstablishementList(String name, int id, Boolean isAdd) {
    for (Establishment mEstablishment : mEstablishment_s) {
      if (mEstablishment.getEstablishment().getName().equalsIgnoreCase(name)
          && mEstablishment.getEstablishment().getId() == id) {
        mEstablishment.getEstablishment().setSelectedByUser(isAdd);
      }
    }
  }

  private String getUserSelectedCategoryId() {
    StringBuilder mStringBuilder = new StringBuilder();
    int i = 0;
    for (Integer mInteger : mUserCategoryChoiceList) {
      mStringBuilder.append(mInteger);
      if (i++ != mUserCategoryChoiceList.size() - 1) {
        mStringBuilder.append(",");
      }
    }

    return mStringBuilder.toString();

  }

  @OnClick({R.id.back_btn, R.id.done_btn})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.back_btn:
        finish();
        break;
      case R.id.done_btn:
        Intent mIntent = new Intent(getApplicationContext(), FilterActivity.class);
        mIntent.putExtra(USER_CHOICE_CATEGORY, getUserSelectedCategoryId());
        startActivity(mIntent);
        break;
    }
  }
}
