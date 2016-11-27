package com.hackit.hackroom;

import java.util.Arrays;
import java.util.List;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public class Person {
    private String name;
    private  List<String> tags;
    private String image;

    public Person(String name, String image, String[] strings) {
        this.name = name;
        this.image = image;
        this.tags = Arrays.asList(strings);
    }

    public Person(String name, String large, List<String> tags) {
        this.name = name;
        this.image = large;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }
}
