package com.example.y.mvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.y.mvp.MyApplication;
import com.example.y.mvp.R;
import com.example.y.mvp.activity.swipeBack.SwipeBackActivityBase;
import com.example.y.mvp.activity.swipeBack.SwipeBackActivityHelper;
import com.example.y.mvp.activity.swipeBack.SwipeBackLayout;
import com.example.y.mvp.activity.swipeBack.Utils;
import com.example.y.mvp.mvp.presenter.BasePresenterImpl;
import com.example.y.mvp.utils.DayNightHelper;
import com.example.y.mvp.utils.RxUtils;

import butterknife.ButterKnife;

/**
 * by y on 2016/4/28.
 */
public abstract class MvpActivity<V, T extends BasePresenterImpl<V>> extends AppCompatActivity
        implements SwipeBackActivityBase {

    protected T mPresenter;
    protected DayNightHelper mDayNightHelper;
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        initTheme();
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        if (isSupportSwipe()) {
            mHelper = new SwipeBackActivityHelper(this);
            mHelper.onActivityCreate();
            initSwipeLayout();
        }
        initData();
    }

    protected void initData(){}

    protected boolean isSupportSwipe() {
        return true;
    }

    protected void initSwipeLayout() {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mHelper != null)
            mHelper.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        if (mHelper == null) {
            return null;
        }
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    protected void initTheme() {
        mDayNightHelper = new DayNightHelper(this);
        if (mDayNightHelper.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }

    protected abstract T createPresenter();

    void Toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    public static Context getContext() {
        return MyApplication.getInstance();
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        if (isRxUnsubscribe()) {
            RxUtils.unsubscribe();
        }
    }

    protected boolean isRxUnsubscribe() {
        return true;
    }
}
