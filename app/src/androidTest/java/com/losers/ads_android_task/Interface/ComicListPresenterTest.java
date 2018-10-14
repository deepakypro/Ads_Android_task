package com.losers.ads_android_task.Interface;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.losers.ads_android_task.Network.ApiResponse.ComicResponse;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ComicListPresenterTest {

  @Mock CommonBaseView mCommonBaseView;
  @Mock ComicListInterface mComicListInterface;
  @Mock Observable<ComicResponse> mComicResponseObservable;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
//    when(mComicResponseObservable).thenReturn(mCommonBaseView.onSuccess());
  }



  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void comicList() {
  }
}