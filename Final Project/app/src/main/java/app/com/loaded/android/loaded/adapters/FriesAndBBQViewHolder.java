package app.com.loaded.android.loaded.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.com.loaded.android.loaded.R;

/**
 * Created by ShowMe on 12/20/16.
 */

public class FriesAndBBQViewHolder extends RecyclerView.ViewHolder {

    public TextView friesAndBBQTextView, quantityTextView;
    Button addButton, subtractButton;


    public FriesAndBBQViewHolder(View itemView) {
        super(itemView);
        friesAndBBQTextView = (TextView) itemView.findViewById(R.id.textview_friesAndBBQ);
        quantityTextView = (TextView) itemView.findViewById(R.id.textView_quantity);
        addButton = (Button) itemView.findViewById(R.id.button_add);
        subtractButton = (Button) itemView.findViewById(R.id.button_subtract);
    }

}
