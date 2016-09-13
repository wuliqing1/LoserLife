package com.example.y.mvp.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.mvp.R;
import com.example.y.mvp.adapter.TabNewsAdapter;
import com.example.y.mvp.mvp.Bean.TabNewsInfo;
import com.example.y.mvp.mvp.presenter.TabNewsPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.UIUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * by 12406 on 2016/5/14.
 */
public class NewsViewPagerFragment extends BaseFragment<BaseView.TabNewsView, TabNewsPresenterImpl> implements BaseView.TabNewsView {

    @SuppressWarnings("unused")
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @SuppressWarnings("unused")
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private List<TabNewsInfo> data;
    private TabNewsAdapter tabNewsAdapter;

    @Override
    protected TabNewsPresenterImpl createPresenter() {
        return new TabNewsPresenterImpl();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return View.inflate(getActivity(), R.layout.fragment_viewpager, null);
    }

    @Override
    protected void initData() {
        mPresenter.requestNetWork();

        data = new LinkedList<>();
        tabNewsAdapter = new TabNewsAdapter(getChildFragmentManager(), data);
    }

    @Override
    public void setData(List<TabNewsInfo> datas) {
        if (!datas.isEmpty()) {
            data.addAll(datas);
            viewPager.setAdapter(tabNewsAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void netWorkError() {
        Toast(UIUtils.getContext().getResources().getString(R.string.network_error));
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showFoot() {

    }

    @Override
    public void hideFoot() {

    }
}
