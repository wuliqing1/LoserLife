package com.example.y.mvp.mvp.presenter;


import com.example.y.mvp.mvp.Bean.ImageListInfo;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.ImageListModelImpl;
import com.example.y.mvp.mvp.view.BaseView;

import java.util.List;

/**
 * by y on 2016/4/29.
 */
public class ImageListPresenterImpl extends BasePresenterImpl<BaseView.ImageListView>
        implements BasePresenter.ImageListPresenter, BaseDataBridge.ImageListData {

    private final BaseModel.ImageListModel imageListModel;

    public ImageListPresenterImpl() {
        this.imageListModel = new ImageListModelImpl();
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
        imageListModel.netWorkList(id, page, this);
    }


    @Override
    public void onClick(ImageListInfo info) {
        getView().onItemClick(info.getId(), info.getTitle());
    }


    @Override
    public void addData(List<ImageListInfo> imageListInfo) {
        getView().setData(imageListInfo);
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
