package cn.shesshan.myapp.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.DataHelper;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;
import cn.shesshan.myapp.Thread.LoadNetworkDataThread;

public class ShowInfoFragment extends Fragment {
    private static final String TAG="ShowInfoActivity";
    private RecyclerView rvTimeline;// RecyclerView控件
    private List<DateContent> dateList=new ArrayList<>();
    private List<Bitmap> bitTest;
    private List<String> urls;
    private List<cn.shesshan.myapp.Entity.Message> messages;
    private View grayLayout;

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
            //initData();
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_showinfo,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvTimeline=getView().findViewById(R.id.rvTimeline);
        grayLayout=getView().findViewById(R.id.gray_layout);

        // 获取数据
        DataHelper dataHelper=new DataHelper();
        dateList=dataHelper.getDateList();
        messages=dataHelper.getMessages();

        // 设置布局管理器：线性展示item，默认方向vertical
        rvTimeline.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置适配器Adapter：渲染数据
        rvTimeline.setAdapter(new TimelineAdapter(getContext(),dateList,grayLayout,0));

        // 网络请求子线程
        //urls=new ArrayList<>();
        //urls.add("https://shesshan.cn/img/swufe_info.png");
        //new LoadNetworkDataThread(handler,urls).start();
    }
}
