package com.example.mvp;

import android.widget.Toast;

import com.example.mvp.mvp.bean.Student;
import com.example.mvp.mvp.presenter.StudentListPresenterImpl;
import com.example.mvp.mvp.view.BaseView;

import java.util.List;

public class MainActivity extends MvpActivity<BaseView.StudentListView, StudentListPresenterImpl>
        implements BaseView.StudentListView {

    @Override
    protected StudentListPresenterImpl createPresenter() {
        return new StudentListPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.requestNetWork(1);
    }

    @Override
    public void setData(List<Student> datas) {
        Toast.makeText(this, "datas = " + datas.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }
}
