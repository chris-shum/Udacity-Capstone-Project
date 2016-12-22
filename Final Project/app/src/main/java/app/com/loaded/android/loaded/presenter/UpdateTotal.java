package app.com.loaded.android.loaded.presenter;

import android.widget.TextView;

/**
 * Created by ShowMe on 12/18/16.
 */

public class UpdateTotal {

    public static void updateBurgerTotal(TextView totalTextView, String itemCost, boolean add) {
        String[] splitString = totalTextView.getText().toString().split("\\$");
        double originalTotal = Double.valueOf(splitString[1]);
        String[] splitCost = itemCost.split("\\$");
        double itemPrice = Double.valueOf(splitCost[1]);
        if(add){
            totalTextView.setText("Total: "+FormatCurrency.formatCurrency(originalTotal+itemPrice));
        }else{
            totalTextView.setText("Total: "+FormatCurrency.formatCurrency(originalTotal-itemPrice));
        }
    }

    public static void updateFriesAndBBQTotal(TextView itemTextView, TextView totalTextView, int originalQuantity, int newQuantity){
        String[] splitString = totalTextView.getText().toString().split("\\$");
        double originalTotal = Double.valueOf(splitString[1]);
        String[] splitCost = itemTextView.getText().toString().split("\\$");
        double itemPrice = Double.valueOf(splitCost[1]);
        totalTextView.setText("Total: "+FormatCurrency.formatCurrency(originalTotal-originalQuantity*itemPrice+newQuantity*itemPrice));
    }
}
