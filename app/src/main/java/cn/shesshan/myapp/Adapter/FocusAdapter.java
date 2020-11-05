package cn.shesshan.myapp.Adapter;

import android.content.Context;
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
import cn.shesshan.myapp.R;

public class FocusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG="FocusAdapter";
    private Context context;
    private List<Entry> entryList;
    private boolean isFocusSelected=true;

    public FocusAdapter(Context context,List<Entry> entryList){
        this.context=context;
        this.entryList=entryList;
    }
    @Override
    @NonNull
    public FocusAdapter.FocusHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        return new FocusHolder(LayoutInflater.from(context).inflate(R.layout.info_focus,
                parent,
                false));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position){
        final FocusHolder focusHolder=(FocusHolder)holder;
        Entry entry=entryList.get(position);
        Glide.with(context).load(entry.getLogoURI()).into(focusHolder.getIvPublisherLogo());
        focusHolder.getTvPublisherName().setText(entry.getPublisher());
        focusHolder.getIvFocusSelected().setBackgroundResource(R.mipmap.focus_selected);
        // 点击关注图标事件处理
        focusHolder.getIvFocusSelected().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFocusSelected)
                    focusHolder.getIvFocusSelected().setBackgroundResource(R.mipmap.focus);
                else
                    focusHolder.getIvFocusSelected().setBackgroundResource(R.mipmap.focus_selected);
                isFocusSelected=(!isFocusSelected);
            }
        });
    }
    @Override
    public int getItemCount(){
        return entryList.size();
    }
    public class FocusHolder extends RecyclerView.ViewHolder{
        private ImageView ivPublisherLogo,ivFocusSelected;
        private TextView tvPublisherName;
        public FocusHolder(View itemView){
            super(itemView);
            ivPublisherLogo=itemView.findViewById(R.id.ivPublisherLogo);
            ivFocusSelected=itemView.findViewById(R.id.ivFocusSelected);
            tvPublisherName=itemView.findViewById(R.id.tvPublisherName);
        }

        public ImageView getIvPublisherLogo() {
            return ivPublisherLogo;
        }

        public void setIvPublisherLogo(ImageView ivPublisherLogo) {
            this.ivPublisherLogo = ivPublisherLogo;
        }

        public ImageView getIvFocusSelected() {
            return ivFocusSelected;
        }

        public void setIvFocusSelected(ImageView ivFocusSelected) {
            this.ivFocusSelected = ivFocusSelected;
        }

        public TextView getTvPublisherName() {
            return tvPublisherName;
        }

        public void setTvPublisherName(TextView tvPublisherName) {
            this.tvPublisherName = tvPublisherName;
        }
    }
}
