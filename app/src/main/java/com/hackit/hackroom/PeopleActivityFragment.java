package com.hackit.hackroom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackit.hackroom.people.PersonListView;
import com.hackit.hackroom.people.PersonListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class PeopleActivityFragment extends Fragment {

    @BindView(R.id.person_list)
    PersonListView personListView;
    private PersonListViewModel personListViewModel;

    public PeopleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personListView.setViewModel(personListViewModel);
    }

    public void setViewModel(PersonListViewModel personListViewModel){
        this.personListViewModel = personListViewModel;
    }



}
