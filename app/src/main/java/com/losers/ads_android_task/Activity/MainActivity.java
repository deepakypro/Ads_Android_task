package com.losers.ads_android_task.Activity;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import com.losers.ads_android_task.R;

public class MainActivity extends AppCompatActivity {

  FragmentPagerAdapter adapterViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  }


}
