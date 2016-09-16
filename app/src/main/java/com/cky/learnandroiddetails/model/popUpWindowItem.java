package com.cky.learnandroiddetails.model;

import java.io.Serializable;

/**
 * Created by cuikangyuan on 16/9/14.
 */
public class PopUpWindowItem implements Serializable {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
