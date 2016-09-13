package com.example.y.mvp.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.y.mvp.R;
import com.example.y.mvp.adapter.ImageDetailAdapter;
import com.example.y.mvp.mvp.Bean.ImageDetailInfo;
import com.example.y.mvp.mvp.presenter.ImageDetailPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.network.Api;
import com.example.y.mvp.utils.ActivityUtils;
import com.example.y.mvp.utils.CompetenceUtils;
import com.example.y.mvp.utils.DiaLogUtils;
import com.example.y.mvp.utils.UIUtils;
import com.example.y.mvp.widget.MyOnPageChangeListener;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;


/**
 * by y on 2016/4/29.
 */
public class ImageDetailActivity extends MvpActivity<BaseView.ImageDetailView, ImageDetailPresenterImpl>
        implements BaseView.ImageDetailView, BaseView.ToolBarItemView,ImageDetailAdapter.onClickShowDialogListener {

    @SuppressWarnings("unused")
    @Bind(R.id.viewPager)
    ViewPager viewPager;
//    @SuppressWarnings("unused")
//    @Bind(R.id.toolBar)
//    Toolbar toolBar;

    private int id;
    private int pos;
    private LinkedList<ImageDetailInfo> list;
//    private BasePresenter.ToolBarItemPresenter toolBarItemPresenter;
    private ImageDetailAdapter bigImageAdapter;


    public static void startIntent(Activity activity, int id, String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("title", title);
        ActivityUtils.startActivity(activity, ImageDetailActivity.class, bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        toolBar.setTitle(UIUtils.getString(R.string.image_detail));
//        setSupportActionBar(toolBar);
        CompetenceUtils.Storage(this);
        getBundle();
        init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected ImageDetailPresenterImpl createPresenter() {
        return new ImageDetailPresenterImpl();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.competence(requestCode, grantResults);
    }

    private void init() {
//        toolBarItemPresenter = new ToolBarItemPresenterImpl(this);
        list = new LinkedList<>();
        mPresenter.requestNetWork(id);
        bigImageAdapter = new ImageDetailAdapter(this,list);
//        bigImageAdapter.setClickShowDialogListener(this);
//        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                toolBarItemPresenter.switchId(item.getItemId());
//                return true;
//            }
//        });

        viewPager.addOnPageChangeListener(new MyOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                pos = position;
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_detail;
    }


    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            id = bundle.getInt("id");
        }
    }

    @Override
    public void netWorkError() {
        Toast(UIUtils.getString(R.string.network_error));
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showFoot() {

    }

    @Override
    public void hideFoot() {

    }


    @Override
    public void setData(List<ImageDetailInfo> datas) {
        if (!datas.isEmpty()) {
            list.addAll(datas);
            viewPager.setAdapter(bigImageAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public void switchShare() {
        ActivityUtils.share(this, Api.IMAGER_URL + list.get(pos).getSrc());
    }

    @Override
    public void showDialog(ImageView imageView, int position) {
        DiaLogUtils.iamgeViewDialog(this, imageView, position);
    }
}
