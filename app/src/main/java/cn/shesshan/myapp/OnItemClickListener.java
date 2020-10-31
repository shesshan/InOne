package cn.shesshan.myapp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import cn.shesshan.myapp.Entity.Entry;

public interface OnItemClickListener {
    void onItemClick(View view, int position);
}
