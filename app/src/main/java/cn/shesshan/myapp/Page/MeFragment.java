package cn.shesshan.myapp.Page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.shesshan.myapp.Adapter.MePagerAdapter;
import cn.shesshan.myapp.MainActivity;
import cn.shesshan.myapp.R;

public class MeFragment extends Fragment {

    private static final String TAG="MeFragment";

    private FragmentActivity fragmentActivity;
    private TabLayout topTabBar;
    private ViewPager2 mePager;
    private NestedScrollView nestedScrollView;
    private ViewPager2 mainViewPager;


    public MeFragment(FragmentActivity fragmentActivity,ViewPager2 mainViewPager){
        this.fragmentActivity=fragmentActivity;
        // 父ViewPager2
        this.mainViewPager=mainViewPager;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        // 解决nestedScrollView,viewpager2滑动冲突
        nestedScrollView.setFillViewport(true);
        mePager.setAdapter(new MePagerAdapter(getActivity(),3));

        // ViewPager2&TabLayout梦幻联动
        new TabLayoutMediator(
                topTabBar,
                mePager,
                true,
                new TabLayoutMediator.TabConfigurationStrategy(){
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(setTabText(position));
                    }
                }).attach();

        // 解决嵌套viewpager2滑动冲突
        mePager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private int curPos=0; // 当前滑动位置
            private int oldPos=0; // 上一个滑动位置
            private int curState=0; // 当前手指按下状态
            private List<Integer> scrolledPixeledList=new ArrayList<>();// 手指滑动时的像素坐标
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.i("Scroll",position+"");
                curPos = position;
                if (curState == 1)
                    // 手指按下滑动坐标记录
                    scrolledPixeledList.add(positionOffsetPixels);

            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.i("Selected",position+"");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                curState = state;
                if (state == 0) {
                    if (curPos == oldPos) {
                        if(curPos==0) {
                            if (scrolledPixeledList.size() > 1
                                        && scrolledPixeledList.get(scrolledPixeledList.size()-1) == 0
                                        || (scrolledPixeledList.get(scrolledPixeledList.size()-1) - scrolledPixeledList.get(0)) > 0) {
                                    // 有可能出现滑到一半放弃的情况也是可以出现curPos == oldPos == 0，则先判断是否是在往右滑时放弃
                                    return;
                            }
                            Log.d("TAG", "To the Left.");
                            // 父viewpager当前不是第一个页面则可切换
                            int loc=mainViewPager.getCurrentItem();
                            if(loc>0) mainViewPager.setCurrentItem(loc-1);
                        }
                        else if(curPos==(mePager.getAdapter().getItemCount()-1)){
                            Log.d("TAG", "To the Right.");
                            // 父viewpager当前不是最后一个页面则可切换
                            int loc=mainViewPager.getCurrentItem();
                            if(loc<(mainViewPager.getAdapter().getItemCount()-1))
                                mainViewPager.setCurrentItem(loc+1);
                        }
                    }
                    oldPos = curPos;
                    scrolledPixeledList.clear();// 清空滑动记录
                }
            }
        });
    }

    public void initView(){
        nestedScrollView = getView().findViewById(R.id.nsv);
        topTabBar=getView().findViewById(R.id.topTabBar);
        mePager=getView().findViewById(R.id.mePager);
    }
    public String setTabText(int position){
        String text;
        switch(position){
            case 0:
                text="兴趣";
                break;
            case 1:
                text="日程";
                break;
            case 2:
                text= "关注";
                break;
            default:
                return "集时";
        }
        return text;
    }
}
