package cn.shesshan.myapp.Pager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class ChildPager extends ViewPager {

    private static final String TAG ="ChildPager";
    public ChildPager(Context context) {
        super(context);
    }

    public ChildPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @NonNull
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int curPosition;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                curPosition = this.getCurrentItem();
                int count = this.getAdapter().getCount();
                Log.i(TAG, "curPosition:=" +curPosition);
                // 当当前页面在最后一页和第0页的时候，由父亲拦截触摸事件
                if (curPosition == count - 1|| curPosition==0) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {//其他情况，由孩子拦截触摸事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

        }
        return super.dispatchTouchEvent(ev);
    }
}
