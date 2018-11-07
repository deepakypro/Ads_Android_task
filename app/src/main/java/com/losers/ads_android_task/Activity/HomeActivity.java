package com.losers.ads_android_task.Activity;

import android.content.Intent;
import android.os.Bundle;



import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.MiscUtils;

public class HomeActivity extends AppCompatActivity
    implements OnNavigationItemSelectedListener {


  Unbinder mUnbinder;
  @BindView(R.id.your_at_tv)
  TextView mYourAtTv;
  @BindView(R.id.temperature_tv)
  TextView mTemperatureTv;
  @BindView(R.id.food_imageview)
  ImageView mFoodImageview;
  @BindView(R.id.movie_imageview)
  ImageView mMovieImageview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    mUnbinder = ButterKnife.bind(this);


    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//    navigationView.setNavigationItemSelectedListener(
//         getApplicationContext());

    MiscUtils.setImage(mFoodImageview, R.drawable.category_food);
    MiscUtils.setImage(mMovieImageview, R.drawable.category_moview);

    startActivity(new Intent(getApplicationContext(),ChoiceActivity.class));
//    setCategoryAdapter();
  }

//  private void setCategoryAdapter() {
//    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getApplicationContext(),
//        LinearLayoutManager.VERTICAL, false);
//
//    //mEditorChoiceTrending.setLayoutManager(horizontalLayoutManagaer);
//    mSectionRv.setLayoutManager(new GridLayoutManager(this, 2));
//
//    //mEditorChoiceTrending.setHasFixedSize(true);
//    mSectionRv.setHasFixedSize(true);
//
//    CategoriesModel mCategoriesModel = new CategoriesModel();
//    CategoriesRecyclerAdapter mHorizontalAdapter = new CategoriesRecyclerAdapter(
//        getApplicationContext(), mCategoriesModel.getCategoriesModelsList());
//
//    mSectionRv.setAdapter(mHorizontalAdapter);
//  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      mUnbinder.unbind();
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @OnClick({R.id.food_imageview, R.id.movie_imageview})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.food_imageview:
        break;
      case R.id.movie_imageview:
        break;
    }
  }
}
