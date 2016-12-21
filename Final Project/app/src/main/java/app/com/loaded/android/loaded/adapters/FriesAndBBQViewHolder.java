package app.com.loaded.android.loaded.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.com.loaded.android.loaded.R;

/**
 * Created by ShowMe on 12/20/16.
 */

public class FriesAndBBQViewHolder extends RecyclerView.ViewHolder {

    public TextView friesAndBBQTextView;

    public FriesAndBBQViewHolder(View itemView) {
        super(itemView);
        friesAndBBQTextView = (TextView) itemView.findViewById(R.id.textview_friesAndBBQ);
    }

}
