package app.com.loaded.android.loaded.presenter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.widget.ListView;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.local.ShoppingCartContentProvider;
import app.com.loaded.android.loaded.data.local.ShoppingCartTable;

/**
 * Created by ShowMe on 12/20/16.
 */

public class ListViewCursorLoader extends FragmentActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 0x02;
    private CursorAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_cart);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

//        adapter = new SimpleCursorAdapter(this, R.layout.items_row, null,
//                ShoppingCartTable.RESTAURANT_COLUMNS, new int[]{R.id.item_order},
//                Adapter.NO_SELECTION);
        ListView listView = (ListView) findViewById(android.R.id.list);
//        listView.setAdapter(adapter);

        adapter = new CustomCursorAdapter(this, cursor);
        listView.setAdapter(adapter);

    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(this,
                ShoppingCartContentProvider.CONTENT_URI
                , ShoppingCartTable.RESTAURANT_COLUMNS, null, null, null);
    }

    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }
}