package com.hackit.hackroom;

/**
 * Created by emanueledivizio on 26/11/16.
 */
public class Person {
    private String name;
    private String image;

    public Person(String name, String image) {
        this.name = name;
        this.image = image;
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
}
