package app.com.loaded.android.loaded.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import app.com.loaded.android.loaded.R;

/**
 * Created by ShowMe on 12/17/16.
 */

public class ToppingsViewHolder extends RecyclerView.ViewHolder {

    public CheckBox nameCheckBox;


    public ToppingsViewHolder(View itemView) {
        super(itemView);
        nameCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_toppings);
    }

}
