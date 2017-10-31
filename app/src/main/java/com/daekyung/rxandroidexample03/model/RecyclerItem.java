package com.daekyung.rxandroidexample03.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

/**
 * Created by computer on 2017. 10. 31..
 */

public class RecyclerItem extends BaseObservable{

    private String title;
    private Drawable image;

    public RecyclerItem(Drawable image, String title) {
        this.title = title;
        this.image = image;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Bindable
    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
