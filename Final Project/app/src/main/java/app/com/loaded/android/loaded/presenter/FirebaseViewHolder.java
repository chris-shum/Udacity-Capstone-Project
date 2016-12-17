package app.com.loaded.android.loaded.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import app.com.loaded.android.loaded.R;

/**
 * Created by ShowMe on 12/17/16.
 */

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;
    public TextView priceTextView;
    public TextView descriptionTextView;
    public TextView imageTextView;
    public TextView extrasTextView;

    public FirebaseViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.name);
        priceTextView = (TextView) itemView.findViewById(R.id.price);
        descriptionTextView = (TextView) itemView.findViewById(R.id.description);
        imageTextView = (TextView) itemView.findViewById(R.id.image);
        extrasTextView = (TextView) itemView.findViewById(R.id.extras);
    }

}
