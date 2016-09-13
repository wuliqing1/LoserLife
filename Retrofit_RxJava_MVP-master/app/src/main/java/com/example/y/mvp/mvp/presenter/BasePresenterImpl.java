package com.example.y.mvp.mvp.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * by y on 2016/5/27.
 */
@SuppressWarnings("ALL")
public abstract class BasePresenterImpl<T> {
    protected Reference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
