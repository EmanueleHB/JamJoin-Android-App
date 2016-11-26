package com.hackit.hackroom.people;


import com.hackit.hackroom.Person;

import rx.Observable;

/**
 * Created by emanueledivizio on 25/08/16.
 */
public class PersonListViewModel {
    private FetchPeople fetchPeople;
    private int number;

    public PersonListViewModel(FetchPeople fetchPeople, int number) {
        this.fetchPeople = fetchPeople;
        this.number = number;
    }

    public Observable<Person> fetchPeople(){
        return fetchPeople.fetchPeople(number);
    }

}
