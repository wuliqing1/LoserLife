package com.example.y.mvp.mvp.presenter;

import android.text.Spanned;

import com.example.y.mvp.mvp.Bean.NewsDetailInfo;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.NewsDetailModelImpl;
import com.example.y.mvp.mvp.view.BaseView;

/**
 * by 12406 on 2016/5/30.
 */
public class NewsDetailPresenterImpl extends BasePresenterImpl<BaseView.NewsDetailView>
        implements BasePresenter.NewsDetailPresenter, BaseDataBridge.NewsDetailData {

    private final BaseModel.NewsDetailModel newsDetailModel;

    public NewsDetailPresenterImpl() {
        newsDetailModel = new NewsDetailModelImpl();
    }

    @Override
    public void requestNetWork(int id) {
        getView().showProgress();
        newsDetailModel.netWorkNewsDetail(id, this);
    }

    @Override
    public void addData(final NewsDetailInfo datas) {
        getView().setData(datas);
        newsDetailModel.netWorkNewsMessageWithImage(datas.getMessage(), this);
    }

    @Override
    public void setMessage(Spanned spanned) {
        getView().transforMessage(spanned);
        getView().hideProgress();
    }

    @Override
    public void error() {
        getView().hideProgress();
        getView().netWorkError();
    }
}
