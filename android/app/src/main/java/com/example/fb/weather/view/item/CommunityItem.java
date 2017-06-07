package com.example.fb.weather.view.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.fb.weather.R;
import com.example.fb.weather.network.response.Community;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;
import java.util.List;

/**
 * Created by fb on 2017/5/21.
 */

public class CommunityItem extends AbstractFlexibleItem<CommunityItem.CommunityVH> {

  private Community community;

  public CommunityItem(Community community) {
    this.community = community;
  }

  public Community getData(){
    return community;
  }

  @Override public boolean equals(Object o) {
    return false;
  }

  @Override public CommunityVH createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater,
      ViewGroup parent) {
    return new CommunityVH(inflater.inflate(getLayoutRes(), parent, false), adapter);
  }

  @Override public void bindViewHolder(FlexibleAdapter adapter, CommunityVH holder, int position,
      List payloads) {
    super.bindViewHolder(adapter, holder, position, payloads);
  }

  @Override public int getLayoutRes() {
    return R.layout.item_community;
  }

  class CommunityVH extends FlexibleViewHolder {


    @BindView(R.id.comment_title) TextView commentTitle;
    @BindView(R.id.comment_content) TextView commentContent;
    @BindView(R.id.layout_button) LinearLayout layoutButton;

    public CommunityVH(View view, FlexibleAdapter adapter) {
      super(view, adapter);
      ButterKnife.bind(this, view);
    }
  }
}
