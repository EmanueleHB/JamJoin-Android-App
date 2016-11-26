package com.hackit.hackroom.people;

import android.view.View;

import com.hackit.hackroom.Person;
import com.hackit.hackroom.R;
import com.hackit.hackroom.recyclerViewAdapter.ListItemViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emanueledivizio on 25/08/16.
 */
public class PersonListViewHolder extends ListItemViewHolder<Person> {
    @BindView(R.id.person_view_holder)
    PersonView personView;


    private PersonVM personVM;

    public PersonListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bindItem(Person person){
        personVM = new PersonVM(person);
        personView.setViewModel(personVM);
    }

}
