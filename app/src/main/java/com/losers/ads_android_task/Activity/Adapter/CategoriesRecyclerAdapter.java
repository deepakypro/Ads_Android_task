//package com.losers.ads_android_task.Activity.Adapter;
//
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.losers.ads_android_task.Model.CategoriesModel;
//import com.losers.ads_android_task.R;
//import com.losers.ads_android_task.Utils.MiscUtils;
//import java.util.List;
//
//public class CategoriesRecyclerAdapter extends
//    RecyclerView.Adapter<CategoriesRecyclerAdapter.MyViewHolder> {
//
//  private List<CategoriesModel> horizontalList;
//  private Context context;
//
//  public class MyViewHolder extends RecyclerView.ViewHolder {
//
//    ImageView mImageView;
//    TextView mTextViewName;
//
//    public MyViewHolder(View view) {
//      super(view);
//      mImageView = view.findViewById(R.id.category_recycler_list_imageview);
//      mTextViewName = view.findViewById(R.id.category_recycler_list_name);
//
//    }
//  }
//
//  public CategoriesRecyclerAdapter(Context context,
//      List<CategoriesModel> horizontalList) {
//    this.context = context;
//    this.horizontalList = horizontalList;
//  }
//
//  @Override
//  public CategoriesRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
//      int viewType) {
//    View itemView = LayoutInflater
//        .from(parent.getContext()).inflate(R.layout.cardview_categories_recycler_list, parent, false);
//    return new CategoriesRecyclerAdapter.MyViewHolder(itemView);
//  }
//
//  @Override
//  public void onBindViewHolder(final CategoriesRecyclerAdapter.MyViewHolder holder,
//      final int position) {
//    final CategoriesModel mItem = horizontalList
//        .get(position);
//
//    holder.mTextViewName.setAllCaps(true);
//    holder.mTextViewName.setText(mItem.getCategoryName() + "");
//    // holder.mTextViewDetail.setText(String.valueOf(mCheckinsCount));
//
//    MiscUtils.setImage(holder.mImageView, mItem.getImageId());
//
//    holder.mImageView.setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//
////        Intent intent = new Intent(context, SectionActivity.class);
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        intent.putExtra(NAME, mItem.getCategoryName());
////        intent.putExtra(IMAGE_ID, mItem.getImageId());
////        intent.putExtra(SECTION_ID, getSectionEnum(mItem.getCategoryName()));
//////        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//////            context,
//////            holder.mImageView,
//////            ViewCompat.getTransitionName( holder.mImageView));
////        context.startActivity(intent);
////        context.startActivity(intent, options.toBundle());
//      }
//    });
//
//  }
//
////  private SECTION_ENUM getSectionEnum(String category) {
////    switch (category) {
////      case "Arts & Entertainment":
////        return SECTION_ENUM.ARTS_ENTERTAINMENT;
////      case "Movie Theater":
////        return SECTION_ENUM.MOVIE_THEATER;
////      case "Stadium":
////        return SECTION_ENUM.STADIUM;
////      case "Event":
////        return SECTION_ENUM.EVENT;
////      case "Food":
////        return SECTION_ENUM.FOOD;
////      case "Nightlife":
////        return SECTION_ENUM.NIGHTLIFE;
////      case "Outdoors":
////        return SECTION_ENUM.OUTDOORS;
////      case "Parking":
////        return SECTION_ENUM.PARKING;
////      case "Spiritual Center":
////        return SECTION_ENUM.SPIRITUAL_CENTER;
////      case "Clothing Store":
////        return SECTION_ENUM.SHOP;
////      case "Travel":
////        return SECTION_ENUM.TRAVEL;
////      case "Hotel":
////        return SECTION_ENUM.HOTEL;
////
////
////    }
////    return null;
////  }
//
//  @Override
//  public int getItemCount() {
//    return horizontalList.size();
//  }
//}
//
//
//
