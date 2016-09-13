package com.example.y.mvp.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.y.mvp.R;
import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.fragment.AboutFragment;
import com.example.y.mvp.fragment.ImageNewFragment;
import com.example.y.mvp.fragment.ImageViewPagerFragment;
import com.example.y.mvp.fragment.JokeMainPagerFragment;
import com.example.y.mvp.fragment.NewsViewPagerFragment;
import com.example.y.mvp.fragment.VideoFragment;
import com.example.y.mvp.mvp.presenter.MainViewPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.UIUtils;

import butterknife.Bind;

public class MainActivity extends MvpActivity<BaseView.MainView, MainViewPresenterImpl> implements BaseView.MainView {

    @SuppressWarnings("unused")
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @SuppressWarnings("unused")
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @SuppressWarnings("unused")
    @Bind(R.id.dl_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.fragment)
    FrameLayout fragment;

    public static String CURRENT_MENU_PAGE_INDEX = "current_menu_page_index";
    int cur_menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected boolean isSupportSwipe() {
        return false;
    }

    @Override
    protected MainViewPresenterImpl createPresenter() {
        return new MainViewPresenterImpl();
    }

    private void init() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            cur_menu_id = getIntent().getExtras().getInt(CURRENT_MENU_PAGE_INDEX, R.id.navigation_item_news);
        } else {
            cur_menu_id = R.id.navigation_item_news;
        }
        mPresenter.switchTitle(cur_menu_id);
        setSupportActionBar(toolBar);
        setupDrawerContent(navigationView);
        mPresenter.switchId(cur_menu_id);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        cur_menu_id = menuItem.getItemId();
                        mPresenter.switchId(cur_menu_id);
                        mPresenter.switchTitle(cur_menu_id);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (Constant.BACK_EXIT) {
                super.onBackPressed();
                return;
            }
            Constant.BACK_EXIT = true;
            Toast(UIUtils.getString(R.string.exit_app));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Constant.BACK_EXIT = false;
                }
            }, 2000);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void switchNews() {
        replaceFragment(new NewsViewPagerFragment());
    }

    @Override
    public void switchImageClassification() {
        replaceFragment(new ImageViewPagerFragment());
    }

    @Override
    public void switchNewImage() {
        replaceFragment(new ImageNewFragment());
    }

    @Override
    public void switchJoke() {
        replaceFragment(new JokeMainPagerFragment());
    }

    @Override
    public void switchVideo() {
        replaceFragment(new VideoFragment());
    }

    @Override
    public void switchAbout() {
        replaceFragment(new AboutFragment());
    }

    @Override
    public void switchTitle(String title) {
        toolBar.setTitle(title);
    }

    void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }

}
