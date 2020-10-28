package cn.shesshan.myapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.Entity.Entry;

public class ShowInfoAcitivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<Entry> entryList=new ArrayList<>();
    private TimelineAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        rv=findViewById(R.id.rvinfo);
        initData();
    }

    public void initData(){
        entryList.add(new Entry("经济信息工程学院学生职业发展中心","《2020年保研交流分享会》"));
        entryList.add(new Entry("经济信息工程学院" ,"《科技节》来了！"));
        entryList.add(new Entry("西财金融投资协会","《学超股权交易大会》——迈出实操第一步"));
        adapter=new TimelineAdapter(ShowInfoAcitivity.this,entryList);
        rv.setLayoutManager(new LinearLayoutManager(ShowInfoAcitivity.this));
        rv.setAdapter(adapter);
    }
}
