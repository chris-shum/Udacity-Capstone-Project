package app.com.loaded.android.loaded.data.model;

/**
 * Created by ShowMe on 12/13/16.
 */

public class LoadedMenuItem {
    String name;
    String price;
    String description;
    String image;
    String extras;

    public LoadedMenuItem(){

    }

    public LoadedMenuItem(String name, String price, String description, String image, String extras) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.extras = extras;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
