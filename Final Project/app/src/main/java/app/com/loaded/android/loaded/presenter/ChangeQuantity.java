package app.com.loaded.android.loaded.presenter;

import android.widget.TextView;

import app.com.loaded.android.loaded.data.Singleton;

/**
 * Created by ShowMe on 12/22/16.
 */

public class ChangeQuantity {

    public static void addQuantity(TextView itemTextView, TextView quantityTextView, TextView totalTextView) {
        Singleton mSingleton = Singleton.getInstance();
        int quantity = Integer.valueOf(quantityTextView.getText().toString());
        int newQuantity = 0;
        if (quantity < 5) {
            newQuantity = quantity + 1;
        } else {
            newQuantity = quantity;
        }
        quantityTextView.setText(String.valueOf(newQuantity));
        UpdateTotal.updateFriesAndBBQTotal(itemTextView, totalTextView, quantity, newQuantity);
        if (newQuantity == 0) {
            mSingleton.getFriesAndBBQMap().remove(itemTextView.getText().toString());
        } else {
            mSingleton.getFriesAndBBQMap().put(itemTextView.getText().toString(), String.valueOf(newQuantity));
        }
    }

    public static void subtractQuantity(TextView itemTextView, TextView quantityTextView, TextView totalTextView) {
        Singleton mSingleton = Singleton.getInstance();
        int quantity = Integer.valueOf(quantityTextView.getText().toString());
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity - 1;
        } else {
            newQuantity = quantity;
        }
        quantityTextView.setText(String.valueOf(newQuantity));
        UpdateTotal.updateFriesAndBBQTotal(itemTextView, totalTextView, quantity, newQuantity);
        if (newQuantity == 0) {
            mSingleton.getFriesAndBBQMap().remove(itemTextView.getText().toString());
        } else {
            mSingleton.getFriesAndBBQMap().put(itemTextView.getText().toString(), String.valueOf(newQuantity));
        }
    }
}