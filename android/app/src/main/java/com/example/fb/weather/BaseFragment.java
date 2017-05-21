package com.example.fb.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import com.example.fb.weather.event.RxBus;
import com.example.fb.weather.utils.Utils;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscription;

/**
 * Created by fb on 2017/5/20.
 */

public abstract class BaseFragment extends Fragment {

  List<Subscription> sps = new ArrayList<>();
  private List<PresenterDelegate> delegates = new ArrayList<>();
  public Unbinder unbinder;
  private ArrayMap<String, Observable> observables = new ArrayMap<>();

  public void initToolbar(Toolbar toolbar){
    toolbar.setNavigationIcon(R.drawable.ic_titlebar_back);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        getActivity().onBackPressed();
        return false;
      }
    });
  }

  @Override public void onAttach(Context context) {
    try {
      AndroidSupportInjection.inject(this);
    } catch (Exception e) {
      Log.e("AppCompat没有找到注入页面", "");
      //CrashUtils.sendCrash(e);
    }
    super.onAttach(context);
  }


  protected void delegatePresenter(Presenter presenter, ResponseListener listener) {
    PresenterDelegate delegate = new PresenterDelegate(presenter);
    delegate.attachView(listener);
    delegates.add(delegate);

  }

  @CallSuper @Override public void onDestroyView() {
    if (getActivity() != null) {
      Utils.hideKeyboard(getActivity());
    }
    if (delegates.size() > 0) {
      for (int i = 0; i < delegates.size(); i++) {
        delegates.get(i).unattachView();
      }
    }
    //if (getActivity() instanceof BaseActivity){
    //    ((BaseActivity) getActivity()).setBackPress(null);
    //}
    unattachView();
    if (unbinder != null) unbinder.unbind();
    super.onDestroyView();
  }

  public void unattachView() {
    for (int i = 0; i < sps.size(); i++) {
      sps.get(i).unsubscribe();
    }
    Iterator entries = observables.entrySet().iterator();
    while (entries.hasNext()) {
      Map.Entry entry = (Map.Entry) entries.next();
      String key = (String) entry.getKey();
      RxBus.getBus().unregister(key, observables.get(key));
    }
  }

  public Subscription RxRegiste(Subscription subscription) {
    sps.add(subscription);
    return subscription;
  }

  public <T> Observable<T> RxBusAdd(@NonNull Class<T> clazz) {
    Observable ob = RxBus.getBus().register(clazz);
    observables.put(clazz.getName(), ob);
    return ob;
  }


  public void showLoaing(ProgressBar progressBar){
    progressBar.setVisibility(View.VISIBLE);
  }

  public void hideLoading(ProgressBar progressBar){
    progressBar.setVisibility(View.GONE);
  }

  public void startShare(Context context){
    Intent share_intent = new Intent();
    share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
    share_intent.setType("text/plain");//设置分享内容的类型
    share_intent.putExtra(Intent.EXTRA_SUBJECT, "title");//添加分享内容标题
    share_intent.putExtra(Intent.EXTRA_TEXT, "shareContent");//添加分享内容
    //创建分享的Dialog
    share_intent = Intent.createChooser(share_intent, "分享");
    context.startActivity(share_intent);
  }

}
