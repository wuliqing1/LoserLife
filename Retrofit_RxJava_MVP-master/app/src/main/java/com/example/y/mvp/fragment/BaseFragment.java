package com.example.y.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.y.mvp.mvp.presenter.BasePresenterImpl;
import com.example.y.mvp.utils.LogUtils;
import com.example.y.mvp.utils.RxUtils;
import com.example.y.mvp.utils.UIUtils;

import butterknife.ButterKnife;

/**
 * by y on 2016/4/28.
 */
public abstract class BaseFragment<V, T extends BasePresenterImpl<V>> extends Fragment {

    protected boolean isVisible;
    protected static final String FRAGMENT_INDEX = "fragment_index";
    protected int index = 0;
    protected int page = 1;
    protected boolean isNull = false;
    protected View view;
    protected T mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(FRAGMENT_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView(inflater, container);
        ButterKnife.bind(this, view);
        LogUtils.i("BaseFragment", getClass().getSimpleName());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        initData();
    }

    protected abstract T createPresenter();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    void Toast(String content) {
        Toast.makeText(UIUtils.getContext(), content, Toast.LENGTH_LONG).show();
    }


    private void onVisible() {
        initData();
    }

    private void onInvisible() {
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ButterKnife.unbind(this);
        RxUtils.unsubscribe();
    }

}
