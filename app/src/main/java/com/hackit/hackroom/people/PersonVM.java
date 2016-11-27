package com.hackit.hackroom.people;

import com.hackit.hackroom.Person;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public class PersonVM {
    private Person person;

    public PersonVM(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }

    public String getImage(){
        return person.getImage();
    }
}
