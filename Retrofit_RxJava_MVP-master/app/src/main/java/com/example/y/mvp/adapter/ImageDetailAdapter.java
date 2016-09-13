package com.example.y.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.y.mvp.mvp.Bean.ImageDetailInfo;
import com.example.y.mvp.network.Api;
import com.example.y.mvp.utils.ImageLoaderUtils;

import java.util.List;

/**
 * by y on 2016/5/3.
 */
public class ImageDetailAdapter extends BasePagerAdapter<ImageDetailInfo> {

    public interface onClickShowDialogListener {
        void showDialog(ImageView imageView, int position);
    }

    private onClickShowDialogListener mClickShowDialogListener;

    public void setClickShowDialogListener(onClickShowDialogListener onClickShowDialogListener) {
        this.mClickShowDialogListener = onClickShowDialogListener;
    }

    public ImageDetailAdapter(Context context, List<ImageDetailInfo> datas) {
        super(context, datas);
    }

    @Override
    protected Object instantiate(ViewGroup container, final int position, ImageDetailInfo data) {
        final ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoaderUtils.display(mContext, imageView, Api.IMAGER_URL + data.getSrc());
        container.addView(imageView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mClickShowDialogListener != null) {
                    mClickShowDialogListener.showDialog(imageView, position);
                }
                return true;
            }
        });
        return imageView;
    }

}
