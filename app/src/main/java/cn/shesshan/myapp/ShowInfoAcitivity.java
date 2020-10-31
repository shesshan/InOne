package cn.shesshan.myapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;

public class ShowInfoAcitivity extends AppCompatActivity {
    private RecyclerView rvTimeline;// RecyclerView控件
    private List<DateContent> dateList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        rvTimeline=findViewById(R.id.rvTimeline);
        initData();
    }

    public void initData(){
        List<Entry> entryList=new ArrayList<>();
        for(int i=0;i<10;i++){
            entryList.add(new Entry("经济信息工程学院","西南财经大学第三届国际金融科技论坛SWUFE&CDAR"));
        }
        dateList.add(new DateContent("10.31",entryList));
        dateList.add(new DateContent("11.3" ,entryList));
        dateList.add(new DateContent("11.5",entryList));
        // 设置布局管理器：线性展示item，默认方向vertical
        rvTimeline.setLayoutManager(new LinearLayoutManager(ShowInfoAcitivity.this));
        // 设置适配器Adapter：渲染数据
        rvTimeline.setAdapter(new TimelineAdapter(ShowInfoAcitivity.this,dateList));
    }
}
