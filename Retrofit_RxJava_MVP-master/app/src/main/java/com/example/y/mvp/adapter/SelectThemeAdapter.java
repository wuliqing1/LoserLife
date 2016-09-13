package com.example.y.mvp.adapter;

import android.content.Context;

import com.example.y.mvp.R;

import java.util.List;

/**
 * Created by 10172915 on 2016/8/29.
 */

public class SelectThemeAdapter extends CommonAdapter<String> {
    public SelectThemeAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.select_theme_tv, s);
    }
}
