package com.losers.ads_android_task.Activity;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import androidx.viewpager.widget.ViewPager;
import com.losers.ads_android_task.Fragment.GridFragment;
import com.losers.ads_android_task.Fragment.ListFragment;
import com.losers.ads_android_task.R;

public class MainActivity extends AppCompatActivity {

  FragmentPagerAdapter adapterViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
    adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
    vpPager.setAdapter(adapterViewPager);
  }

  public static class MyPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;

    public MyPagerAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
      return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
      switch (position) {
        case 0: // Fragment # 0 - This will show FirstFragment
          return new ListFragment();
        case 1: // Fragment # 0 - This will show FirstFragment different title
          return new GridFragment();

        default:
          return null;
      }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
      return "Page " + position;
    }

  }

}
