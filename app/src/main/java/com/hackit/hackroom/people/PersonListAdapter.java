package com.hackit.hackroom.people;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hackit.hackroom.Person;
import com.hackit.hackroom.R;
import com.hackit.hackroom.recyclerViewAdapter.ListRecyclerViewAdapter;

/**
 * Created by emanueledivizio on 25/08/16.
 */
public class PersonListAdapter extends ListRecyclerViewAdapter<Person, PersonListViewHolder> {

    public PersonListAdapter(Context mContext) {
        super(mContext);
    }


    @Override
    public PersonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflateView(R.layout.person_view_holder, parent);
        return new PersonListViewHolder(v);
    }
}
