package com.example.y.mvp.mvp.view;

import android.text.Spanned;

import com.example.y.mvp.mvp.Bean.ImageDetailInfo;
import com.example.y.mvp.mvp.Bean.ImageListInfo;
import com.example.y.mvp.mvp.Bean.ImageNewInfo;
import com.example.y.mvp.mvp.Bean.JokePicBean;
import com.example.y.mvp.mvp.Bean.JokeTextBean;
import com.example.y.mvp.mvp.Bean.NewsDetailInfo;
import com.example.y.mvp.mvp.Bean.NewsListInfo;
import com.example.y.mvp.mvp.Bean.QiubaiVideo;
import com.example.y.mvp.mvp.Bean.TabNameInfo;
import com.example.y.mvp.mvp.Bean.TabNewsInfo;

import java.util.List;

/**
 * by y on 2016/5/27.
 */
public interface BaseView<T> {


    void setData(List<T> datas);

    void netWorkError();

    void hideProgress();

    void showProgress();

    void showFoot();

    void hideFoot();

    interface JokePicView extends BaseView<JokePicBean.JokePicInfo> {
    }

    interface JokeTextView extends BaseView<JokeTextBean.JokeTextInfo> {
    }

    interface ImageDetailView extends BaseView<ImageDetailInfo> {
    }

    interface ImageListView extends BaseView<ImageListInfo> {
        void onItemClick(int id, String title);
    }

    interface ImageNewView extends BaseView<ImageNewInfo> {
        void onItemClick(int id, String title);
    }

    interface NewsListView extends BaseView<NewsListInfo> {
        void onItemClick(int id);
    }

    interface VideoListView extends BaseView<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {
    }

    interface NewsDetailView {
        void setData(NewsDetailInfo datas);

        void transforMessage(Spanned spanned);

        void netWorkError();

        void hideProgress();

        void showProgress();
    }

    interface TabNameView extends BaseView<TabNameInfo> {
    }

    interface TabNewsView extends BaseView<TabNewsInfo> {
    }

    interface MainView {


        void switchNews();

        void switchImageClassification();

        void switchNewImage();

        void switchJoke();

        void switchVideo();

        void switchAbout();

        void switchTitle(String title);
    }

    interface ToolBarItemView {

        void switchShare();
    }

    interface EmptyView{

    }
}
