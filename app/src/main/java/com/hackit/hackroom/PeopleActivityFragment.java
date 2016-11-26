package com.hackit.hackroom;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackit.hackroom.people.FetchPeopleMock;
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

    public PeopleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.bind(this, v);
        personListView.setViewModel(new PersonListViewModel(new FetchPeopleMock(), 10));
        return v;
    }
}
