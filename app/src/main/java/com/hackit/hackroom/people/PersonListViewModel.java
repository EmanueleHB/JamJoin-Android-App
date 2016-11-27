package com.hackit.hackroom.people;


import com.hackit.hackroom.Person;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by emanueledivizio on 25/08/16.
 */
public class PersonListViewModel {
    private FetchPeople fetchPeople;
    private int number;

    private Subject<Person, Person> personSubject = PublishSubject.create();

    public PersonListViewModel(FetchPeople fetchPeople, int number) {
        this.fetchPeople = fetchPeople;
        this.number = number;
    }

    public Observable<Person> fetchPeople(){
        return fetchPeople.fetchPeople(number);
    }


    public Observable<Person> observeItemCLick(){
        return personSubject.asObservable();
    }

    public void click(Person person){
        personSubject.onNext(person);
    }

}
