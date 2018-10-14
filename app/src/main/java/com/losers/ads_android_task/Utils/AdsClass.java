package com.losers.ads_android_task.Utils;

import com.adsnative.ads.ANAdPositions;
import com.adsnative.ads.ANAdPositions.ClientPositions;
import com.adsnative.ads.ANAdViewBinder;
import com.losers.ads_android_task.R;

public class AdsClass {

  public static ANAdViewBinder getAnAdViewBinder() {
    return new ANAdViewBinder.Builder(R.layout.layout_custom_ads)
        .bindTitle(R.id.title)
        .bindSummary(R.id.summary)
        .bindIconImage(R.id.iconImage)
        .bindMainImage(R.id.mainImage)

        .bindPromotedBy(R.id.promotedBy)

        .build();
  }

  public static ANAdViewBinder getGridAnAdViewBinder() {
    return new ANAdViewBinder.Builder(R.layout.layout_custom_grid_ads)
        .bindTitle(R.id.title)
        .bindMainImage(R.id.mainImage)

        .build();
  }

  public static ClientPositions getClientPosition() {
    return ANAdPositions.clientPositioning().
        addFixedPosition(5).enableRepeatingPositions(10);
  }
}
