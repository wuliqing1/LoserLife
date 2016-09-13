package com.example.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mvp.mvp.presenter.BasePresenterImpl;

public abstract class MvpActivity<V, T extends BasePresenterImpl<V>> extends AppCompatActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        setContentView(getLayoutId());
        initData();
    }

    protected void initData() {
    }

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}
