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

        .bindMainImage(R.id.mainImage)
        .bindCallToAction(R.id.callToAction)
        .bindPromotedBy(R.id.promotedBy)

        .build();
  }

  public static ClientPositions getClientPosition() {
    return ANAdPositions.clientPositioning().
        addFixedPosition(5).addFixedPosition(2);
  }
}
