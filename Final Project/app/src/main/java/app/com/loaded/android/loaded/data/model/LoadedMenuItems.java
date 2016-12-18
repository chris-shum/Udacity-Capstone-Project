package app.com.loaded.android.loaded.data.model;

/**
 * Created by ShowMe on 12/17/16.
 */

public class LoadedMenuItems extends Object {
    String name;
    int price;
    boolean available;

    public LoadedMenuItems() {
    }

    public LoadedMenuItems(String name, int price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
