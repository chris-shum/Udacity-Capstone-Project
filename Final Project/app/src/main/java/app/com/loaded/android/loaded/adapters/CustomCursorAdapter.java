package app.com.loaded.android.loaded.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.local.ShoppingCartTable;

/**
 * Created by ShowMe on 12/20/16.
 */

public class CustomCursorAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.order_row, parent, false);
        return v;
    }


    @Override
    public void bindView(View v, Context context, Cursor c) {
        String description = c.getString(c.getColumnIndexOrThrow(ShoppingCartTable.COLUMN_DESCRIPTION));
        String price = "$"+c.getString(c.getColumnIndexOrThrow(ShoppingCartTable.COLUMN_PRICE));

        TextView title_text = (TextView) v.findViewById(R.id.item_order);
        if (title_text != null) {
            title_text.setText(description);
        }

        TextView date_text = (TextView) v.findViewById(R.id.item_price);
        if (date_text != null) {
            date_text.setText(price);
        }
    }
}