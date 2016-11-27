package com.hackit.hackroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hackit.hackroom.people.FetchPeopleHeroku;
import com.hackit.hackroom.people.PersonListViewModel;
import com.hackit.hackroom.person.ProfileActivity_;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity
public class PeopleActivity extends AppCompatActivity {


    @Extra
    String query;
    private PersonListViewModel homeworkListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("JamJoin");
        setSupportActionBar(toolbar);

        homeworkListViewModel = new PersonListViewModel(new FetchPeopleHeroku(getResources().getStringArray(R.array.labels)
        ), 10);

        PeopleActivityFragment fragment = new PeopleActivityFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

        homeworkListViewModel.observeItemCLick().subscribe(person -> {
            ProfileActivity_.intent(this).name(person.getName()).url(person.getImage()).start();
        });

        fragment.setViewModel(homeworkListViewModel);

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(query != null){

            MenuItem searchMenuItem = menu.findItem( R.id.action_search ); // get my MenuItem with placeholder submenu
            searchMenuItem.expandActionView(); // Expand the search menu item in order to show by default the query


            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setQuery(query, true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_people, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        RxSearchView.queryTextChanges(searchView).subscribe(charSequence -> {
            homeworkListViewModel.filter(charSequence.toString());
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
