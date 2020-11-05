package cn.shesshan.myapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.Entity.Message;
import cn.shesshan.myapp.R;

public class PublisherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG="PublisherAdapter";
    private Context context;
    private List<Entry> entryList;
    public PublisherAdapter(Context context, List<Entry> entryList){
        this.context=context;
        this.entryList=entryList;
    }
    @Override
    @NonNull
    public PublisherAdapter.PublisherHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        return new PublisherHolder(LayoutInflater.from(context).inflate(R.layout.info_published, parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position){
        PublisherHolder publisherHolder=(PublisherHolder)holder;
        Entry entry=entryList.get(position);
        publisherHolder.getTvDate().setText(entry.getMessage().getTime());// 时间
        Log.i(TAG,"Time:"+entry.getMessage().getTime() );
        publisherHolder.getTvDetails().setText("内容："+entry.getContent());// 内容
        Log.i(TAG,"Content:"+entry.getContent() );
        publisherHolder.getTvPlace().setText("地点："+entry.getMessage().getPlace());// 地点
        Log.i(TAG,"Place:"+entry.getMessage().getPlace() );
    }
    @Override
    public int getItemCount(){
        return entryList.size();
    }
    public class PublisherHolder extends RecyclerView.ViewHolder{
        private TextView tvPlace,tvDetails,tvDate;
        public PublisherHolder(View itemView){
            super(itemView);
            tvPlace=itemView.findViewById(R.id.tvPlace);
            tvDetails=itemView.findViewById(R.id.tvDetails);
            tvDate=itemView.findViewById(R.id.tvDate);
        }

        public TextView getTvPlace() {
            return tvPlace;
        }

        public void setTvPlace(TextView tvPlace) {
            this.tvPlace = tvPlace;
        }

        public TextView getTvDetails() {
            return tvDetails;
        }

        public void setTvDetails(TextView tvDetails) {
            this.tvDetails = tvDetails;
        }

        public TextView getTvDate() {
            return tvDate;
        }

        public void setTvDate(TextView tvDate) {
            this.tvDate = tvDate;
        }
    }
}
