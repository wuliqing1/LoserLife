package com.example.y.mvp.network;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;

import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.mvp.Bean.BaseBean;
import com.example.y.mvp.mvp.Bean.JokePicBean;
import com.example.y.mvp.mvp.Bean.JokeTextBean;
import com.example.y.mvp.mvp.Bean.NewsDetailInfo;
import com.example.y.mvp.mvp.Bean.QiubaiVideo;
import com.example.y.mvp.utils.RxUtils;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * by y on 2016/5/6.
 */
public class NetWorkRequest {

    public static void newsDetail(int id, Subscriber<NewsDetailInfo> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void newsList(int id, int page, Subscriber<BaseBean.NewsListBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getNewsList(id, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void tabNews(Subscriber<BaseBean.TabNewsBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getTabNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void imageDetail(int id, Subscriber<BaseBean.ImageDetailBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getImageDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void imageList(int id, int page, Subscriber<BaseBean.ImageListBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getImageList(id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void imageNew(int id, int rows, Subscriber<BaseBean.ImageNewBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getImageNews(id, rows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void tabName(Subscriber<BaseBean.TabNameBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getTngouApi().getTabName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /////////////////////////////////////////////////////////////

    public static void jokeTextList(int page, Subscriber<JokeTextBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getBaiDuApi().getJokeText(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void jokePicList(int page, Subscriber<JokePicBean> subscriber) {
        RxUtils.unsubscribe();
        RxUtils.subscription = Network.getBaiDuApi().getJokePic(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void videoList(int page, String keyword, Subscriber<QiubaiVideo> subscriber) {
        RxUtils.unsubscribe();
        SimpleDateFormat df = new SimpleDateFormat(Constant.DATE_TIME_FORMAT);
        String timestamp = df.format(new Date());
        RxUtils.subscription = Network.getRouTeApi().video(page + "", "249", timestamp, keyword, "41", Api.ROUTE_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void newsMessageWithImage(final String message, Subscriber<Spanned> subscriber) {
        RxUtils.unsubscribe();
        Observable observable = Observable.create(new Observable.OnSubscribe<Spanned>() {
            @Override
            public void call(Subscriber<? super Spanned> subscriber) {
                Spanned spanned = Html.fromHtml(message, new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        InputStream is = null;
                        try {
                            is = (InputStream) new URL(source).getContent();
                            Drawable d = Drawable.createFromStream(is, "src");
                            d.setBounds(0, 0, d.getIntrinsicWidth(),
                                    d.getIntrinsicHeight());
                            is.close();
                            return d;
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }, null);

                subscriber.onNext(spanned);
                subscriber.onCompleted();
            }
        });

        RxUtils.subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
