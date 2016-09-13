package com.example.mvp.mvp.model;

public interface BaseModel<T> {
    void netWork(T model);
    interface StudentListModel extends BaseModel<BaseDataBridge.StudentListData> {
        void requestStudentList(int id, BaseDataBridge.StudentListData studentListData);
    }
}
