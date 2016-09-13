package com.example.y.mvp.mvp.presenter;

import com.example.y.mvp.mvp.Bean.JokePicBean;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.JokePicModeImpl;
import com.example.y.mvp.mvp.view.BaseView;

import java.util.List;

/**
 * by y on 2016/5/30.
 */
public class JokePicPresenterImpl extends BasePresenterImpl<BaseView.JokePicView>
        implements BasePresenter.JokePicPresenter, BaseDataBridge.JokePicList {

    private final BaseModel.JokePicListModel jokePicListModel;


    public JokePicPresenterImpl() {
        jokePicListModel = new JokePicModeImpl();
    }

    @Override
    public void requestNetWork(int page, boolean isNull) {
        if (page == 1) {
            getView().showProgress();
        } else {
            if (!isNull) {
                getView().showFoot();
            }
        }
        jokePicListModel.netWorkJoke(page, this);
    }

    @Override
    public void addData(List<JokePicBean.JokePicInfo> datas) {
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
