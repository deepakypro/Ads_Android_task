package com.losers.ads_android_task.Activity;


import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.widget.TableLayout;
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
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(vpPager);
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
    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

      switch (position) {
        case 0: // Fragment # 0 - This will show FirstFragment
          return "List View";
        case 1: // Fragment # 0 - This will show FirstFragment different title
          return "Grid View";
//                case 2: // Fragment # 1 - This will show SecondFragment
//                    return ThirdFragment.newInstance(2, "Page # 3");
        default:
          return null;
      }

    }

  }

}
