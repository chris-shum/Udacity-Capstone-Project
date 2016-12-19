package app.com.loaded.android.loaded.presenter;

import java.text.NumberFormat;

/**
 * Created by ShowMe on 12/18/16.
 */

public class FormatCurrency {
    public static String formatCurrency(double number) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(number);
    }
}
