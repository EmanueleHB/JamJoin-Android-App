package com.hackit.hackroom.people;

import com.hackit.hackroom.Person;

import java.util.List;

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

    public String getInterests(){
        String tagsString = "";
        List<String> tags =person.getTags();
        tagsString = tagsString.concat(tags.get(0));
        tagsString = tagsString.concat(", " + tags.get(1));
        tagsString = tagsString.concat("," + tags.get(2));
        return tagsString;
    }
}
