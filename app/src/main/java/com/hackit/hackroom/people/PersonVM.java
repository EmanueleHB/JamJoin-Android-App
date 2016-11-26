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
        String name = person.getName();
        StringBuilder sb = new StringBuilder(name);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public String getImage(){
        return person.getImage();
    }
}
