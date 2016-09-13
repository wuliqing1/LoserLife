package com.example.y.mvp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.y.mvp.R;
import com.example.y.mvp.databinding.NewsListItemBinding;
import com.example.y.mvp.mvp.Bean.NewsListInfo;

import java.util.List;

/**
 * by 12406 on 2016/5/15.
 */
public class NewsListAdapter extends BaseRecyclerViewAdapter<NewsListInfo> {

    public NewsListAdapter(List<NewsListInfo> datas) {
        super(datas);
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, int position, final NewsListInfo data) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).setData(data);
        }
    }

    @Override
    protected BaseRecyclerViewHolder onCreate(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                NewsListItemBinding binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new ViewHolder(binding);
            default:
                return new BaseRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false));
        }
    }


    class ViewHolder extends BaseRecyclerViewHolder {

        private NewsListItemBinding newsListItemBinding;

        public ViewHolder(NewsListItemBinding newsListItemBinding) {
            super(newsListItemBinding.getRoot());
            this.newsListItemBinding = newsListItemBinding;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void setData(@NonNull final NewsListInfo newsListInfo) {
            super.setData(newsListInfo);
            newsListItemBinding.setNewsListInfo(newsListInfo);
            newsListItemBinding.executePendingBindings();
        }
    }
}
