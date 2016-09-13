package com.example.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mvvm.adapter.MyAdapter;
import com.example.mvvm.bean.User;
import com.example.mvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private List<User> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            User user = new User("wu" + i, "liqing" + i);
//            http://img3.imgtn.bdimg.com/it/u=2055314771,2851808558&fm=206&gp=0.jpg
            user.photo_url.set("http://pic.baike.soso.com/p/20131213/20131213115637-1062134399.jpg");
            data.add(user);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityMainBinding.recyclerView.setLayoutManager(layoutManager);
        activityMainBinding.recyclerView.setAdapter(new MyAdapter(data));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
