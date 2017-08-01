package com.example.android.weeklytips.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Dog extends RealmObject {

    @PrimaryKey
    private int dogId;
    private String name;
    private int age;

    public Dog() {
    }

    public Dog(int dogId, String name, int age) {
        this.dogId = dogId;
        this.name = name;
        this.age = age;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogId=" + dogId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
