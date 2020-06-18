package model;

import java.util.ArrayList;

public class Shop {

    private String name;

    private ArrayList<Integer> items;

    public Shop(String name) {
        items = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }
}
