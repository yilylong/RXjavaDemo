package com.zhl.rx.bean;

/**
 * 描述：
 * Created by zhaohl on 2018-1-5.
 */

public class Car {
    private String name;
    private String model;
    private String factory;
    private float price;

    public Car(){

    }
    public Car(String name,String model){
        this.name = name;
        this.model = model;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
