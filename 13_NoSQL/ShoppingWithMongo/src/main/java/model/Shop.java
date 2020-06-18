package model;

import java.util.ArrayList;

public class Shop {

    private String name;

    private ArrayList<Integer> goods;

    public Shop(String name) {
        goods = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getGoods() {
        return goods;
    }
}
