package cn.shesshan.myapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.OnItemClickListener;
import cn.shesshan.myapp.R;

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG="TimelineAdapter";
    private Context context;
    private List<DateContent> dateList;
    private View grayLayout;
    private boolean isPopWindowShowing=false;
    private boolean isLikeSelected=false;

    private static final int TYPE_FIRST = 0x0000;
    private static final int TYPE_GENERAL= 0x0001;
    int count=0;

    public TimelineAdapter(Context context, List<DateContent> list,View grayLayout) {
        this.context=context;
        this.dateList=list;
        this.grayLayout=grayLayout;
    }

    // each item inflate a View(布局填充)，并封装到Holder
    @Override
    @NonNull
    public DateContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.date_items, parent,false);
        Log.i(TAG,"Item "+(++count)+" created.");
        return new DateContentHolder(view);
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
        final DateContent dateContent=dateList.get(position);
        // 日期
        dateHolder.tvDate.setText(dateContent.getDate());
        // 当日所有信息
        EntryAdapter entryAdapter=new EntryAdapter(context,dateContent.getEntryList());
        entryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // 单击事件处理:悬浮窗PopupWindow
                showDetails(context,dateContent.getEntryList().get(position));
            }
        });
        dateHolder.rvInfo.setLayoutManager(new LinearLayoutManager(context));
        dateHolder.rvInfo.setAdapter(entryAdapter);
        dateHolder.rvInfo.setVisibility(View.VISIBLE);
        dateHolder.itemView.setTag(dateList.get(position));
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

    public void showDetails(Context context,Entry entry){
        // 背景变暗
        grayLayout.setVisibility(View.VISIBLE);
        View view = LayoutInflater.from(context).inflate(
                R.layout.card_details, null,false);
        // 设置组件
        onCreatePopupWindow(view,entry);

        // 设置悬浮窗体
        final PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(view,Gravity.CENTER, 0, 0);

        // 点击背景部分，浮窗消失
        grayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPopWindowShowing){
                    popupWindow.getContentView().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            popupWindow.dismiss();
                        }
                    },3000);
                }
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                grayLayout.setVisibility(View.GONE);
                isPopWindowShowing=false;
            }
        });
        isPopWindowShowing=true;
    }

    public void onCreatePopupWindow(View view,Entry entry){
        TextView tvDetails=view.findViewById(R.id.tvDetails);
        tvDetails.setText(entry.getContent());
        final ImageView ivLike=view.findViewById(R.id.ivLike);
        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLikeSelected){
                    ivLike.setBackgroundResource(R.drawable.like);
                    isLikeSelected=false;
                }
                else{
                    ivLike.setBackgroundResource(R.drawable.like_selected);
                    isLikeSelected=true;
                }
            }
        });
    }
}
