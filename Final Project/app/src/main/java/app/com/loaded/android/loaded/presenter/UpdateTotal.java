package app.com.loaded.android.loaded.presenter;

import android.widget.TextView;

/**
 * Created by ShowMe on 12/18/16.
 */

public class UpdateTotal {

    public static void updateBurgerTotal(TextView textView, String cost, boolean topping) {
        String[] splitString = textView.getText().toString().split("\\$");
        double originalPrice = Double.valueOf(splitString[1]);
        String[] splitCost = cost.split("\\$");
        double toppingsPrice = Double.valueOf(splitCost[1]);
        if(topping){
            textView.setText("Total: "+FormatCurrency.formatCurrency(originalPrice+toppingsPrice));
        }else{
            textView.setText("Total: "+FormatCurrency.formatCurrency(originalPrice-toppingsPrice));
        }
    }
}
