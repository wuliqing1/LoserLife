package com.example.mvp.mvp.presenter;

import com.example.mvp.mvp.bean.Student;
import com.example.mvp.mvp.model.BaseDataBridge;
import com.example.mvp.mvp.model.BaseModel;
import com.example.mvp.mvp.model.StudentListModelImpl;
import com.example.mvp.mvp.view.BaseView;

import java.util.List;

/**
 * Created by 10172915 on 2016/9/6.
 */

public class StudentListPresenterImpl extends BasePresenterImpl<BaseView.StudentListView>
        implements BasePresenter.StudentListPresenter, BaseDataBridge.StudentListData {
    private BaseModel.StudentListModel studentListModel;

    public StudentListPresenterImpl() {
        studentListModel = new StudentListModelImpl();
    }

    @Override
    public void requestNetWork(int id) {
        studentListModel.requestStudentList(id, this);
    }

    @Override
    public void addData(List<Student> datas) {
        getView().setData(datas);
    }

    @Override
    public void error() {

    }
}
