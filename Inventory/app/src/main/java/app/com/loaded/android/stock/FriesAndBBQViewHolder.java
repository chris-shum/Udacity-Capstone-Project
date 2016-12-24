package app.com.loaded.android.stock;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by ShowMe on 12/20/16.
 */

public class FriesAndBBQViewHolder extends RecyclerView.ViewHolder {

    public TextView friesAndBBQTextView;
    Switch inventorySwitch;


    public FriesAndBBQViewHolder(View itemView) {
        super(itemView);
        friesAndBBQTextView = (TextView) itemView.findViewById(R.id.textView_friesAndBBQ);
        inventorySwitch = (Switch) itemView.findViewById(R.id.switch_inventory);
    }

}
