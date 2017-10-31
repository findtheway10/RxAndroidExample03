package com.daekyung.rxandroidexample03.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.daekyung.rxandroidexample03.R;
import com.daekyung.rxandroidexample03.adapter.RecyclerViewAdapter;
import com.daekyung.rxandroidexample03.databinding.ActivityMainBinding;
import com.daekyung.rxandroidexample03.model.RecyclerItem;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        activityMainBinding.recyclerView.setLayoutManager(layoutManager);

        mRecyclerViewAdapter = new RecyclerViewAdapter();
        activityMainBinding.recyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.getItemPublishSubject().subscribe(s -> toast(s.getTitle()));
    }


    @Override
    public void onStart() {
        super.onStart();

        if (mRecyclerViewAdapter == null) {
            return;
        }

        getItemObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    mRecyclerViewAdapter.updateItem(item);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                });
    }

    private Observable<RecyclerItem> getItemObservable() {

        final PackageManager pm = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        return Observable.fromIterable(pm.queryIntentActivities(i, 0))
                .sorted(new ResolveInfo.DisplayNameComparator(pm))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(item -> {
                    Drawable image = item.activityInfo.loadIcon(pm);
                    String title = item.activityInfo.loadLabel(pm).toString();
                    return new RecyclerItem(image, title);
                });
    }


    private void toast(String title) {
        Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
    }
}
