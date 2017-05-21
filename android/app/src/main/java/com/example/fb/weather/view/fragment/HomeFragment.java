package com.example.fb.weather.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.fb.weather.BaseFragment;
import com.example.fb.weather.FragmentAdapter;
import com.example.fb.weather.R;
import java.util.ArrayList;

/**
 * Created by fb on 2017/5/21.
 */

public class HomeFragment extends BaseFragment {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.toolbar_titile) TextView toolbarTitile;
  @BindView(R.id.toolbar_share) ImageView toolbarShare;
  @BindView(R.id.home_tab) TabLayout homeTab;
  @BindView(R.id.weather_pager) ViewPager weatherPager;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    unbinder = ButterKnife.bind(this, view);

    setToolbar();
    setViewPager();
    return view;
  }

  private void setViewPager(){

    ArrayList<Fragment> fragments = new ArrayList<>();
    FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragments);

    if (adapter.getCount() <= 0) {
      fragments.add(new WeatherFragment());
    }

    weatherPager.setAdapter(adapter);
    weatherPager.setOffscreenPageLimit(2);
    homeTab.setupWithViewPager(weatherPager);
    weatherPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeTab));
  }

  private void setToolbar() {
    initToolbar(toolbar);
    toolbarTitile.setText("天气");
    toolbarTitile.setTextColor(getResources().getColor(R.color.white));
    toolbarShare.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startShare(getActivity());          //打开分享页面
      }
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }
}
