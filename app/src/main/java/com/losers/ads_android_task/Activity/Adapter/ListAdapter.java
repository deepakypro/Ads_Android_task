package com.losers.ads_android_task.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.MiscUtils;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<ComicResponse> implements View.OnClickListener {

  private List<ComicResponse> dataSet;
  private Context mContext;
  private final MiscUtils mMiscUtils = new MiscUtils();

  // View lookup cache
  private static class ViewHolder {

    TextView mNameTv;
    TextView mDateTv;
    TextView mNum_tv;
    ImageView mImv;
  }


  public ListAdapter(List<ComicResponse> data, Context context) {
    super(context, R.layout.layout_comic_adapter, data);
    this.dataSet = data;
    this.mContext = context;

  }


  @Override
  public void onClick(View v) {
    int position = (Integer) v.getTag();
    Object object = getItem(position);
    ComicResponse ComicResponse = (ComicResponse) object;

    switch (v.getId()) {

//      case R.id.item_info:
//
//        Snackbar.make(v, "Release date " +ComicResponse.getFeature(), Snackbar.LENGTH_LONG)
//            .setAction("No action", null).show();
//
//        break;

    }


  }

  private int lastPosition = -1;

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data item for this position
    ComicResponse ComicResponse = getItem(position);
    // Check if an existing view is being reused, otherwise inflate the view
    ViewHolder viewHolder; // view lookup cache stored in tag

    final View result;

    if (convertView == null) {

      viewHolder = new ViewHolder();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.layout_comic_adapter, parent, false);
      viewHolder.mNameTv = (TextView) convertView.findViewById(R.id.title_tv);
      viewHolder.mDateTv = (TextView) convertView.findViewById(R.id.date_tv);
      viewHolder.mNum_tv = (TextView) convertView.findViewById(R.id.num_tv);
      viewHolder.mImv = (ImageView) convertView.findViewById(R.id.imv);

      result = convertView;

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
      result = convertView;
    }

//    Animation animation = AnimationUtils
//        .loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//    result.startAnimation(animation);
    lastPosition = position;

    viewHolder.mNameTv.setText(ComicResponse.getTitle());
    viewHolder.mDateTv.setText(ComicResponse.getDay());
    viewHolder.mNum_tv.setText(ComicResponse.getNum() + " ");
    mMiscUtils.setImage(viewHolder.mImv, ComicResponse.getImg());
//    viewHolder.info.setOnClickListener(this);
//    viewHolder.info.setTag(position);
    // Return the completed view to render on screen
    return convertView;
  }


}
