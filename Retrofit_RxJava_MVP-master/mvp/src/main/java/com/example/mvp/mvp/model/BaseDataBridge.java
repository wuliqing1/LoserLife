package com.example.mvp.mvp.model;

import com.example.mvp.mvp.bean.Student;

import java.util.List;

public interface BaseDataBridge<T> {
    void addData(List<T> datas);
    void error();
    interface StudentListData extends BaseDataBridge<Student> {
    }
}
