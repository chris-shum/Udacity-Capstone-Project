package app.com.loaded.android.loaded.presenter;

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
    private Context mContext;

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.items_row, parent, false);
        return v;
    }

    /**
     * @param v       The view in which the elements we set up here will be displayed.
     * @param context The running context where this ListView adapter will be active.
     * @param c       The Cursor containing the query results we will display.
     * @author will
     */

    @Override
    public void bindView(View v, Context context, Cursor c) {
        String title = c.getString(c.getColumnIndexOrThrow(ShoppingCartTable.COLUMN_NAME));
        String date = c.getString(c.getColumnIndexOrThrow(ShoppingCartTable.COLUMN_PRICE));

        /**
         * Next set the title of the entry.
         */

        TextView title_text = (TextView) v.findViewById(R.id.item_order);
        if (title_text != null) {
            title_text.setText(title);
        }

        /**
         * Set Date
         */

        TextView date_text = (TextView) v.findViewById(R.id.item_price);
        if (date_text != null) {
            date_text.setText(date);
        }

    }
}