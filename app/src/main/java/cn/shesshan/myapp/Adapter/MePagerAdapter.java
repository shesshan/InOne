package cn.shesshan.myapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import cn.shesshan.myapp.Page.ArrangeFragment;
import cn.shesshan.myapp.Page.FocusFragment;
import cn.shesshan.myapp.Page.LikeFragment;
import cn.shesshan.myapp.Page.MeFragment;
import cn.shesshan.myapp.Page.ShowInfoFragment;
import cn.shesshan.myapp.Page.SquareFragment;

public class MePagerAdapter extends FragmentStateAdapter {
    private int pageNum;

    public MePagerAdapter(FragmentActivity fragmentActivity, int pageNum){
        super(fragmentActivity);
        this.pageNum=pageNum;
    }
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            // 兴趣
            case 0:
                fragment=new LikeFragment();
                break;
            // 日程
            case 1:
                fragment=new ArrangeFragment();
                break;
            // 关注
            case 2:
                fragment=new FocusFragment();
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
