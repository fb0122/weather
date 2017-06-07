package com.example.fb.weather.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.fb.weather.BaseFragment;
import com.example.fb.weather.R;
import com.example.fb.weather.view.item.CommunityItem;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fb on 2017/5/21.
 */

public class CommunityFragment extends BaseFragment implements TitleFragment {

  @BindView(R.id.recycle_community) RecyclerView recycleCommunity;
  Unbinder unbinder;
  private FlexibleAdapter adapter;
  private List<CommunityItem> itemList = new ArrayList<>();

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_community, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public String getTitle() {
    return "社区";
  }
}
