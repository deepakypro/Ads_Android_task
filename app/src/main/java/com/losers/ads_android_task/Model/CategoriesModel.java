package com.losers.ads_android_task.Model;


import com.losers.ads_android_task.R;
import java.util.ArrayList;
import java.util.List;

public class CategoriesModel {


  public int imageId;
  public String categoryName;
  public String cateogryId;

  public CategoriesModel(){}
  public CategoriesModel(int imageId, String categoryName, String cateogryId) {
    this.imageId = imageId;
    this.categoryName = categoryName;
    this.cateogryId = cateogryId;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCateogryId() {
    return cateogryId;
  }

  public void setCateogryId(String cateogryId) {
    this.cateogryId = cateogryId;
  }



  public List<CategoriesModel> getCategoriesModelsList() {
    List<CategoriesModel> mCategoriesModelList = new ArrayList<>();

    mCategoriesModelList.add(new CategoriesModel(R.drawable.category_art, "Arts & Entertainment",
        "4d4b7104d754a06370d81259"));
    mCategoriesModelList.add(new CategoriesModel(R.drawable.category_moview, "Movie Theater",
        "4bf58dd8d48988d17f941735"));
    mCategoriesModelList.add(
        new CategoriesModel(R.drawable.category_stadium, "Stadium", "4bf58dd8d48988d184941735"));
//    mCategoriesModelList
//        .add(new CategoriesModel(R.drawable.category_events, "Event", "4d4b7105d754a06373d81259"));
//    mCategoriesModelList
//        .add(new CategoriesModel(R.drawable.category_food, "Food", "4d4b7105d754a06374d81259"));
//    mCategoriesModelList.add(new CategoriesModel(R.drawable.category_nightlife, "Nightlife",
//        "4d4b7105d754a06376d81259"));
//    mCategoriesModelList.add(
//        new CategoriesModel(R.drawable.category_outdoor, "Outdoors", "4d4b7105d754a06377d81259"));
//    mCategoriesModelList.add(
//        new CategoriesModel(R.drawable.category_parking, "Parking", "4c38df4de52ce0d596b336e1"));
//    mCategoriesModelList.add(new CategoriesModel(R.drawable.category_sprituial, "Spiritual Center",
//        "4bf58dd8d48988d131941735"));
//
//    mCategoriesModelList
//        .add(new CategoriesModel(R.drawable.category_shop, "Clothing Store", "4d4b7105d754a06378d81259"));
//    mCategoriesModelList.add(
//        new CategoriesModel(R.drawable.category_travel, "Travel ", "4d4b7105d754a06379d81259"));
//    mCategoriesModelList
//        .add(new CategoriesModel(R.drawable.category_hotel, "Hotel", "4bf58dd8d48988d1fa931735"));

    return mCategoriesModelList;
  }
}
