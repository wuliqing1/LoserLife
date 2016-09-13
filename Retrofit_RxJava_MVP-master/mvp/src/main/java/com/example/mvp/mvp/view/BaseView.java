package com.example.mvp.mvp.view;

import com.example.mvp.mvp.bean.Student;

import java.util.List;

public interface BaseView<T> {
    void setData(List<T> datas);
    void hideProgress();
    void showProgress();

    interface StudentListView extends BaseView<Student> {
    }
}
