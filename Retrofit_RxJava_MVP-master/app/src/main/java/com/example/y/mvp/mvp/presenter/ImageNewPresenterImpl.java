package com.example.y.mvp.mvp.presenter;


import android.widget.Toast;

import com.example.y.mvp.R;
import com.example.y.mvp.mvp.Bean.ImageNewInfo;
import com.example.y.mvp.mvp.model.BaseDataBridge;
import com.example.y.mvp.mvp.model.BaseModel;
import com.example.y.mvp.mvp.model.ImageNewModelImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.UIUtils;

import java.util.List;

/**
 * by y on 2016/4/29.
 */
public class ImageNewPresenterImpl extends BasePresenterImpl<BaseView.ImageNewView>
        implements BasePresenter.ImageNewPresenter, BaseDataBridge.ImageNewData {

    private final BaseModel.ImageNewModel imageNewModel;


    public ImageNewPresenterImpl() {
        this.imageNewModel = new ImageNewModelImpl();
    }


    @Override
    public void requestNetWork(String id, String rows) {
        if (id.isEmpty()) {
            getView().hideProgress();
            Toast.makeText(UIUtils.getContext(), UIUtils.getString(R.string.image_new_id), Toast.LENGTH_LONG).show();
        } else {
            if (rows.isEmpty()) {
                rows = "20";
            }
//            if (ActivityUtils.syskeyBroadStatus()) {
//                ActivityUtils.closeSyskeyBroad();
//            }
            getView().showProgress();
            imageNewModel.netWorkNew(Integer.valueOf(id), Integer.valueOf(rows), this);
        }
    }

    @Override
    public void onClick(ImageNewInfo info) {
        getView().onItemClick(info.getId(), info.getTitle());
    }

    @Override
    public void addData(List<ImageNewInfo> imageNewInfo) {
        getView().setData(imageNewInfo);
        getView().hideProgress();
    }

    @Override
    public void error() {
        getView().hideProgress();
        getView().netWorkError();
    }
}
