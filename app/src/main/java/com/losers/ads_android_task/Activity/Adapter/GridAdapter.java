package com.losers.ads_android_task.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.MiscUtils;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class GridAdapter  extends ArrayAdapter<ComicResponse> implements View.OnClickListener {

  private List<ComicResponse> dataSet;
  private Context mContext;
  private final MiscUtils mMiscUtils = new MiscUtils();
  private GridAdapter.ListAdapterListener mListAdapterListener;

  // View lookup cache
  private static class ViewHolder {

    CardView mCardView;
    TextView mNameTv;
    TextView mDateTv;
    TextView mNum_tv;
    ImageView mImv;


  }


  public GridAdapter(List<ComicResponse> data, Context context,
      GridAdapter.ListAdapterListener mListAdapterListener) {
    super(context, R.layout.layout_grid_adapter, data);
    this.dataSet = data;
    this.mContext = context;
    this.mListAdapterListener = mListAdapterListener;

  }


  @Override
  public void onClick(View v) {
    int position = (Integer) v.getTag();
    Object object = getItem(position);
    ComicResponse mComicResponse = (ComicResponse) object;

    switch (v.getId()) {

      case R.id.cardview:

        if (mListAdapterListener != null) {
          mListAdapterListener.onClick(mComicResponse.getImg(), mComicResponse.getTitle(),
              getFormattedDate(mComicResponse.getDay(), mComicResponse.getMonth(),
                  mComicResponse.getYear()),
              mComicResponse.getAlt(), mComicResponse.getNum(), mComicResponse.getTranscript(),
              mComicResponse.getNews());
        }

        break;

    }


  }

  private int lastPosition = -1;

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data item for this position
    ComicResponse ComicResponse = getItem(position);
    // Check if an existing view is being reused, otherwise inflate the view
    GridAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

    final View result;

    if (convertView == null) {

      viewHolder = new GridAdapter.ViewHolder();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.layout_grid_adapter, parent, false);
      viewHolder.mNameTv = (TextView) convertView.findViewById(R.id.title_tv);
      viewHolder.mDateTv = (TextView) convertView.findViewById(R.id.date_tv);
      viewHolder.mNum_tv = (TextView) convertView.findViewById(R.id.num_tv);
      viewHolder.mImv = (ImageView) convertView.findViewById(R.id.imv);
      viewHolder.mCardView = convertView.findViewById(R.id.cardview);

      result = convertView;

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (GridAdapter.ViewHolder) convertView.getTag();
      result = convertView;
    }

//    Animation animation = AnimationUtils
//        .loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//    result.startAnimation(animation);
    lastPosition = position;

    viewHolder.mNameTv.setText(ComicResponse.getTitle());
    viewHolder.mDateTv.setText(getFormattedDate(ComicResponse.getDay(), ComicResponse.getMonth(),
        ComicResponse.getYear()));
    viewHolder.mNum_tv.setText(ComicResponse.getNum() + " ");
    mMiscUtils.setImage(viewHolder.mImv, ComicResponse.getImg());

    viewHolder.mCardView.setOnClickListener(this);
    viewHolder.mCardView.setTag(position);
    // Return the completed view to render on screen
    return convertView;
  }


  public interface ListAdapterListener {

    void onClick(final String image, final String title, final String date, final String alt,
        final int num, final String transcript, final String news);
  }


  private String getFormattedDate(String day, String month, String year) {

    String mon;
    switch (month) {
      case "1":
        mon = "Jan";
        break;
      case "2":
        mon = "Feb";
        break;
      case "3":
        mon = "Mar";
        break;
      case "4":
        mon = "Apr";
        break;
      case "5":
        mon = "May";
        break;
      case "6":
        mon = "June";
        break;
      case "7":
        mon = "July";
        break;
      case "8":
        mon = "Aug";
        break;
      case "9":
        mon = "Sep";
        break;
      case "10":
        mon = "Oct";
        break;
      case "11":
        mon = "Nov";
        break;
      case "12":
        mon = "Dec";
        break;

      default:
        mon = "";
    }
    return day + " , " + mon + " , " + year;
  }
}

