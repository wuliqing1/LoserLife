package com.example.mvp.mvp.model;

import android.os.Handler;
import android.os.Looper;

import com.example.mvp.mvp.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10172915 on 2016/9/6.
 */

public class StudentListModelImpl implements BaseModel.StudentListModel {
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void netWork(final BaseDataBridge.StudentListData model) {

    }

    @Override
    public void requestStudentList(int id, final BaseDataBridge.StudentListData studentListData) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    final List<Student> data = new ArrayList<Student>();
                    data.add(new Student("wuliqing"));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            studentListData.addData(data);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
