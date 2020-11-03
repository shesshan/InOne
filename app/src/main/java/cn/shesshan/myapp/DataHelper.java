package cn.shesshan.myapp;

import android.graphics.Bitmap;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;

public class DataHelper {
    private List<DateContent> dateList;
    private List<Entry> entryList;
    private List<Bitmap> bitTest;

    public void initData(){
        List<Entry> entryList=new ArrayList<>();
        for(int i=0;i<3;i++){
            entryList.add(new Entry("经济信息工程学院",
                    i+": 西南财经大学第三届国际金融科技论坛SWUFE&CDAR",
                    bitTest.get(0)));
        }
        dateList.add(new DateContent("10.31",entryList));
        dateList.add(new DateContent("11.3" ,entryList));
        dateList.add(new DateContent("11.5",entryList));
    }
}
