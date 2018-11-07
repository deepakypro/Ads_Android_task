package com.losers.ads_android_task.Activity.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.losers.ads_android_task.Network.ApiResponse.Restaurants.Establishment.Establishment;
import com.losers.ads_android_task.R;
import java.util.List;

public class EstablishmentAdapter extends
    RecyclerView.Adapter<EstablishmentAdapter.MyViewHolder> {

  private List<Establishment> horizontalList;
  private Context context;
  private ChooseAdapterListner mChooseAdapterListner;

  public class MyViewHolder extends RecyclerView.ViewHolder {

    //    ImageView mImageView;
    TextView mNameTv; // mCategoryTv, mAddressTv, mTimeTv;

    public MyViewHolder(View view) {
      super(view);
      // mImageView = view.findViewById(R.id.place_imageview);
      mNameTv = view.findViewById(R.id.establishment_tv);
//      mCategoryTv = view.findViewById(R.id.category_tv);
//      mAddressTv = view.findViewById(R.id.address_tv);
//      mTimeTv = view.findViewById(R.id.time_tv);

    }
  }

  public EstablishmentAdapter(Context context,
      List<Establishment> horizontalList, ChooseAdapterListner mChooseAdapterListner) {
    this.context = context;
    this.horizontalList = horizontalList;
    this.mChooseAdapterListner = mChooseAdapterListner;
  }

  @Override
  public EstablishmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View itemView = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.cardview_establishment_item, parent, false);
    return new EstablishmentAdapter.MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(final EstablishmentAdapter.MyViewHolder holder,
      final int position) {
    final Establishment mItem = horizontalList
        .get(position);

    holder.mNameTv.setAllCaps(true);
    holder.mNameTv.setText(mItem.getEstablishment().getName() + "");

    holder.mNameTv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {

        if (mChooseAdapterListner == null) {
          return;
        }
        if (!mItem.getEstablishment().isSelectedByUser()) {
          holder.mNameTv
              .setBackgroundColor(context.getResources().getColor(R.color.primaryLightColor));
          mChooseAdapterListner
              .onClick(mItem.getEstablishment().getId(), mItem.getEstablishment().getName(),true);
        } else {
          holder.mNameTv.setBackgroundColor(context.getResources().getColor(R.color.white));
          mChooseAdapterListner
              .onClick(mItem.getEstablishment().getId(), mItem.getEstablishment().getName(),false);
        }

      }
    });

  }

//  }

  @Override
  public int getItemCount() {
    return horizontalList.size();
  }

 public interface ChooseAdapterListner {

    void onClick(int id, String name,Boolean isAdd);
  }

}



