package com.example.mvp.mvp.presenter;

public interface BasePresenter {
    interface StudentListPresenter extends BasePresenter {
        void requestNetWork(int id);
    }
}
