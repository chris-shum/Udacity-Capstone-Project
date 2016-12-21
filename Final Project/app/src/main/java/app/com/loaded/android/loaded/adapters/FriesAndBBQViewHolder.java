package app.com.loaded.android.loaded.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import app.com.loaded.android.loaded.R;

/**
 * Created by ShowMe on 12/20/16.
 */

public class FriesAndBBQViewHolder extends RecyclerView.ViewHolder {

    public CheckBox mNameCheckBox;

    public FriesAndBBQViewHolder(View itemView) {
        super(itemView);
        mNameCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_toppings);
    }

}
