package com.hackit.hackroom.recyclerViewAdapter;

import rx.Observable;

/**
 * Created by emanueledivizio on 28/08/16.
 */
public abstract class ListViewModel<T> {

    public abstract Observable<T> getItems();

}
