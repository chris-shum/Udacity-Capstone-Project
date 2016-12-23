package app.com.loaded.android.loaded.ui.Menu;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.Singleton;
import app.com.loaded.android.loaded.data.local.ShoppingCartContentProvider;
import app.com.loaded.android.loaded.data.local.ShoppingCartTable;
import app.com.loaded.android.loaded.presenter.FormatCurrency;

import static app.com.loaded.android.loaded.adapters.FirebaseRecyclerViewAdapter.createFirebaseFriesAndBBQRecyclerViewAdapter;

public class BBQFragment extends Fragment {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    Context mContext;
    DatabaseReference mFirebaseDatabase;
    TextView mTotalTextView;
    Singleton mSingleton;
    Button mAddToCartButton;


    public BBQFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mSingleton = Singleton.getInstance();
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bbq, container, false);
        mTotalTextView = (TextView) view.findViewById(R.id.textView_bbqTotal);
        mAddToCartButton = (Button) view.findViewById(R.id.button_bbq_addToCart);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_bbq);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setAdapter(createFirebaseFriesAndBBQRecyclerViewAdapter(mFirebaseDatabase, mLinearLayoutManager, mRecyclerView, mTotalTextView, mSingleton, "-BBQ"));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSingleton.getFriesAndBBQMap().size() == 0) {
                } else {
                    for (String key : mSingleton.getFriesAndBBQMap().keySet()) {
                        String[] boop = key.split("\\$");
                        double price = Double.valueOf(boop[1]) * Double.valueOf(mSingleton.getFriesAndBBQMap().get(key).toString());
                        String description = mSingleton.getFriesAndBBQMap().get(key) + "x " + key;
                        String[] newPrice = FormatCurrency.formatCurrency(price).split("\\$");
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(ShoppingCartTable.COLUMN_DESCRIPTION, description);
                        contentValues.put(ShoppingCartTable.COLUMN_PRICE, newPrice[1]);
                        BBQFragment.SaveToDatabase saveToDatabase = new BBQFragment.SaveToDatabase();
                        saveToDatabase.execute(contentValues);
                    }
                    mSingleton.getFriesAndBBQMap().clear();
                }
            }
        });

        return view;
    }

    private class SaveToDatabase extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... contentValues) {
            mContext.getContentResolver().insert(ShoppingCartContentProvider.CONTENT_URI, contentValues[0]);
            return null;
        }
    }

}
