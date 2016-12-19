package app.com.loaded.android.loaded.data;

import java.util.ArrayList;

/**
 * Created by ShowMe on 8/8/16.
 */
public class Singleton {
    String meat;
    String cheese;
    ArrayList<String> toppings;
    String fries;
    String price;

    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public ArrayList<String> getToppings() {
        if (toppings == null) {
            toppings = new ArrayList<>();
        }
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public String getFries() {
        return fries;
    }

    public void setFries(String fries) {
        this.fries = fries;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
