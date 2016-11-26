package com.hackit.hackroom.recyclerViewAdapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by emanueledivizio on 28/08/16.
 */
public abstract class ListRecyclerViewAdapter<T, VH extends  ListItemViewHolder<T>> extends RecyclerView.Adapter<VH> {
    protected List<T> items = new ArrayList<>();
    protected Context mContext;

    public ListRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindItem(getItem(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public <VM extends  ListViewModel<T>> void setViewModel(VM viewModel){
        viewModel.getItems().observeOn(AndroidSchedulers.mainThread()).subscribe(this::addItem, Throwable::printStackTrace,  () -> {});
    }


    public T getItem(int position){
        return items.get(position);
    }

    public void addItem(T item){
        items.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<T> itemList){
        items.addAll(itemList);
        notifyDataSetChanged();
    }

    public void setItems(List<T> itmList){
        items.clear();
        items.addAll(itmList);
        notifyDataSetChanged();
    }

    protected View inflateView(@LayoutRes int resource, ViewGroup parent){
        View v = LayoutInflater.from(mContext).inflate(resource, parent, false);
        return v;
    }
}
