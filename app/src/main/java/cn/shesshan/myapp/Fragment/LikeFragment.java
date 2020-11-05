package cn.shesshan.myapp.Fragment;

import android.os.Bundle;
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

import cn.shesshan.myapp.Adapter.ShowItemsAdapter;
import cn.shesshan.myapp.DataHelper;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;

/**
 * 展示感兴趣的活动
 * (嵌套rv:
 *     like_items.xml:date + rv：
 *                         info_item.xml)
 */
public class LikeFragment extends Fragment {
    private static final String TAG="LikeFragment";
    private RecyclerView rvBasic;// RecyclerView控件
    private List<DateContent> dateList=new ArrayList<>();
    private List<String> urls;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.basic_rv,container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvBasic=getView().findViewById(R.id.rvBasic);
        initData();
    }

    public void initData(){
        // 模拟后端返回数据
        DataHelper dataHelper=new DataHelper();
        dateList=dataHelper.getDateList();
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        //manager.setSmoothScrollbarEnabled(true);
        rvBasic.setLayoutManager(manager);
        //rvBasic.setHasFixedSize(true);
        //rvBasic.setNestedScrollingEnabled(false);
        // 设置适配器Adapter：渲染数据
        rvBasic.setAdapter(new ShowItemsAdapter(getContext(),dateList,1));
    }
}
