package cn.shesshan.myapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.PublisherAdapter;
import cn.shesshan.myapp.DataHelper;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.Entity.Message;
import cn.shesshan.myapp.R;

public class PublisherActivity extends AppCompatActivity {
    private static final String TAG="PublisherActivity";
    private RecyclerView rvPublishedInfo;
    private ImageView ivPublisherLogo,ivFocus;
    private TextView tvPublisherName;
    private List<Entry> entryList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"here 33");
        setContentView(R.layout.activity_publisher);
        Log.i(TAG,"here 35");
        initView();
        Log.i(TAG,"here 37");
        initData();
    }
    public void initView(){
        rvPublishedInfo=findViewById(R.id.rvPublishedInfo);
        ivPublisherLogo=findViewById(R.id.ivPublisherLogo);
        ivFocus=findViewById(R.id.ivFocus);
        tvPublisherName=findViewById(R.id.tvPublisherName);
    }
    public void initData(){
        Log.i(TAG,"here 45");
        DataHelper dataHelper=new DataHelper();
        Log.i(TAG,"here 46");
        entryList=dataHelper.findAllInfo("经济信息工程学院");
        Log.i(TAG,"Entry Counts: "+entryList.size());
        rvPublishedInfo.setLayoutManager(new LinearLayoutManager(this));
        rvPublishedInfo.setAdapter(new PublisherAdapter(this,entryList));
    }
}
