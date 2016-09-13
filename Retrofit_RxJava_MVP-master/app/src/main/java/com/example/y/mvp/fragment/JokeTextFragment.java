package com.example.y.mvp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.mvp.R;
import com.example.y.mvp.adapter.BaseRecyclerViewAdapter;
import com.example.y.mvp.adapter.JokeTextAdapter;
import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.mvp.Bean.JokeTextBean;
import com.example.y.mvp.mvp.presenter.JokeTextPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.ActivityUtils;
import com.example.y.mvp.utils.UIUtils;
import com.example.y.mvp.widget.MyRecyclerView;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * by y on 2016/5/30.
 */
public class JokeTextFragment extends BaseFragment<BaseView.JokeTextView, JokeTextPresenterImpl>
        implements SwipeRefreshLayout.OnRefreshListener,
        MyRecyclerView.LoadingData, BaseView.JokeTextView, BaseRecyclerViewAdapter.OnItemLongClickListener<JokeTextBean.JokeTextInfo> {

    @Bind(R.id.recyclerView)
    MyRecyclerView recyclerView;
    @Bind(R.id.srf_layout)
    SwipeRefreshLayout srfLayout;

    private boolean isPrepared;
    private boolean isLoad;

    private JokeTextAdapter adapter;


    @Override
    protected JokeTextPresenterImpl createPresenter() {
        return new JokeTextPresenterImpl();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_joke_text, null);
            isPrepared = true;
        }
        return view;
    }

    @Override
    protected void initData() {

        if (!isPrepared || !isVisible || isLoad) {
            return;
        }

        List<JokeTextBean.JokeTextInfo> jokeTextInfo = new LinkedList<>();

        srfLayout.setOnRefreshListener(this);

        adapter = new JokeTextAdapter(jokeTextInfo);
        adapter.setOnLongClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLoadingData(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(Constant.RECYCLERVIEW_LINEAR, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

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
        adapter.removeAll();
        page = 1;
        mPresenter.requestNetWork(page, isNull);
    }

    @Override
    public void onLoadMore() {
        if (!srfLayout.isRefreshing()) {
            ++page;
            mPresenter.requestNetWork(page, isNull);
        }
    }

    @Override
    public void setData(List<JokeTextBean.JokeTextInfo> datas) {
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
        if (!srfLayout.isRefreshing()) {
            srfLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        if (srfLayout.isRefreshing()) {
            srfLayout.setRefreshing(false);
        }
    }

    @Override
    public void showFoot() {
        adapter.isShowFooter(true);
    }

    @Override
    public void hideFoot() {
        adapter.isShowFooter(false);
    }

    @Override
    public void onLongClick(View view, int position, JokeTextBean.JokeTextInfo info) {
        ActivityUtils.share(getActivity(), String.valueOf(Html.fromHtml(info.getText())));
    }

}
