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
import android.widget.Button;
import android.widget.ListView;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.Singleton;
import app.com.loaded.android.loaded.data.local.ShoppingCartContentProvider;
import app.com.loaded.android.loaded.data.local.ShoppingCartTable;
import app.com.loaded.android.loaded.presenter.CustomCursorAdapter;

public class CartFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    Button button;
    private Context context;
    ListView listView;
    private static final int LOADER_ID = 0x02;
    private CursorAdapter adapter;
    Cursor cursor;

    CursorAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);

        mAdapter = new CustomCursorAdapter(getActivity(), cursor);

//        mAdapter = new SimpleCursorAdapter(getActivity(),
//                android.R.layout.simple_list_item_2, null,
//                ShoppingCartTable.RESTAURANT_COLUMNS,
//                new int[]{android.R.id.text1, android.R.id.text2}, 0);
        setListAdapter(mAdapter);

        // Start out with a progress indicator.
//        setListShown(false);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
    }

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

//        context = getContext();
//        button = (Button) view.findViewById(R.id.wompButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                DeleteAllFromDatabase deleteAllFromDatabase = new DeleteAllFromDatabase();
////                deleteAllFromDatabase.execute();
////                DeleteFromDatabase deleteFromDatabase = new DeleteFromDatabase();
////                deleteFromDatabase.execute();
//
//                startActivity(new Intent(view.getContext(), ListViewCursorLoader.class));
//
//            }
//        });

//        ShoppingCartHelper mHelper = new ShoppingCartHelper(view.getContext());
//
//        Cursor cursor = mHelper.getRestaurantList();
//
//        listView = (ListView) view.findViewById(R.id.listView);
//
//        adapter = new CustomCursorAdapter(view.getContext(), cursor);
//        listView.setAdapter(adapter);

//        QueryDatabase queryDatabase = new QueryDatabase();
//        queryDatabase.execute();
////        mCursorAdapter = new SimpleCursorAdapter(view.getContext(), android.R.layout.simple_list_item_1, cursor, new String[]{ShoppingCartTable.COLUMN_PRICE}, new int[]{android.R.id.text1}, 0);
//        mCursorAdapter = new CustomCursorAdapter(view.getContext(), cursor);
//        listView.setAdapter(mCursorAdapter);

        // Inflate the layout for this fragment
        // TODO: 12/14/16 figure out how to get data from content provider and use loaders to load order
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(getContext(),
                ShoppingCartContentProvider.CONTENT_URI
                , ShoppingCartTable.RESTAURANT_COLUMNS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        adapter.swapCursor(cursor);
//        adapter.notifyDataSetChanged();

        mAdapter.swapCursor(data);

        // The list should now be shown.
//        if (isResumed()) {
//            setListShown(true);
//        } else {
//            setListShownNoAnimation(true);
//        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    private class DeleteAllFromDatabase extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            context.getContentResolver().delete(ShoppingCartContentProvider.CONTENT_URI, null, null);
            return null;
        }
    }

    private class QueryDatabase extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... contentValues) {
//            cursor = context.getContentResolver().query(ShoppingCartContentProvider.CONTENT_URI, null, null, null, null);
//            mCursorAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_1, cursor, new String[]{}, new int[]{android.R.id.text1}, 0);
//            mCursorAdapter.notifyDataSetChanged();
            return null;
        }
    }

    private class DeleteFromDatabase extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... contentValues) {
            Singleton singleton = Singleton.getInstance();
            String[] cheese = singleton.getCheese().split("\\+");
            if (cheese[0].contains("cheese")) {
                cheese[0] = "no ";
            }
            String toppings;
            if (singleton.getToppings().size() == 0) {
                toppings = "no toppings";
            } else {
                toppings = singleton.getToppings().toString();
            }
            String[] fries = singleton.getFries().split(" \\+");
            String outputMessage = singleton.getMeat() + " burger with " + cheese[0].toLowerCase()
                    + "cheese and " + toppings + ". \n" + fries[0] + ". ";
            String[] boop = new String[1];
            boop[0] = outputMessage;
            context.getContentResolver().delete(ShoppingCartContentProvider.CONTENT_URI, "name = ?", boop);
            return null;
        }
    }
}
