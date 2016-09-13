package com.example.mvvm.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 10172915 on 2016/8/30.
 */

public class ImageUtils {
    @BindingAdapter({"image"})
    public static void imageLoader(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }
}
