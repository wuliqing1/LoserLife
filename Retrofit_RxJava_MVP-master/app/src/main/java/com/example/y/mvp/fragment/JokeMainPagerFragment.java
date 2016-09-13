package com.example.y.mvp.fragment;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.mvp.R;
import com.example.y.mvp.adapter.TabJokeAdapter;
import com.example.y.mvp.databinding.FragmentJokeViewpagerBinding;
import com.example.y.mvp.mvp.presenter.BasePresenterImpl;
import com.example.y.mvp.utils.UIUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * by y on 2016/5/30.
 */
public class JokeMainPagerFragment extends BaseFragment {

    private FragmentJokeViewpagerBinding fragmentJokeViewpagerBinding;

    @Override
    protected BasePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        fragmentJokeViewpagerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_joke_viewpager, container, false);
        return fragmentJokeViewpagerBinding.getRoot();
    }

    @Override
    protected void initData() {

        List<String> data = new LinkedList<>();
        data.add(UIUtils.getString(R.string.joke_text));
        data.add(UIUtils.getString(R.string.joke_pic));


        TabJokeAdapter adapter = new TabJokeAdapter(getChildFragmentManager(), data);

        fragmentJokeViewpagerBinding.viewPager.setAdapter(adapter);
        fragmentJokeViewpagerBinding.tabLayout.setupWithViewPager(fragmentJokeViewpagerBinding.viewPager);
    }
}
