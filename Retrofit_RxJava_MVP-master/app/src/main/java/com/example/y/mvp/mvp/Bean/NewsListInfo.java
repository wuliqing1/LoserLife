package com.example.y.mvp.mvp.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * by 12406 on 2016/5/15.
 */
@SuppressWarnings("ALL")
public class NewsListInfo extends BaseObservable {


    /**
     * count : 0
     * description : 据悉，1月6日，刚结婚的印尼女子米尔娜约了两位闺蜜杰西卡和Hani在雅加达的一间咖啡馆见面，她喝下咖啡时还大叫“难喝”，但不久后就倒地死亡
     * fcount : 0
     * fromname : 齐鲁晚报
     * fromurl : http://www.qlwb.com.cn/2016/0811/695724.shtml
     * id : 12626
     * img : /top/160811/f314412708bb70b5d6c3ff4d53447006.jpg
     * keywords : 女子不满投毒闺蜜
     * rcount : 0
     * time : 1470900295000
     * title : 女子不满闺蜜咖啡投毒氰化物 大叫“难喝”后身亡
     * topclass : 0
     */

    private int count;
    private String description;
    private int fcount;
    private String fromname;
    private String fromurl;
    private int id;
    private String img;
    private String keywords;
    private int rcount;
    private long time;
    private String title;
    private int topclass;

    @Bindable
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public String getFromurl() {
        return fromurl;
    }

    public void setFromurl(String fromurl) {
        this.fromurl = fromurl;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.newsListInfo);
    }

    @Bindable
    public int getTopclass() {
        return topclass;
    }

    public void setTopclass(int topclass) {
        this.topclass = topclass;
        notifyPropertyChanged(BR.newsListInfo);
    }
}
