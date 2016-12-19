package app.com.loaded.android.loaded.ui.Menu;


import android.content.Context;
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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.model.LoadedMenuItems;
import app.com.loaded.android.loaded.presenter.UpdateTotal;

import static app.com.loaded.android.loaded.presenter.CreateSpinner.createBurgerSpinner;
import static app.com.loaded.android.loaded.presenter.FirebaseRecyclerViewAdapter.createFirebaseRecyclerViewAdapter;
import static app.com.loaded.android.loaded.presenter.FormatCurrency.formatCurrency;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurgerFragment extends Fragment {

    List<String> spinnerArrayMeat, spinnerArrayCheeses, spinnerArrayFries;
    List<LoadedMenuItems> cheesesArray, friesArray;
    DatabaseReference firebaseDatabase;
    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    TextView totalTextView;
    String lastCheeseItem = "No cheese +$0.00";
    String lastFriesItem = "No fries +$0.00";


    // TODO: 12/18/16 selections add prices and changes textview on bottom total
    // TODO: 12/18/16 add add to cart buttons
    // TODO: 12/18/16 add to cart buttons need to add data to sqlite database via content provider

    public BurgerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //cooked spinner setup
        spinnerArrayMeat = new ArrayList<String>();
        cheesesArray = new ArrayList<LoadedMenuItems>();
        friesArray = new ArrayList<LoadedMenuItems>();
        spinnerArrayCheeses = new ArrayList<String>();
        spinnerArrayFries = new ArrayList<String>();

        spinnerArrayMeat.add("Meat");
        spinnerArrayCheeses.add("No cheese +$0.00");
        spinnerArrayFries.add("No fries +$0.00");

        // TODO: 12/18/16 create method to make these firebase calls
        firebaseDatabase.child("menu").child("-Burger").child("cooked").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> td = (ArrayList<String>) dataSnapshot.getValue();
                spinnerArrayMeat.addAll(td);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });

        firebaseDatabase.child("menu").child("-Burger").child("cheeses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LoadedMenuItems post = postSnapshot.getValue(LoadedMenuItems.class);
                    cheesesArray.add(post);
                    spinnerArrayCheeses.add(post.getName() + " +" + formatCurrency(post.getPrice()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        firebaseDatabase.child("menu").child("-Fries").child("types").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LoadedMenuItems post = postSnapshot.getValue(LoadedMenuItems.class);
                    friesArray.add(post);
                    spinnerArrayFries.add(post.getName() + " +" + formatCurrency(post.getPrice() - 2));
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
        // Inflate the layout for this fragment

        totalTextView = (TextView) view.findViewById(R.id.textView_total);

        createBurgerSpinner(view.getContext(), android.R.layout.simple_spinner_item, spinnerArrayMeat, view.findViewById(R.id.spinner_meat));
        createBurgerSpinner(view.getContext(), android.R.layout.simple_spinner_item, spinnerArrayCheeses, view.findViewById(R.id.spinner_cheeses)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentSelection = adapterView.getSelectedItem().toString();
                if (lastCheeseItem.equals("No cheese +$0.00") && currentSelection.equals("No cheese +$0.00")) {
                } else if (lastCheeseItem.equals("No cheese +$0.00") && !currentSelection.equals("No cheese +$0.00")) {
                    lastCheeseItem = currentSelection;
                    UpdateTotal.updateBurgerTotal(totalTextView, currentSelection, true);
                } else if (!lastCheeseItem.equals("No cheese +$0.00") && currentSelection.equals("No cheese +$0.00")) {
                    lastCheeseItem = "No cheese +$0.00";
                    UpdateTotal.updateBurgerTotal(totalTextView, "Hey $1.25", false);
                } else {
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createBurgerSpinner(view.getContext(), android.R.layout.simple_spinner_item, spinnerArrayFries, view.findViewById(R.id.spinner_fries)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentSelection = adapterView.getSelectedItem().toString();
                if ((lastFriesItem.equals("No fries +$0.00") || lastFriesItem.equals("Regular +$0.00")) && (currentSelection.equals("No fries +$0.00") || currentSelection.equals("Regular +$0.00"))) {
                    lastFriesItem = currentSelection;
                } else if ((lastFriesItem.equals("No fries +$0.00") || lastFriesItem.equals("Regular +$0.00")) && currentSelection.equals("Cheese Fries +$1.25")) {
                    UpdateTotal.updateBurgerTotal(totalTextView, currentSelection, true);
                    lastFriesItem = currentSelection;
                } else if ((lastFriesItem.equals("No fries +$0.00") || lastFriesItem.equals("Regular +$0.00")) && currentSelection.equals("Briskey Gravy +$4.00")) {
                    UpdateTotal.updateBurgerTotal(totalTextView, currentSelection, true);
                    lastFriesItem = currentSelection;
                } else if (lastFriesItem.equals("Cheese Fries +$1.25") && currentSelection.equals("Briskey Gravy +$4.00")) {
                    UpdateTotal.updateBurgerTotal(totalTextView, "Hey, $2.75", true);
                    lastFriesItem = currentSelection;
                } else if (lastFriesItem.equals("Briskey Gravy +$4.00") && currentSelection.equals("Cheese Fries +$1.25")) {
                    UpdateTotal.updateBurgerTotal(totalTextView, "Hey, $2.75", false);
                    lastFriesItem = currentSelection;
                } else if (lastFriesItem.equals("Briskey Gravy +$4.00") && (currentSelection.equals("Regular +$0.00") || currentSelection.equals("No fries +$0.00"))) {
                    UpdateTotal.updateBurgerTotal(totalTextView, "Hey, $4.00", false);
                    lastFriesItem = currentSelection;
                } else if (lastFriesItem.equals("Cheese Fries +$1.25") && (currentSelection.equals("Regular +$0.00") || currentSelection.equals("No fries +$0.00"))) {
                    UpdateTotal.updateBurgerTotal(totalTextView, "Hey, $1.25", false);
                    lastFriesItem = currentSelection;
                } else {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        context = getContext();

        //Initializes Recycler View and Layout Manager.
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_toppings);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(createFirebaseRecyclerViewAdapter(firebaseDatabase, linearLayoutManager, recyclerView, totalTextView));
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

}
