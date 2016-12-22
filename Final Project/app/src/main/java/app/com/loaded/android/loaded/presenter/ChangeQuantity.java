package app.com.loaded.android.loaded.presenter;

import android.widget.TextView;

/**
 * Created by ShowMe on 12/22/16.
 */

public class ChangeQuantity {

    public static void addQuantity(TextView itemTextView, TextView quantityTextView, TextView totalTextView) {
        // TODO: 12/22/16 see if int coming out of editText can be int
        int quantity = Integer.valueOf(quantityTextView.getText().toString());
        int newQuantity = 0;
        if (quantity < 5) {
            newQuantity = quantity + 1;
        } else {
            newQuantity = quantity;
        }
        quantityTextView.setText(String.valueOf(newQuantity));
        UpdateTotal.updateFriesAndBBQTotal(itemTextView, totalTextView, quantity, newQuantity);
    }

    public static void subtractQuantity(TextView itemTextView, TextView quantityTextView, TextView totalTextView) {
        // TODO: 12/22/16 see if int coming out of editText can be int
        int quantity = Integer.valueOf(quantityTextView.getText().toString());
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity - 1;
        } else {
            newQuantity = quantity;
        }
        quantityTextView.setText(String.valueOf(newQuantity));
        UpdateTotal.updateFriesAndBBQTotal(itemTextView, totalTextView, quantity, newQuantity);
    }
}