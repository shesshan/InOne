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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private ImageView ivArrange;
    private TextView tvMain,tvMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        pagerAdapter = new PagerAdapter(this, 3);
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
    public void initView() {
        viewPager2 = findViewById(R.id.vpContent);
        ivArrange = findViewById(R.id.ivArrange);
        tvMain = findViewById(R.id.tvMain);
        tvMe = findViewById(R.id.tvMe);
    }

    /**
     * 恢复底部组件原貌
     */
    public void resumeClick() {
        tvMain.getPaint().setFakeBoldText(false);
        tvMain.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFont));
        tvMe.getPaint().setFakeBoldText(false);
        tvMe.setTextColor(ContextCompat.getColor(this,R.color.bottomTabFont));
        ivArrange.setBackgroundResource(R.drawable.find);
    }

    /**
     * 点击底部组件事件处理
     */
    @Override
    public void onClick(View view) {
        resumeClick();
        Log.i(TAG, "last page: " + viewPager2.getCurrentItem());
        switch (view.getId()) {
            case R.id.tvMain:
                tvMain.getPaint().setFakeBoldText(true);// 字体加粗
                tvMain.setTextColor(ContextCompat.getColor(this, R.color.bottomTabFontSelected)); // 字体颜色变深
                viewPager2.setCurrentItem(0, false);
                break;
            case R.id.tvMe:
                tvMe.getPaint().setFakeBoldText(true);// 字体加粗
                tvMe.setTextColor(ContextCompat.getColor(this, R.color.bottomTabFontSelected)); // 字体颜色变深
                viewPager2.setCurrentItem(2, false);
                break;
            case R.id.ivArrange:
                ivArrange.setBackgroundResource(R.drawable.find_fill);// 图标改变
                viewPager2.setCurrentItem(1, false);
                break;
        }
    }

    /**
     * 页面滑动事件处理
     */
    public void onPageChanged(int position) {
        resumeClick();
        switch (position) {
            case 0:
                tvMain.getPaint().setFakeBoldText(true);// 字体加粗
                tvMain.setTextColor(ContextCompat.getColor(this, R.color.bottomTabFontSelected)); // 字体颜色变深
                break;
            case 1:
                ivArrange.setBackgroundResource(R.drawable.find_fill);// 图标改变
                break;
            case 2:
                tvMe.getPaint().setFakeBoldText(true);// 字体加粗
                tvMe.setTextColor(ContextCompat.getColor(this, R.color.bottomTabFontSelected)); // 字体颜色变深
                break;
        }
    }
}