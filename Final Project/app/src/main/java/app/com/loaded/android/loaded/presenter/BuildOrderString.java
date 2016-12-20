package app.com.loaded.android.loaded.presenter;

import android.widget.TextView;

import app.com.loaded.android.loaded.data.Singleton;

/**
 * Created by ShowMe on 12/20/16.
 */

public class BuildOrderString {

    public static String buildBurgerOrder(TextView mTotalTextView) {
        Singleton mSingleton = Singleton.getInstance();

            String[] price = mTotalTextView.getText().toString().split("\\$");
            mSingleton.setPrice(price[1]);
            String[] cheese = mSingleton.getCheese().split("\\+");
            if (cheese[0].contains("cheese")) {
                cheese[0] = "no ";
            }
            String toppings;
            if (mSingleton.getToppings().size() == 0) {
                toppings = "no toppings";
            } else {
                toppings = mSingleton.getToppings().toString();
            }
            String[] fries = mSingleton.getFries().split(" \\+");
            String outputMessage = mSingleton.getMeat() + " burger with " + cheese[0].toLowerCase()
                    + "cheese and " + toppings + ". \n" + fries[0] + ".";
            return outputMessage;
    }
}
