package com.losers.ads_android_task.Activity.Adapter;

import static com.losers.ads_android_task.Utils.Constants.RESTAURANT_DETAILS;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.losers.ads_android_task.Activity.ProductDetailsActivity;

import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Restaurant;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.MiscUtils;
import java.util.List;

public class ProductListAdapter extends
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

  private List<Restaurant> horizontalList;
  private Context context;

  public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mNameTv, mCategoryTv, mAddressTv, mTimeTv;

    public MyViewHolder(View view) {
      super(view);
      mImageView = view.findViewById(R.id.place_imageview);
      mNameTv = view.findViewById(R.id.name_tv);
      mCategoryTv = view.findViewById(R.id.category_tv);
      mAddressTv = view.findViewById(R.id.address_tv);
      mTimeTv = view.findViewById(R.id.time_tv);

    }
  }

  public ProductListAdapter(Context context,
      List<Restaurant> horizontalList) {
    this.context = context;
    this.horizontalList = horizontalList;
  }

  @Override
  public ProductListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View itemView = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.cardview_restaurant, parent, false);
    return new ProductListAdapter.MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(final ProductListAdapter.MyViewHolder holder,
      final int position) {
    final Restaurant mItem = horizontalList
        .get(position);

    holder.mNameTv.setAllCaps(true);
    holder.mNameTv.setText(mItem.getRestaurant().getName() + "");
    holder.mAddressTv.setText(mItem.getRestaurant().getLocation().getLocalityVerbose() + "");
    holder.mCategoryTv.setText(mItem.getRestaurant().getCuisines() + "");
//    // holder.mTextViewDetail.setText(String.valueOf(mCheckinsCount));
//    MiscUtils.setImage(holder.mImageView, mItem.getRestaurant().getFeaturedImage());

    holder.mImageView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
//        context.startActivity(new Intent(context, ProductDetailsActivity.class));

        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(RESTAURANT_DETAILS, mItem);
//        intent.putExtra(IMAGE_ID, mItem.getImageId());
//        intent.putExtra(SECTION_ID, getSectionEnum(mItem.getCategoryName()));
////        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
////            context,
////            holder.mImageView,
////            ViewCompat.getTransitionName( holder.mImageView));
        context.startActivity(intent);
//        context.startActivity(intent, options.toBundle());
      }
    });

  }

//  }

  @Override
  public int getItemCount() {
    return horizontalList.size();
  }
}



