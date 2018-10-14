package com.losers.ads_android_task.Fragment;


import static com.losers.ads_android_task.Utils.Constants.ADS_UNIT;
import static com.losers.ads_android_task.Utils.Constants.ALT;
import static com.losers.ads_android_task.Utils.Constants.DATE;
import static com.losers.ads_android_task.Utils.Constants.IMAGE;
import static com.losers.ads_android_task.Utils.Constants.NEWS;
import static com.losers.ads_android_task.Utils.Constants.NUM;
import static com.losers.ads_android_task.Utils.Constants.TITLE;
import static com.losers.ads_android_task.Utils.Constants.TRANSCRIPT;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.adsnative.ads.ANListAdapter;
import com.losers.ads_android_task.Activity.Adapter.ListAdapter;
import com.losers.ads_android_task.Activity.Adapter.ListAdapter.ListAdapterListener;
import com.losers.ads_android_task.Activity.DetailsComicActivity;
import com.losers.ads_android_task.Interface.ComicListPresenter;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.AdsClass;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements CommonBaseView, ListAdapterListener {

  @BindView(R.id.progressbar)
  ProgressBar mProgressbar;

  private boolean isFirstQueryHit = false;
  private List<ComicResponse> mComicResponses = new ArrayList<>();
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private ANListAdapter anListAdapter;

  private ListAdapter mListAdapter;

  private boolean isApiRequestActive = false;
  @BindView(R.id.listview)
  ListView mListview;
  @BindView(R.id.load_more)
  RelativeLayout mLoadMore;

  private int mItemCount = 0;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private ComicListPresenter mComicListPresenter;
  Unbinder mUnbinder;

  public ListFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment ListFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static ListFragment newInstance(String param1, String param2) {
    ListFragment fragment = new ListFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_list, container, false);
    mUnbinder = ButterKnife.bind(this, view);

    return view;
  }


  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mComicListPresenter = new ComicListPresenter(this);

    setAdapter();
    getData(false);

    mListview.setOnScrollListener(new OnScrollListener() {
      @Override
      public void onScrollStateChanged(AbsListView absListView, int i) {

      }

      @Override
      public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {
        if (firstVisibleItem + visibleItemCount > totalItemCount - 2) {

          if (isFirstQueryHit) {
            showLoadMore();
            getData(true);
          }


        }
      }
    });
  }


  private void setAdapter() {
    mListAdapter = new ListAdapter(mComicResponses, getContext(), this::onClick);

    anListAdapter = new ANListAdapter(getContext(), mListAdapter,
        ADS_UNIT, AdsClass.getClientPosition());

    // Set your original view's adapter to ANListAdapter instance

    mListview.setAdapter(anListAdapter);

    // Register the renderer with the ANListAdapter
    anListAdapter.registerViewBinder(AdsClass.getAnAdViewBinder());
    // Start loading ads
    anListAdapter.loadAds();

//    mListview.setAdapter(mListAdapter);
  }

  private void getData(boolean isRefresh) {

    if (!isApiRequestActive) {
      isApiRequestActive = true;
      mComicListPresenter.comicList(mItemCount, isRefresh);
      mItemCount += 10;
    }

  }

  @Override
  public void onUnknownError(String error) {

  }

  @Override
  public void onTimeout() {

  }

  private void showLoadMore() {
    if (mLoadMore == null) {
      return;
    }
    mLoadMore.setVisibility(View.VISIBLE);
  }

  private void hideLoadMore() {
    if (mLoadMore == null) {
      return;
    }
    mLoadMore.setVisibility(View.GONE);
  }

  @Override
  public void onSuccess(Object object, Object object1) {

    if (mProgressbar != null && mProgressbar.getVisibility() == View.VISIBLE) {
      mProgressbar.setVisibility(View.GONE);
    }
    isApiRequestActive = false;
    isFirstQueryHit = true;

    hideLoadMore();

    mComicResponses.addAll((List<ComicResponse>) object);

    if (mComicResponses == null && mComicResponses.isEmpty()) {
      return;
    }

//    if (!isRefresh) {
//
//
////      mListview.setAdapter(adapter);
//    } else {

    mListAdapter.notifyDataSetChanged();

//    }

  }

  @Override
  public void onError(Throwable e) {

  }

  @Override
  public void onResume() {
    super.onResume();
    // We recommend loading new ads when the user returns to your activity
//    anListAdapter.loadAds();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mComicListPresenter.clear();
    mUnbinder.unbind();
  }

  @Override
  public void onClick(String image, String title, String date, String alt,
      int num, String transcript, String news) {

    inCreaseClickCount();
    Intent mIntent = new Intent(getContext(), DetailsComicActivity.class);
    mIntent.putExtra(IMAGE, image);
    mIntent.putExtra(TITLE, title);
    mIntent.putExtra(DATE, date);
    mIntent.putExtra(ALT, alt);
    mIntent.putExtra(NUM, num);
    mIntent.putExtra(TRANSCRIPT, transcript);
    mIntent.putExtra(NEWS, news);
    startActivity(mIntent);
  }



  private void inCreaseClickCount() {
    int count = DetailsComicActivity.sUserClickCountInteger.get();
    DetailsComicActivity.sUserClickCountInteger.set(count + 1);
  }
}

