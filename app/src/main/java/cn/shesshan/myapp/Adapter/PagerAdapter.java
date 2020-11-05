package cn.shesshan.myapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import cn.shesshan.myapp.Fragment.SquareFragment;
import cn.shesshan.myapp.Fragment.MeFragment;
import cn.shesshan.myapp.Fragment.ShowInfoFragment;

public class PagerAdapter extends FragmentStateAdapter {

    private int pageNum;
    private FragmentActivity fragmentActivity;
    private ViewPager2 mainViewPager;
    private List<String> urls;

    public PagerAdapter(FragmentActivity fragmentActivity, int pageNum, ViewPager2 viewPager2){
        super(fragmentActivity);
        this.fragmentActivity=fragmentActivity;
        this.pageNum=pageNum;
        this.mainViewPager=viewPager2;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment=new ShowInfoFragment();
                break;
            case 1:
                fragment=new SquareFragment();
                break;
            case 2:
                // 将父ViewPager2传给子ViewPager2
                fragment=new MeFragment(fragmentActivity,mainViewPager);
                break;
            default:
                fragment=new Fragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount(){
        return pageNum;
    }

}
