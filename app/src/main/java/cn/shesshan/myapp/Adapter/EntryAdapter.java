package cn.shesshan.myapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.R;

public class EntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG="EntryAdapter";
    private Context context;
    private List<Entry> entryList;
    private int count=0;

    public EntryAdapter(Context context,List<Entry> entryList){
        this.context=context;
        this.entryList=entryList;
    }
    @Override
    @NonNull
    public EntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"Item "+(++count)+" created.");
        return new EntryHolder(LayoutInflater.from(context).inflate(R.layout.item_info, parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        EntryHolder entryHolder=(EntryHolder)holder;
        // 设置发布者logo
        entryHolder.ivPublisherLogo.setBackgroundResource(R.mipmap.swufe_info_logo);
        Entry entry=entryList.get(position);
        // 设置发布者名称
        entryHolder.tvPublisherName.setText(entry.getPublisher());
        // 设置发布内容
        entryHolder.tvDetails.setText(entry.getContent());
        Log.i(TAG,"Item "+(position+1)+" bound.");
    }
    @Override
    public int getItemCount(){
        return entryList.size();
    }

    public class EntryHolder extends RecyclerView.ViewHolder{
        private ImageView ivPublisherLogo;
        private TextView tvPublisherName,tvDetails;
        public EntryHolder(View itemView){
            super(itemView);
            ivPublisherLogo=itemView.findViewById(R.id.ivPublisherLogo);
            tvPublisherName=itemView.findViewById(R.id.tvPublisherName);
            tvDetails=itemView.findViewById(R.id.tvDetails);
        }
    }
}
