package com.hackit.hackroom.recyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by emanueledivizio on 28/08/16.
 */
public abstract class ListItemViewHolder<T> extends RecyclerView.ViewHolder {
    public ListItemViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindItem(T mark);
}
