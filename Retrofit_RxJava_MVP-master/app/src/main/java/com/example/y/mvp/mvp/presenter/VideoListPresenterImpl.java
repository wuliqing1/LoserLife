package com.example.y.mvp.mvp.presenter;

import com.example.y.mvp.mvp.Bean.QiubaiVideo;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.VideoListModeImpl;
import com.example.y.mvp.mvp.view.BaseView;

import java.util.List;

/**
 * by y on 2016/5/30.
 */
public class VideoListPresenterImpl extends BasePresenterImpl<BaseView.VideoListView>
        implements BasePresenter.VideoListPresenter, BaseDataBridge.VideoList {

    private final BaseModel.VideoListModel videoListModel;

    public VideoListPresenterImpl() {
        this.videoListModel = new VideoListModeImpl();
    }

    @Override
    public void requestNetWork(int page, String keyword, boolean isNull) {
        if (page == 1) {
            getView().showProgress();
        } else {
            if (!isNull) {
                getView().showFoot();
            }
        }
        videoListModel.netWorkJoke(page, keyword, this);
    }

    @Override
    public void addData(List<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> datas) {
        getView().setData(datas);
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
