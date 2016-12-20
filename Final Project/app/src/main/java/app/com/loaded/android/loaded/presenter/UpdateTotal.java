package app.com.loaded.android.loaded.presenter;

import android.widget.TextView;

/**
 * Created by ShowMe on 12/18/16.
 */

public class UpdateTotal {

    public static void updateBurgerTotal(TextView textView, String cost, boolean add) {
        String[] splitString = textView.getText().toString().split("\\$");
        double originalPrice = Double.valueOf(splitString[1]);
        String[] splitCost = cost.split("\\$");
        double additionalPrice = Double.valueOf(splitCost[1]);
        if(add){
            textView.setText("Total: "+FormatCurrency.formatCurrency(originalPrice+additionalPrice));
        }else{
            textView.setText("Total: "+FormatCurrency.formatCurrency(originalPrice-additionalPrice));
        }
    }
}
