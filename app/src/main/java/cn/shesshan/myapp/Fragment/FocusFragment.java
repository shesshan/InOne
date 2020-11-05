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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.shesshan.myapp.Adapter.FocusAdapter;
import cn.shesshan.myapp.DataHelper;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;

/**
 * 展示关注组织(rv：info_focus.xml)
 */
public class FocusFragment extends Fragment {
    private static final String TAG="FocusFragment";
    private RecyclerView rvBasic;// RecyclerView控件
    private List<Entry> entryList;

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
        entryList=new ArrayList<>();
        // 模拟后端返回数据
        DataHelper dataHelper=new DataHelper();
        Map<String,String> map=dataHelper.getPublisherNameMatch();
        for(String s:map.keySet())
            entryList.add(new Entry(s,map.get(s)));
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rvBasic.setLayoutManager(manager);
        rvBasic.setAdapter(new FocusAdapter(getContext(),entryList));
    }

}
