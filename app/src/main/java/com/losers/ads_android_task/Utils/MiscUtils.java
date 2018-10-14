package com.losers.ads_android_task.Utils;

import android.util.Log;
import android.widget.ImageView;
import com.losers.ads_android_task.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MiscUtils {

  public  void setImage(ImageView imageToPicasso, String id) {
    if (imageToPicasso == null) {
      return;
    }

    Picasso.get()
        .load(id)

        .into(imageToPicasso, new Callback() {
          @Override
          public void onSuccess() {

          }

          @Override
          public void onError(Exception e) {

            e.printStackTrace();
          }


        });

  }

  public static int getListCount(){
    return 10;
  }
}
