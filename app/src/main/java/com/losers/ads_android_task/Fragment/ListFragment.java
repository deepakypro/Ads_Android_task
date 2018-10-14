package com.losers.ads_android_task.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.losers.ads_android_task.Activity.Adapter.ListAdapter;
import com.losers.ads_android_task.Interface.ComicListPresenter;
import com.losers.ads_android_task.Interface.CommonBaseView;
import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import com.losers.ads_android_task.R;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements CommonBaseView {

  private  List<ComicResponse> mComicResponses = new ArrayList<>();
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";


  @BindView(R.id.listview)
  ListView mListview;
  @BindView(R.id.load_more)
  RelativeLayout mLoadMore;

  private int mItemCount = 1;
  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  ComicListPresenter mComicListPresenter;
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
    getData(false);
    mListview.setOnScrollListener(new OnScrollListener() {
      @Override
      public void onScrollStateChanged(AbsListView absListView, int i) {

      }

      @Override
      public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
          int totalItemCount) {
        if (firstVisibleItem + visibleItemCount > totalItemCount - 2) {
          showLoadMore();
          getData(true);

        }
      }
    });
  }


  private void getData(boolean isRefresh) {
    mComicListPresenter.comicList(mItemCount,isRefresh);
    mItemCount += 10;
  }

  @Override
  public void onUnknownError(String error) {

  }

  @Override
  public void onTimeout() {

  }

  private void showLoadMore(){
    if(mLoadMore==null){
      return;
    }
    mLoadMore.setVisibility(View.VISIBLE);
  }

  private void hideLoadMore(){
    if(mLoadMore==null){
      return;
    }
    mLoadMore.setVisibility(View.GONE);
  }
  @Override
  public void onSuccess(Object object,Object object1) {

    hideLoadMore();

    boolean isRefresh=(Boolean) object1;

    mComicResponses.addAll((List<ComicResponse>) object);

    if (mComicResponses == null && mComicResponses.isEmpty()) {
      return;
    }
    ListAdapter adapter = new ListAdapter(mComicResponses, getContext());

    if(!isRefresh){
      mListview.setAdapter(adapter);
    }else {
      adapter.notifyDataSetChanged();
    }

  }

  @Override
  public void onError(Throwable e) {

  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mComicListPresenter.clear();
    mUnbinder.unbind();
  }
}

