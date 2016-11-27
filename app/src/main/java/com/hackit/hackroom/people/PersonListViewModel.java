package com.hackit.hackroom.people;


import android.util.Log;

import com.hackit.hackroom.Person;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by emanueledivizio on 25/08/16.
 */
public class PersonListViewModel {
    private FetchPeople fetchPeople;
    private int number;

    private Subject<Person, Person> personSubject = PublishSubject.create();

    private Subject<CharSequence, CharSequence> filterSubject = BehaviorSubject.create();

    public PersonListViewModel(FetchPeople fetchPeople, int number) {
        this.fetchPeople = fetchPeople;
        this.number = number;
    }

    public Observable<List<Person>> fetchPeople(){
        return filterSubject.startWith("").doOnNext(charSequence -> Log.d("SEARCH", charSequence.toString())).flatMap(cs ->fetchPeople.fetchPeople(number, cs.toString()).subscribeOn(Schedulers.newThread()).toList());
    }


    public Observable<Person> observeItemCLick(){
        return personSubject.asObservable();
    }

    public void click(Person person){
        personSubject.onNext(person);
    }

    public void filter(String string) {
        filterSubject.onNext(string);
    }
}
