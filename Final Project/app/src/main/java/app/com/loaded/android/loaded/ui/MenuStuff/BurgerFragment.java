package app.com.loaded.android.loaded.ui.MenuStuff;


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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.model.LoadedMenuItems;
import app.com.loaded.android.loaded.presenter.ToppingsViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurgerFragment extends Fragment {

    List<String> spinnerArrayMeat, spinnerArrayCheeses, spinnerArrayFries;
    List<LoadedMenuItems> ToppingsArray, FriesArray;
    DatabaseReference firebaseDatabase;
    CheckBox nameCheckBox;
    private Context c;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private FirebaseRecyclerAdapter<LoadedMenuItems, ToppingsViewHolder> firebaseRecyclerAdapter;


    public BurgerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

//cooked spinner setup
        spinnerArrayMeat = new ArrayList<String>();
        ToppingsArray = new ArrayList<LoadedMenuItems>();
        FriesArray = new ArrayList<LoadedMenuItems>();
        spinnerArrayCheeses = new ArrayList<String>();
        spinnerArrayFries = new ArrayList<String>();
        firebaseDatabase.child("menu").child("-Burger").child("cooked").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                List<String> td = (ArrayList<String>) dataSnapshot.getValue();
                Log.e("count", td.toString());
                Log.e("count", td.get(0).toString());
                spinnerArrayMeat.addAll(td);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());

            }
        });
        // you need to have a list of data that you want the spinner to display
        spinnerArrayMeat.add("item1");
        spinnerArrayMeat.add("item1");
        spinnerArrayMeat.add("item2");
        spinnerArrayCheeses.add("waaah");
        spinnerArrayFries.add("Nope");


        firebaseDatabase.child("menu").child("-Burger").child("cheeses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    LoadedMenuItems post = postSnapshot.getValue(LoadedMenuItems.class);
                    ToppingsArray.add(post);
                    spinnerArrayCheeses.add(post.getName());
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
                    FriesArray.add(post);
                    spinnerArrayFries.add(post.getName());
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, spinnerArrayMeat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) view.findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, spinnerArrayCheeses);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems2 = (Spinner) view.findViewById(R.id.spinner3);
        sItems2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, spinnerArrayFries);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems3 = (Spinner) view.findViewById(R.id.spinner4);
        sItems3.setAdapter(adapter3);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        c = getContext();

        //Initializes Recycler View and Layout Manager.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.toppingsRecyclerView);
        manager = new LinearLayoutManager(c);
        mRecyclerView.setHasFixedSize(true);
        firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<LoadedMenuItems, ToppingsViewHolder>(
                        LoadedMenuItems.class,
                        R.layout.toppings_card,
                        ToppingsViewHolder.class,
                        firebaseDatabase.child("menu").child("-Burger").child("toppings")
                ) {
                    @Override
                    protected void populateViewHolder(ToppingsViewHolder viewHolder, LoadedMenuItems student, int i) {
                        viewHolder.nameTextView.setText(student.getName());
                    }
                };

        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseRecyclerAdapter.getItemCount();
                int lastVisiblePosition =
                        manager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mRecyclerView.scrollToPosition(positionStart);
                }
            }
        });


        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        mRecyclerView.setLayoutManager(manager);


        return view;
    }

}
