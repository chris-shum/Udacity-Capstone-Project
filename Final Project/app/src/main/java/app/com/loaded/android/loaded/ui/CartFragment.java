package app.com.loaded.android.loaded.ui;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.local.ShoppingCartContentProvider;

public class CartFragment extends Fragment {

    Button button;
    private Context context;


    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        context = getContext();
        button = (Button) view.findViewById(R.id.wompButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteFromDatabase deleteFromDatabase = new DeleteFromDatabase();
                deleteFromDatabase.execute();
            }
        });
        // Inflate the layout for this fragment
        // TODO: 12/14/16 figure out how to get data from content provider and use loaders to load order
        return view;
    }

    private class DeleteFromDatabase extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            context.getContentResolver().delete(ShoppingCartContentProvider.CONTENT_URI, null, null);
            return null;
        }
    }
}
