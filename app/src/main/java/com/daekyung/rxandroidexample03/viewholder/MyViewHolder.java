package com.daekyung.rxandroidexample03.viewholder;

import android.support.v7.widget.RecyclerView;

import com.daekyung.rxandroidexample03.databinding.RecyclerViewItemBinding;
import com.daekyung.rxandroidexample03.model.RecyclerItem;

import io.reactivex.Observable;

/**
 * Created by computer on 2017. 10. 31..
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerViewItemBinding binding;


    public MyViewHolder(RecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(RecyclerItem item) {
        binding.setItem(item);
        binding.executePendingBindings();
    }

    Observable<RecyclerItem> getClickObserver(RecyclerItem item) {
        return Observable.create(e -> itemView.setOnClickListener(
                view -> e.onNext(item)
        ));
    }

}
