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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.adsnative.ads.ANListAdapter;
import com.losers.ads_android_task.Activity.Adapter.GridAdapter;
import com.losers.ads_android_task.Activity.Adapter.GridAdapter.ListAdapterListener;
import com.losers.ads_android_task.Activity.DetailsComicActivity;
import com.losers.ads_android_task.Interface.ComicGridPresenter;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.R;
import com.losers.ads_android_task.Utils.AdsClass;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridFragment extends Fragment implements CommonBaseView, ListAdapterListener {

  @BindView(R.id.progressbar)
  ProgressBar mProgressbar;
  private boolean hasBeenVisibleOnce = false;
  private boolean isApiRequestActive = false;
  private boolean isFirstQueryHit = false;
  private List<ComicResponse> mComicResponses = new ArrayList<>();
  private ComicGridPresenter mComicGridPresenter;
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  @BindView(R.id.grid)
  GridView mGrid;
  @BindView(R.id.load_more)
  RelativeLayout mLoadMore;

  private GridAdapter mListAdapter;
  private ANListAdapter anListAdapter;
  private int mItemGridCount = 0;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  Unbinder mUnbinder;

  public GridFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment GridFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static GridFragment newInstance(String param1, String param2) {
    GridFragment fragment = new GridFragment();
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
    View mView = inflater.inflate(R.layout.fragment_grid, container, false);
    mUnbinder = ButterKnife.bind(this, mView);
    return mView;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mComicGridPresenter = new ComicGridPresenter(this);
    setAdapter();

    mGrid.setOnScrollListener(new OnScrollListener() {
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

  @Override
  public void setUserVisibleHint(boolean visible) {
    super.setUserVisibleHint(true);

    if (this.isVisible()) {
      if (visible && !hasBeenVisibleOnce) {

        hasBeenVisibleOnce = true;
        getData(false);

      }
    }
  }


  private void setAdapter() {
    mListAdapter = new GridAdapter(mComicResponses, getContext(), this::onClick);

    anListAdapter = new ANListAdapter(getContext(), mListAdapter,
        ADS_UNIT, AdsClass.getClientPosition());

    // Set your original view's adapter to ANListAdapter instance

    mGrid.setAdapter(anListAdapter);

    // Register the renderer with the ANListAdapter
    anListAdapter.registerViewBinder(AdsClass.getGridAnAdViewBinder());
    // Start loading ads
    anListAdapter.loadAds();

//    mGrid.setAdapter(mListAdapter);
  }

  @Override
  public void onUnknownError(String error) {

  }

  @Override
  public void onTimeout() {

  }


  private void getData(boolean isRefresh) {

    if (!isApiRequestActive) {
    isApiRequestActive = true;
    mComicGridPresenter.comicGridList(mItemGridCount, isRefresh);
    mItemGridCount += 10;
    }

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

    isFirstQueryHit = true;
    hideLoadMore();
    isApiRequestActive = false;
    boolean isRefresh = (Boolean) object1;

    mComicResponses.addAll((List<ComicResponse>) object);

    if (mComicResponses == null && mComicResponses.isEmpty()) {
      return;
    }
    mListAdapter.notifyDataSetChanged();

  }

  @Override
  public void onError(Throwable e) {

  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mComicGridPresenter.clear();
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
