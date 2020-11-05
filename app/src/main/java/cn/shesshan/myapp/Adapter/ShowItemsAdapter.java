package cn.shesshan.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.R;

// 嵌套recyclerview
public class ShowItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG="LikeAdapter";
    private Context context;
    private List<DateContent> dateList;
    private int flag;

    public ShowItemsAdapter(Context context, List<DateContent> list,int flag){
        this.context=context;
        this.dateList=list;
        this.flag=flag;
    }

    // each item inflate a View(布局填充)，并封装到Holder
    @Override
    @NonNull
    public ShowItemsAdapter.DateContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_show, parent,false);
        return new ShowItemsAdapter.DateContentHolder(view);
    }
    // 渲染数据到View
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShowItemsAdapter.DateContentHolder dateHolder = (ShowItemsAdapter.DateContentHolder) holder;
        DateContent dateContent=dateList.get(position);
        // 1.设置时间
        dateHolder.getTvDate().setText(dateContent.getDate());
        // 2.设置每日包含信息部分
        EntryAdapter entryAdapter=new EntryAdapter(context,dateContent.getEntryList(),flag);
        dateHolder.getRvShowItems().setLayoutManager(new LinearLayoutManager(context));
        dateHolder.getRvShowItems().setAdapter(entryAdapter);
    }

    // 这个是必须实现的abstract方法哦...
    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class DateContentHolder extends RecyclerView.ViewHolder{
        private TextView tvDate;
        private RecyclerView rvShowItems;
        public DateContentHolder(View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tvDate);
            rvShowItems=itemView.findViewById(R.id.rvShowItems);
        }

        public TextView getTvDate() {
            return tvDate;
        }

        public void setTvDate(TextView tvDate) {
            this.tvDate = tvDate;
        }

        public RecyclerView getRvShowItems() {
            return rvShowItems;
        }

        public void setRvShowItems(RecyclerView rvShowItems) {
            this.rvShowItems = rvShowItems;
        }
    }
}
