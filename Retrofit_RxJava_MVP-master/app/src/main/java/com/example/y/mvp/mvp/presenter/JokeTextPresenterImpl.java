package com.example.y.mvp.mvp.presenter;

import com.example.y.mvp.mvp.Bean.JokeTextBean;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.JokeTextModeImpl;
import com.example.y.mvp.mvp.view.BaseView;

import java.util.List;

/**
 * by y on 2016/5/30.
 */
public class JokeTextPresenterImpl extends BasePresenterImpl<BaseView.JokeTextView>
        implements BasePresenter.JokeTextPresenter, BaseDataBridge.JokeTextList {

    private final BaseModel.JokeTextListModel jokeListModel;

    public JokeTextPresenterImpl() {
        this.jokeListModel = new JokeTextModeImpl();
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
        jokeListModel.netWorkJoke(page, this);
    }

    @Override
    public void addData(List<JokeTextBean.JokeTextInfo> datas) {
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
