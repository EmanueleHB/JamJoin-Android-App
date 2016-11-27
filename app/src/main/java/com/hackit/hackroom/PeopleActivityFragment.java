package com.hackit.hackroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackit.hackroom.people.FetchPeopleMock;
import com.hackit.hackroom.people.PersonListView;
import com.hackit.hackroom.people.PersonListViewModel;
import com.hackit.hackroom.person.ProfileActivity_;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class PeopleActivityFragment extends Fragment {

    @BindView(R.id.person_list)
    PersonListView personListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public PeopleActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.bind(this, v);

        PersonListViewModel homeworkListViewModel = new PersonListViewModel(new FetchPeopleMock(), 10);

        toolbar.inflateMenu(R.menu.menu_people);
        toolbar.setTitle("JamJoin");
        SearchView searchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();
        RxSearchView.queryTextChanges(searchView).subscribe(charSequence -> {
            homeworkListViewModel.filter(charSequence.toString());
        });

        homeworkListViewModel.observeItemCLick().subscribe(person -> {
            ProfileActivity_.intent(this).name(person.getName()).url(person.getImage()).start();
        });
        personListView.setViewModel(homeworkListViewModel);
        return v;
    }



}
