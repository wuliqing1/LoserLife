package com.example.y.mvp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.y.mvp.R;
import com.example.y.mvp.mvp.Bean.QiubaiVideo;
import com.example.y.mvp.utils.UIUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import butterknife.Bind;
import io.vov.vitamio.widget.VideoView;

/**
 * by y on 2016/5/30.
 */
public class VideoListAdapter extends BaseRecyclerViewAdapter<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    BitmapUtils bitmapUtils = null;
    private OnItemPlayClickListener mPlayItemClickListener;
    public interface OnItemPlayClickListener<T>{
        void onPlayItemClick(View view, T info);
    }

    public VideoListAdapter(List<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> datas) {
        super(datas);
        bitmapUtils = new BitmapUtils(UIUtils.getContext());
    }

    @Override
    protected void onBind(RecyclerView.ViewHolder holder, int position, QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean data) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).setData(data);
        }
    }

    @Override
    protected BaseRecyclerViewHolder onCreate(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_video_item, parent, false));
            default:
                return new BaseRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false));
        }
    }

    public void setPlayItemClickListener(OnItemPlayClickListener<QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> listener) {
        this.mPlayItemClickListener = listener;
    }

    class ViewHolder extends BaseRecyclerViewHolder {

        @Bind(R.id.videoview)
        VideoView videoview;
        @Bind(R.id.video_img)
        ImageView videoImg;
        @Bind(R.id.play)
        ImageView play;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.hit)
        TextView hit;
        @Bind(R.id.hate)
        TextView hate;
        @Bind(R.id.content)
        RelativeLayout content;
        @Bind(R.id.viewSwitcher)
        CardView viewSwitcher;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void setData(@NonNull QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
            super.setData(item);
            hit.setText("\uD83D\uDC4D" + item.getLove().trim());
            hate.setText("\uD83D\uDC4E" + item.getHate().trim());
            title.setText(item.getText().trim());
            play.setOnClickListener(new PlayOnClickListener(item));
            String imgUrl = item.getProfile_image();
//            if (TextUtils.isEmpty(imgUrl = item.getWeixin_url())) {
//                imgUrl = item.getProfile_image();
//            }
            imgUrl = imgUrl.trim();
            bitmapUtils.display(videoImg, imgUrl);
//            ImageLoaderUtils.display(UIUtils.getContext(), videoImg, imgUrl);
        }

        class PlayOnClickListener implements View.OnClickListener {
            QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean item;

            public PlayOnClickListener(QiubaiVideo.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
                this.item = item;
            }

            @Override
            public void onClick(View v) {
                if (mPlayItemClickListener != null) {
                    mPlayItemClickListener.onPlayItemClick(v,item);
                }
            }
        }
    }
}
