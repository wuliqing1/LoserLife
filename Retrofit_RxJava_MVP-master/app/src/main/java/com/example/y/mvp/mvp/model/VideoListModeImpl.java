package com.example.y.mvp.mvp.model;

import com.example.y.mvp.mvp.Bean.QiubaiVideo;
import com.example.y.mvp.network.MySubscriber;
import com.example.y.mvp.network.NetWorkRequest;

/**
 * by y on 2016/5/30.
 */
public class VideoListModeImpl implements BaseModel.VideoListModel {

    @Override
    public void netWorkJoke(int page, String keyword, final BaseDataBridge.VideoList videoList) {

        NetWorkRequest.videoList(page, keyword, new MySubscriber<QiubaiVideo>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                videoList.error();
            }

            @Override
            public void onNext(QiubaiVideo qiubaiVideo) {
                super.onNext(qiubaiVideo);
                videoList.addData(qiubaiVideo.getShowapi_res_body().getPagebean().getContentlist());
            }
        });

    }
}
