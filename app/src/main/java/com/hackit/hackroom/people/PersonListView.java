package com.hackit.hackroom.people;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.hackit.hackroom.ItemClickSupport;
import com.hackit.hackroom.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by emanueledivizio on 24/08/16.
 */
public class PersonListView extends RelativeLayout {
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    private PersonListAdapter adapter;

    public PersonListView(Context context) {
        this(context, null);
    }

    public PersonListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PersonListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        View v = inflate(getContext(), R.layout.person_list_view_layout, this);
        ButterKnife.bind(this, v);

    }

    public void setViewModel(PersonListViewModel homeworkListViewModel){
        adapter = new PersonListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            homeworkListViewModel.click(adapter.getItem(position));
        });
        homeworkListViewModel.fetchPeople().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(person -> {
            adapter.addItem(person);
        }, Throwable::printStackTrace, () -> {});
    }
}
