package com.daekyung.rxandroidexample03.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.daekyung.rxandroidexample03.databinding.RecyclerViewItemBinding;
import com.daekyung.rxandroidexample03.model.RecyclerItem;
import com.daekyung.rxandroidexample03.viewholder.MyViewHolder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by computer on 2017. 10. 31..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    //item lists
    private List<RecyclerItem> mItems = new ArrayList<>();

    private PublishSubject<RecyclerItem> mPublishSubject;

    public RecyclerViewAdapter() {
        this.mPublishSubject = PublishSubject.create();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        RecyclerViewItemBinding itemBinding =
                RecyclerViewItemBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RecyclerItem item = mItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(List<RecyclerItem> items) {
        mItems.addAll(items);
    }

    public void updateItem(RecyclerItem item) {
        mItems.add(item);
    }

    public PublishSubject<RecyclerItem> getItemPublishSubject() {
        return mPublishSubject;
    }

}
