package cn.shesshan.myapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import cn.shesshan.myapp.Page.ArrangeFragment;
import cn.shesshan.myapp.Page.MeFragment;
import cn.shesshan.myapp.Page.ShowInfoFragment;

public class PagerAdapter extends FragmentStateAdapter {

    private int pageNum;

    public PagerAdapter(FragmentActivity fragmentActivity, int pageNum){
        super(fragmentActivity);
        this.pageNum=pageNum;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment=new ShowInfoFragment();
                break;
            case 1:
                fragment=new ArrangeFragment();
                break;
            case 2:
                fragment=new MeFragment();
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
