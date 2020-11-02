package cn.shesshan.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.shesshan.myapp.Adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG="MainActivity";
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private ImageView ivArrange;
    private TextView tvMain,tvMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        pagerAdapter=new PagerAdapter(this,3);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                onPageChanged(position);
            }
        });
        ivArrange.setOnClickListener(this);
        tvMain.setOnClickListener(this);
        tvMe.setOnClickListener(this);
    }

    // 初始化组件
    public void initView(){
        viewPager2=findViewById(R.id.vpContent);
        ivArrange=findViewById(R.id.ivArrange);
        tvMain=findViewById(R.id.tvMain);
        tvMe=findViewById(R.id.tvMe);
    }
    // 恢复底部组件原貌
    public void resumeClick(){
        TextPaint tp;
        tp=tvMain.getPaint();
        tp.setFakeBoldText(false);
        tp=tvMe.getPaint();
        tp.setFakeBoldText(false);
        ivArrange.setBackgroundResource(R.drawable.test);
        tvMain.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFont));
        tvMe.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFont));
    }
    // 点击底部组件事件处理
    @Override
    public void onClick(View view){
        resumeClick();
        TextPaint tp;
        switch (view.getId()){
            case R.id.tvMain:
                //tvMain.setTextSize(R.dimen.bottomTab_selected_font_size);// 字体变大
                tp = tvMain.getPaint();
                tp.setFakeBoldText(true);
                tvMain.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFontSelected)); // 字体颜色变深
                Log.i(TAG,"last page: "+viewPager2.getCurrentItem());
                viewPager2.setCurrentItem(0,false);
                break;
            case R.id.tvMe:
                //tvMe.setTextSize(R.dimen.bottomTab_selected_font_size);// 字体变大
                tp = tvMe.getPaint();
                tp.setFakeBoldText(true);// 字体加粗
                tvMe.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFontSelected)); // 字体颜色变深
                Log.i(TAG,"last page: "+viewPager2.getCurrentItem());
                viewPager2.setCurrentItem(2,false);
                break;
            case R.id.ivArrange:
                ivArrange.setBackgroundResource(R.drawable.test_selected);// 图标改变
                Log.i(TAG,"last page: "+viewPager2.getCurrentItem());
                viewPager2.setCurrentItem(1,false);
                break;
        }
    }
    // 页面滑动事件处理
    public void onPageChanged(int position){
        resumeClick();
        TextPaint tp;
        switch (position){
            case 0:
                Log.i("Main: ","Page "+viewPager2.getCurrentItem());
                //tvMain.setTextSize(R.dimen.bottomTab_selected_font_size);// 字体变大
                tp = tvMain.getPaint();
                tp.setFakeBoldText(true);// 字体加粗
                tvMain.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFontSelected)); // 字体颜色变深
                break;
            case 1:
                Log.i("Arrange: ","Page "+viewPager2.getCurrentItem());
                ivArrange.setBackgroundResource(R.drawable.test_selected);// 图标改变
                break;
            case 2:
                Log.i("Me: ","Page "+viewPager2.getCurrentItem());
                //tvMe.setTextSize(R.dimen.bottomTab_selected_font_size);// 字体变大
                tp = tvMe.getPaint();
                tp.setFakeBoldText(true);// 字体加粗
                tvMe.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFontSelected)); // 字体颜色变深
                break;
        }
    }
    public void changeSelectedView(View view){

    }
}