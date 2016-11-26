package com.hackit.hackroom.people;

import com.hackit.hackroom.Person;

import rx.Observable;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public interface FetchPeople {
     Observable<Person> fetchPeople(int number);
}
