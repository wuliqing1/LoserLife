package com.example.y.mvp.mvp.Bean;

/**
 * by 12406 on 2016/5/14.
 */
@SuppressWarnings("ALL")
public class TabNewsInfo {
    /**
     * description : 天狗实时事件:民生热点事件，民生热词排行 做最好的民生热点、热词提取；推送最新的民生实时事件，挖掘最新的民生热词。
     * id : 1
     * keywords : 民生热点事件 民生热词排行 天狗实时事件
     * name : 民生热点
     * seq : 1
     * title : 民生热点事件_民生热词排行-天狗实时事件
     */

    private String description;
    private int id;
    private String keywords;
    private String name;
    private int seq;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
