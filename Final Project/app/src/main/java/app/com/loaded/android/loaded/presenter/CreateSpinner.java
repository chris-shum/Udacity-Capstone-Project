package app.com.loaded.android.loaded.presenter;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by ShowMe on 12/18/16.
 */

public class CreateSpinner {

    public static Spinner createBurgerSpinner(Context context, int resource, List<String> list, View spinner) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, resource, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) spinner;
        sItems.setAdapter(adapter);

        return sItems;
    }

}