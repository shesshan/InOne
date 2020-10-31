package cn.shesshan.myapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;
import cn.shesshan.myapp.ShowInfoAcitivity;

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG="TimelineAdapter";
    private Context context;
    private List<DateContent> dateList;
    private static final int TYPE_FIRST = 0x0000;
    private static final int TYPE_GENERAL= 0x0001;
    int count=0;

    public TimelineAdapter(Context context, List<DateContent> list) {
        this.context=context;
        //inflater = LayoutInflater.from(context);
        this.dateList = list;
    }
    // each item inflate a View(布局填充)，并封装到Holder
    @Override
    @NonNull
    public DateContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"Item "+(++count)+" created.");
        return new DateContentHolder(LayoutInflater.from(context).inflate(R.layout.date_items, parent,false));
    }
    // 渲染数据到View
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DateContentHolder dateHolder = (DateContentHolder) holder;
        // 1.设置时间轴部分
        // 线
        if (getItemViewType(position) == TYPE_FIRST)
            // 第一个item只显示圆点下方竖线
            dateHolder.tvTopLine.setVisibility(View.INVISIBLE);
        else
            dateHolder.tvTopLine.setVisibility(View.VISIBLE);
        // 点
        dateHolder.tvDot.setBackgroundResource(R.drawable.timeline_dot);
        // 2.设置每日包含信息部分
        DateContent dateContent=dateList.get(position);
        // 日期
        dateHolder.tvDate.setText(dateContent.getDate());
        // 当日所有信息
        EntryAdapter entryAdapter=new EntryAdapter(context,dateContent.getEntryList());
        dateHolder.rvInfo.setLayoutManager(new LinearLayoutManager(context));
        dateHolder.rvInfo.setAdapter(entryAdapter);
        dateHolder.rvInfo.setVisibility(View.VISIBLE);
        Log.i(TAG,"Item "+(position+1)+" bound.");
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_FIRST;
        else
            return TYPE_GENERAL;
    }

    public class DateContentHolder extends RecyclerView.ViewHolder {
        private TextView tvDate,tvTopLine, tvDot;
        private RecyclerView rvInfo;
        public DateContentHolder(View itemView) {
            super(itemView);
            tvTopLine = itemView.findViewById(R.id.tvTopLine);
            tvDot = itemView.findViewById(R.id.tvDot);
            tvDate=itemView.findViewById(R.id.tvDate);
            rvInfo=itemView.findViewById(R.id.rvInfo);
        }
    }
}
