package com.zhl.rx.bean;

import java.util.List;

/**
 * 描述：
 * Created by zhaohl on 2018-1-5.
 */

public class Person {
    private String name;
    private int age;
    private Gender gender;
    private int tall;
    private List<Car> cars;
    public Person(){

    }
    public Person(String name,int age){
        this.name = name;
        this.age = age;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
