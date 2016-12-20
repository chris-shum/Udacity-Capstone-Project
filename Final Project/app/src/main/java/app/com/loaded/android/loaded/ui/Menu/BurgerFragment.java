package app.com.loaded.android.loaded.ui.Menu;


import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.Singleton;
import app.com.loaded.android.loaded.data.local.ShoppingCartContentProvider;
import app.com.loaded.android.loaded.data.local.ShoppingCartTable;
import app.com.loaded.android.loaded.data.model.LoadedMenuItems;
import app.com.loaded.android.loaded.presenter.BuildOrderString;
import app.com.loaded.android.loaded.presenter.UpdateTotal;

import static app.com.loaded.android.loaded.adapters.FirebaseToppingsRecyclerViewAdapter.createFirebaseRecyclerViewAdapter;
import static app.com.loaded.android.loaded.presenter.CreateSpinner.createBurgerSpinner;
import static app.com.loaded.android.loaded.presenter.FormatCurrency.formatCurrency;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurgerFragment extends Fragment {

    List<String> mSpinnerMeatArray, mSpinnerCheesesArray, mSpinnerFriesArray;
    List<LoadedMenuItems> mCheesesObjectArray, mFriesObjectArray;
    DatabaseReference mFirebaseDatabase;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    TextView mTotalTextView;
    String mLastCheeseItem = "No cheese +$0.00";
    String mLastFriesItem = "No fries +$0.00";
    Button mAddToCartButton;
    Singleton mSingleton;


    // TODO: 12/18/16 selections add prices and changes textview on bottom total
    // TODO: 12/18/16 add add to cart buttons
    // TODO: 12/18/16 add to cart buttons need to add data to sqlite database via content provider

    public BurgerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mSingleton = Singleton.getInstance();
        mContext = getContext();

        //cooked spinner setup
        mSpinnerMeatArray = new ArrayList<String>();
        mCheesesObjectArray = new ArrayList<LoadedMenuItems>();
        mFriesObjectArray = new ArrayList<LoadedMenuItems>();
        mSpinnerCheesesArray = new ArrayList<String>();
        mSpinnerFriesArray = new ArrayList<String>();

        mSpinnerMeatArray.add("Meat");
        mSpinnerCheesesArray.add(mLastCheeseItem);
        mSpinnerFriesArray.add(mLastFriesItem);

        // TODO: 12/18/16 create method to make these firebase calls
        mFirebaseDatabase.child("menu").child("-Burger").child("cooked").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> td = (ArrayList<String>) dataSnapshot.getValue();
                mSpinnerMeatArray.addAll(td);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });

        mFirebaseDatabase.child("menu").child("-Burger").child("cheeses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LoadedMenuItems post = postSnapshot.getValue(LoadedMenuItems.class);
                    mCheesesObjectArray.add(post);
                    mSpinnerCheesesArray.add(post.getName() + " +" + formatCurrency(post.getPrice()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mFirebaseDatabase.child("menu").child("-Fries").child("types").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LoadedMenuItems post = postSnapshot.getValue(LoadedMenuItems.class);
                    mFriesObjectArray.add(post);
                    mSpinnerFriesArray.add(post.getName() + " +" + formatCurrency(post.getPrice() - 2));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger, container, false);
        mTotalTextView = (TextView) view.findViewById(R.id.textView_burgerTotal);
        mAddToCartButton = (Button) view.findViewById(R.id.button_addToCart);

        createBurgerSpinner(view.getContext(), android.R.layout.simple_spinner_item, mSpinnerMeatArray, view.findViewById(R.id.spinner_meat)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSingleton.setMeat(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createBurgerSpinner(view.getContext(), android.R.layout.simple_spinner_item, mSpinnerCheesesArray, view.findViewById(R.id.spinner_cheeses)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentSelection = adapterView.getSelectedItem().toString();
                UpdateTotal.updateBurgerTotal(mTotalTextView, mLastCheeseItem, false);
                UpdateTotal.updateBurgerTotal(mTotalTextView, currentSelection, true);
                mLastCheeseItem = currentSelection;
                mSingleton.setCheese(currentSelection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createBurgerSpinner(view.getContext(), android.R.layout.simple_spinner_item, mSpinnerFriesArray, view.findViewById(R.id.spinner_fries)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentSelection = adapterView.getSelectedItem().toString();
                UpdateTotal.updateBurgerTotal(mTotalTextView, mLastFriesItem, false);
                UpdateTotal.updateBurgerTotal(mTotalTextView, currentSelection, true);
                mLastFriesItem = currentSelection;
                mSingleton.setFries(currentSelection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Initializes Recycler View and Layout Manager.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_toppings);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setAdapter(createFirebaseRecyclerViewAdapter(mFirebaseDatabase, mLinearLayoutManager, mRecyclerView, mTotalTextView, mSingleton));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSingleton.getMeat().equals("Meat")) {
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ShoppingCartTable.COLUMN_DESCRIPTION, BuildOrderString.buildBurgerOrder(mTotalTextView));
                    contentValues.put(ShoppingCartTable.COLUMN_PRICE, mSingleton.getPrice());
                    BurgerFragment.SaveToDatabase saveToDatabase = new BurgerFragment.SaveToDatabase();
                    saveToDatabase.execute(contentValues);
                }
                // TODO: 12/20/16 open dialogbox to confirm order
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
