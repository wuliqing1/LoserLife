package com.example.y.mvp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.mvp.R;
import com.example.y.mvp.activity.NewsDetailActivity;
import com.example.y.mvp.adapter.BaseRecyclerViewAdapter;
import com.example.y.mvp.adapter.NewsListAdapter;
import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.databinding.FragmentNewsBinding;
import com.example.y.mvp.mvp.Bean.NewsListInfo;
import com.example.y.mvp.mvp.presenter.NewsListPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.UIUtils;
import com.example.y.mvp.widget.MyRecyclerView;

import java.util.LinkedList;
import java.util.List;

/**
 * by 12406 on 2016/5/14.
 */
public class NewsMainFragment extends BaseFragment<BaseView.NewsListView, NewsListPresenterImpl>
        implements SwipeRefreshLayout.OnRefreshListener,
        MyRecyclerView.LoadingData, BaseView.NewsListView, BaseRecyclerViewAdapter.OnItemClickListener<NewsListInfo> {
    private boolean isPrepared;
    private boolean isLoad;

    private NewsListAdapter adapter;
    private FragmentNewsBinding fragmentNewsBinding;

    public static Fragment newInstance(int index) {
        Bundle bundle = new Bundle();
        NewsMainFragment fragment = new NewsMainFragment();
        bundle.putInt(FRAGMENT_INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected NewsListPresenterImpl createPresenter() {
        return new NewsListPresenterImpl();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            fragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
            view = fragmentNewsBinding.getRoot();
            isPrepared = true;
        }
        return view;
    }

    @Override
    protected void initData() {
        if (!isPrepared || !isVisible || isLoad) {
            return;
        }

        LinkedList<NewsListInfo> list = new LinkedList<>();
        adapter = new NewsListAdapter(list);
        adapter.setOnItemClickListener(this);

        fragmentNewsBinding.srfLayout.setOnRefreshListener(this);

        fragmentNewsBinding.recyclerView.setHasFixedSize(true);
        fragmentNewsBinding.recyclerView.setLoadingData(this);
        fragmentNewsBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(Constant.RECYCLERVIEW_LINEAR, LinearLayoutManager.VERTICAL));
        fragmentNewsBinding.recyclerView.setAdapter(adapter);

        fragmentNewsBinding.srfLayout.post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        });

        isLoad = true;
    }


    @Override
    public void onRefresh() {
        page = 1;
        adapter.removeAll();
        mPresenter.requestNetWork(index + 1, page, isNull);
    }

    @Override
    public void onLoadMore() {
        if (!fragmentNewsBinding.srfLayout.isRefreshing()) {
            ++page;
            mPresenter.requestNetWork(index + 1, page, isNull);
        }
    }


    @Override
    public void setData(List<NewsListInfo> datas) {
        if (datas.isEmpty()) {
            isNull = true;
        } else {
            adapter.addAll(datas);
        }
    }

    @Override
    public void netWorkError() {
        Toast(UIUtils.getString(R.string.network_error));
    }

    @Override
    public void showProgress() {
        if (!fragmentNewsBinding.srfLayout.isRefreshing()) {
            fragmentNewsBinding.srfLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        if (fragmentNewsBinding.srfLayout.isRefreshing()) {
            fragmentNewsBinding.srfLayout.setRefreshing(false);
        }
    }

    @Override
    public void showFoot() {
        adapter.isShowFooter(true);
    }

    public void hideFoot() {
        adapter.isShowFooter(false);
    }

    @Override
    public void onItemClick(View view, int position, NewsListInfo info) {
        mPresenter.onClick(info);
    }

    @Override
    public void onItemClick(int id) {
        NewsDetailActivity.startIntent(getActivity(),id);
    }
}
