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
import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.DataHelper;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;

/**
 * 和主界面Fragment1一样:时间选择器+时间轴
 */
public class ArrangeFragment extends Fragment {
    private static final String TAG="ArrangeActivity";
    private RecyclerView rvBasic;// RecyclerView控件
    private List<DateContent> dateList=new ArrayList<>();
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
        DataHelper dataHelper=new DataHelper();
        dateList=dataHelper.getDateList();
        // 设置布局管理器：线性展示item，默认方向vertical
        rvBasic.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置适配器Adapter：渲染数据
        rvBasic.setAdapter(new ShowItemsAdapter(getContext(),dateList,2));
    }
}
