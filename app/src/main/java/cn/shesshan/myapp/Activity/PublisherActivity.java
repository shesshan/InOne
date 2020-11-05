package cn.shesshan.myapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
    private Toolbar generalBar;
    private List<Entry> entryList=new ArrayList<>();
    boolean isFocusSelected=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher);
        Intent intent=getIntent();
        String publisherName=intent.getStringExtra("publisherName");
        initView();
        initData(publisherName);
    }
    public void initView(){
        generalBar=findViewById(R.id.generalBar);
        generalBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvPublishedInfo=findViewById(R.id.rvPublishedInfo);
        ivPublisherLogo=findViewById(R.id.ivPublisherLogo);
        ivFocus=findViewById(R.id.ivFocus);
        tvPublisherName=findViewById(R.id.tvPublisherName);
    }
    public void initData(String publisherName){
        tvPublisherName.setText(publisherName); // 组织名称
        DataHelper dataHelper=new DataHelper();
        entryList=dataHelper.findAllInfo(publisherName);
        if(!entryList.isEmpty()) {
            Glide.with(this).load(entryList.get(0).getLogoURI()).into(ivPublisherLogo);// 组织logo
            rvPublishedInfo.setLayoutManager(new LinearLayoutManager(this));
            rvPublishedInfo.setAdapter(new PublisherAdapter(this, entryList));
        }
    }
    public void onClick(View focusBtn){
        if(isFocusSelected)
            ivFocus.setBackgroundResource(R.mipmap.focus);
        else
            ivFocus.setBackgroundResource(R.mipmap.focus_selected);
        isFocusSelected=(!isFocusSelected);
    }
}
