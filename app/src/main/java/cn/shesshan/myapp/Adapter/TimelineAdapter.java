package cn.shesshan.myapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<Entry> list;
    private static final int TYPE_FIRST = 0x0000;
    private static final int TYPE_GENERAL= 0x0001;

    public TimelineAdapter(Context context, List<Entry> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.date_items, parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        if (getItemViewType(position) == TYPE_FIRST) {
            // 第一个entry上部竖线不显示
            itemHolder.tvTopLine.setVisibility(View.INVISIBLE);

        } else if (getItemViewType(position) == TYPE_GENERAL) {
            itemHolder.tvTopLine.setVisibility(View.VISIBLE);
        }
        itemHolder.tvDot.setBackgroundResource(R.drawable.timeline_dot);
        itemHolder.bindHolder(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_FIRST;
        }
        return TYPE_GENERAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPublisher, tvContent;
        private TextView tvTopLine, tvDot;
        public ViewHolder(View itemView) {
            super(itemView);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTopLine = itemView.findViewById(R.id.tvTopLine);
            tvDot = itemView.findViewById(R.id.tvDot);
        }
        public void bindHolder(Entry entry) {
            tvPublisher.setText(entry.getPublisher());
            tvContent.setText(entry.getContent());
        }
    }
}
