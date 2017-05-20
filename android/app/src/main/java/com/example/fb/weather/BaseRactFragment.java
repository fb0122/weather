package com.example.fb.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

/**
 * Created by fb on 2017/5/21.
 */

public abstract class BaseRactFragment extends Fragment {

  //RN组件
  private ReactRootView mReactRootView;
  private ReactInstanceManager mReactInstanceManager;


  public abstract String getMainComponentName();


  @Nullable
  @Override
  public ReactRootView onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    return mReactRootView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mReactRootView.startReactApplication(mReactInstanceManager, getMainComponentName(), null);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mReactRootView = new ReactRootView(context);
    mReactInstanceManager = ((MyApplication)getActivity().getApplication()).getReactNativeHost().getReactInstanceManager();

  }

}
