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

import com.bumptech.glide.Glide;

import java.util.List;

import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.OnItemClickListener;
import cn.shesshan.myapp.R;

public class EntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG="EntryAdapter";
    private Context context;
    private List<Entry> entryList;
    private OnItemClickListener onItemClickListener; // 声明回调接口
    private int flag; // 0：主页 1：兴趣 2：日程
    private int count=0;
    private boolean isLikeSelected=true;

    public EntryAdapter(Context context,List<Entry> entryList,int flag){
        this.context=context;
        this.entryList=entryList;
        this.flag=flag;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    @NonNull
    public EntryHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view;
        Log.i(TAG,"Flag ===> "+flag);
        switch(flag){
            case 0:
                view=LayoutInflater.from(context).inflate(R.layout.info_item, parent,false);
                if(onItemClickListener!=null){
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v,((RecyclerView)parent).getChildAdapterPosition(v));
                        }
                    });
                }
                break;
            case 1:
                Log.i(TAG,"Create View ===> \"info_like\"");
                view=LayoutInflater.from(context).inflate(R.layout.info_like, parent,false);
                break;
            case 2: default:
                view=LayoutInflater.from(context).inflate(R.layout.info_arrange, parent,false);
                break;
        }
        return new EntryHolder(view,flag);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position){
        final EntryHolder entryHolder=(EntryHolder)holder;
        Entry entry=entryList.get(position);
        // 设置发布者名称
        entryHolder.getTvPublisherName().setText(entry.getPublisher());
        // 设置发布内容
        entryHolder.getTvDetails().setText(entry.getContent());
        switch(flag){
            case 0:
                // 设置发布者logo(Bitmap)
                Glide.with(context).load(entry.getLogoURI()).into(entryHolder.getIvPublisherLogo());
                break;
            case 1:
                Log.i(TAG,"Bind data for ===> like");
                // 设置发布者logo
                Glide.with(context).load(entry.getLogoURI()).into(entryHolder.getIvPublisherLogo());
                // 设置小心心
                // entryHolder.getIvLikeSelected().setBackgroundResource(R.drawable.like_selected);
                // 点击喜欢图标事件处理
                entryHolder.getIvLikeSelected().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isLikeSelected)
                            entryHolder.getIvLikeSelected().setBackgroundResource(R.drawable.like);
                        else
                            entryHolder.getIvLikeSelected().setBackgroundResource(R.drawable.like_selected);
                        isLikeSelected=(!isLikeSelected);
                    }
                });
                break;
            case 2:
                // 设置发布者logo
                Glide.with(context).load(entry.getLogoURI()).into(entryHolder.getIvPublisherLogo());
                // 设置删除图标
                entryHolder.getIvCancel().setBackgroundResource(R.drawable.cancel);
                entryHolder.getIvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeData(position);
                    }
                });
        }
    }
    @Override
    public int getItemCount(){
        return entryList.size();
    }

    //  删除数据
    public void removeData(int position) {
        entryList.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class EntryHolder extends RecyclerView.ViewHolder{
        private ImageView ivPublisherLogo;
        private TextView tvPublisherName,tvDetails;
        private ImageView ivLikeSelected,ivCancel;
        //private View reuseLayout=LayoutInflater.from(context).inflate(R.layout.info_item,null,false);

        public EntryHolder(View itemView,int flag){
            super(itemView);
            ivPublisherLogo=itemView.findViewById(R.id.ivPublisherLogo);
            tvPublisherName=itemView.findViewById(R.id.tvPublisherName);
            tvDetails=itemView.findViewById(R.id.tvDetails);
            switch(flag){
                case 0:
                    // 主页
                    break;
                case 1:
                    Log.i(TAG,"Create holder for ===> like");
                    // 兴趣
                    //ivPublisherLogo=reuseLayout.findViewById(R.id.ivPublisherLogo);
                    //tvPublisherName=reuseLayout.findViewById(R.id.tvPublisherName);
                    //tvDetails=reuseLayout.findViewById(R.id.tvDetails);
                    ivLikeSelected=itemView.findViewById(R.id.ivLikeSelected);
                    break;
                case 2:
                    // 日程
                    //ivPublisherLogo=reuseLayout.findViewById(R.id.ivPublisherLogo);
                    //tvPublisherName=reuseLayout.findViewById(R.id.tvPublisherName);
                    //tvDetails=reuseLayout.findViewById(R.id.tvDetails);
                    ivCancel=itemView.findViewById(R.id.ivCancel);
                    break;
            }
        }

        public ImageView getIvPublisherLogo() {
            return ivPublisherLogo;
        }

        public void setIvPublisherLogo(ImageView ivPublisherLogo) {
            this.ivPublisherLogo = ivPublisherLogo;
        }

        public TextView getTvPublisherName() {
            return tvPublisherName;
        }

        public void setTvPublisherName(TextView tvPublisherName) {
            this.tvPublisherName = tvPublisherName;
        }

        public TextView getTvDetails() {
            return tvDetails;
        }

        public void setTvDetails(TextView tvDetails) {
            this.tvDetails = tvDetails;
        }

        public ImageView getIvLikeSelected() {
            return ivLikeSelected;
        }

        public void setIvLikeSelected(ImageView ivLikeSelected) {
            this.ivLikeSelected = ivLikeSelected;
        }

        public ImageView getIvCancel() {
            return ivCancel;
        }

        public void setIvCancel(ImageView ivCancel) {
            this.ivCancel = ivCancel;
        }

    }
}
