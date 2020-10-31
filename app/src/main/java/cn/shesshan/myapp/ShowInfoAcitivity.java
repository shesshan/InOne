package cn.shesshan.myapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.Thread.LoadBitmapThread;

public class ShowInfoAcitivity extends AppCompatActivity {
    private static final String TAG="ShowInfoAcitivity";
    private RecyclerView rvTimeline;// RecyclerView控件
    private List<DateContent> dateList=new ArrayList<>();
    private List<Bitmap> bitTest;
    private List<String> urls;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    Log.i(TAG,"receive message.");
                    List<Bitmap> bitmapList=(List<Bitmap>)msg.obj;
                    bitTest=bitmapList;
                    break;
            }
            initData();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        rvTimeline=findViewById(R.id.rvTimeline);
        urls=new ArrayList<>();
        urls.add("https://shesshan.cn/img/swufe_info.png");
        new LoadBitmapThread(handler,urls).start();
    }

    public void initData(){
        List<Entry> entryList=new ArrayList<>();
        for(int i=0;i<3;i++){
            entryList.add(new Entry("经济信息工程学院",
                    "西南财经大学第三届国际金融科技论坛SWUFE&CDAR",
                    bitTest.get(0)));
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
