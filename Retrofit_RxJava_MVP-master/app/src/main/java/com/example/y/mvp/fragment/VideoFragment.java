package com.example.y.mvp.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.mvp.R;
import com.example.y.mvp.activity.VideoPlayActivity;
import com.example.y.mvp.adapter.VideoListAdapter;
import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.mvp.Bean.QiubaiVideo;
import com.example.y.mvp.mvp.presenter.VideoListPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.UIUtils;
import com.example.y.mvp.widget.MyRecyclerView;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

public class VideoFragment extends BaseFragment<BaseView.VideoListView, VideoListPresenterImpl> implements SwipeRefreshLayout.OnRefreshListener,
        MyRecyclerView.LoadingData, BaseView.VideoListView, VideoListAdapter.OnItemPlayClickListener<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    @Bind(R.id.recyclerView)
    MyRecyclerView recyclerView;
    @Bind(R.id.srf_layout)
    SwipeRefreshLayout srfLayout;

    private VideoListAdapter mVideoListAdapter;

    private boolean isPrepared;
    private boolean isLoad;

    @Override
    protected VideoListPresenterImpl createPresenter() {
        return new VideoListPresenterImpl();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_video, null);
            isPrepared = true;
        }
        return view;
    }

    @Override
    protected void initData() {
//        if (!isPrepared || isLoad) {
//            return;
//        }
        List<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeen = new LinkedList<>();

        srfLayout.setOnRefreshListener(this);
        mVideoListAdapter = new VideoListAdapter(contentlistBeen);
        mVideoListAdapter.setPlayItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLoadingData(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(Constant.RECYCLERVIEW_GRIDVIEW, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mVideoListAdapter);

        srfLayout.post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        });
        isLoad = true;
    }

    @Override
    public void onRefresh() {
        mVideoListAdapter.removeAll();
        page = 1;
        mPresenter.requestNetWork(page, "", isNull);
    }

    @Override
    public void onLoadMore() {
        if (!srfLayout.isRefreshing()) {
            ++page;
            mPresenter.requestNetWork(page, "", isNull);
        }
    }

    @Override
    public void setData(List<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> datas) {
        if (datas.isEmpty()) {
            isNull = true;
        } else {
            mVideoListAdapter.addAll(datas);
        }
    }

    @Override
    public void netWorkError() {
        Toast(UIUtils.getString(R.string.network_error));
    }

    @Override
    public void hideProgress() {
        if (srfLayout != null && srfLayout.isRefreshing()) {
            srfLayout.setRefreshing(false);
        }
    }

    @Override
    public void showProgress() {
        if (srfLayout != null && !srfLayout.isRefreshing()) {
            srfLayout.setRefreshing(true);
        }
    }

    @Override
    public void showFoot() {
        mVideoListAdapter.isShowFooter(true);
    }

    @Override
    public void hideFoot() {
        mVideoListAdapter.isShowFooter(false);
    }

    @Override
    public void onPlayItemClick(View view, QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean info) {
        Intent intent = VideoPlayActivity.startSelf(getActivity(), info.getVideo_uri(), info.getText());
        getActivity().startActivity(intent);
    }
}
