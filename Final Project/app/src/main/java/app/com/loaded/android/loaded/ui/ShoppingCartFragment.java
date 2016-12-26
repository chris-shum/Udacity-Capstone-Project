package app.com.loaded.android.loaded.ui;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.adapters.CustomCursorAdapter;
import app.com.loaded.android.loaded.data.local.ShoppingCartContentProvider;
import app.com.loaded.android.loaded.data.local.ShoppingCartTable;
import app.com.loaded.android.loaded.presenter.FormatCurrency;

public class ShoppingCartFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    CursorAdapter mAdapter;
    Cursor mCursor;
    private Context mContext;
    TextView mTotalTextView;
    Button mEmptyCartButton, mOrderButton;
    double mTotalDouble;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

        mAdapter = new CustomCursorAdapter(getActivity(), mCursor);
        setListAdapter(mAdapter);
    }

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.setPadding(64, 64, 64, 64);

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        mContext = getContext();
        mTotalTextView = (TextView) view.findViewById(R.id.textView_cartTotal);
        mEmptyCartButton = (Button) view.findViewById(R.id.button_emptyCart);
        mEmptyCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAllFromDatabase deleteAllFromDatabase = new DeleteAllFromDatabase();
                deleteAllFromDatabase.execute();
            }
        });
        mOrderButton = (Button) view.findViewById(R.id.button_placeOrder);
        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Ordered", Toast.LENGTH_SHORT).show();
                // TODO: 12/25/16 integrate Venmo
                /*
                https://github.com/venmo/app-switch-android
                Venmo library takes intents like below:
                Intent venmoIntent = VenmoLibrary.openVenmoPayment(appId, appName, recipient, amount, note, txn);
                startActivityForResult(venmoIntent, REQUEST_CODE_VENMO_APP_SWITCH);
                Can put the order from contentprovider into "note" and total into "amount" to sent order and payment to restaurant
                 */
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = ((CustomCursorAdapter) adapterView.getAdapter()).getCursor();
                cursor.moveToPosition(i);
                DeleteFromDatabase deleteFromDatabase = new DeleteFromDatabase();
                deleteFromDatabase.execute(cursor.getString(cursor.getColumnIndex("_id")));
                return false;
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                ShoppingCartContentProvider.CONTENT_URI
                , ShoppingCartTable.RESTAURANT_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        data.moveToPosition(-1);
        mTotalDouble = 0.00;
        while (data.moveToNext()) {
            String price = data.getString(data.getColumnIndex("price"));
            Double tempPrice = Double.valueOf(price);
            mTotalDouble += tempPrice;
        }
        mTotalDouble *= 1.08;
        mTotalTextView.setText("Total +Tax: " + FormatCurrency.formatCurrency(mTotalDouble));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    // TODO: 12/20/16 figure out how to move this into own class
    private class DeleteFromDatabase extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... idValue) {
            mContext.getContentResolver().delete(ShoppingCartContentProvider.CONTENT_URI, "_id = ?", idValue);
            return null;
        }
    }

    private class DeleteAllFromDatabase extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            mContext.getContentResolver().delete(ShoppingCartContentProvider.CONTENT_URI, null, null);
            return null;
        }
    }
}
