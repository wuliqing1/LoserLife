package com.example.y.mvp.mvp.presenter;

import com.example.y.mvp.mvp.Bean.NewsListInfo;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.NewsListModelImpl;
import com.example.y.mvp.mvp.view.BaseView;

import java.util.List;

/**
 * by 12406 on 2016/5/15.
 */
public class NewsListPresenterImpl extends BasePresenterImpl<BaseView.NewsListView>
        implements BasePresenter.NewsListPresenter, BaseDataBridge.NewsListData {

    private final BaseModel.NewsListModel newsListModel;

    public NewsListPresenterImpl() {
        this.newsListModel = new NewsListModelImpl();
    }


    @Override
    public void requestNetWork(int id, int page, boolean isNull) {
        if (page == 1) {
            getView().showProgress();
        } else {
            if (!isNull) {
                getView().showFoot();
            }
        }
        newsListModel.netWorkNewList(id, page, this);
    }

    @Override
    public void onClick(NewsListInfo info) {
        getView().onItemClick(info.getId());
    }


    @Override
    public void addData(List<NewsListInfo> tngou) {
        getView().setData(tngou);
        getView().hideFoot();
        getView().hideProgress();
    }

    @Override
    public void error() {
        getView().hideFoot();
        getView().hideProgress();
        getView().netWorkError();
    }
}
