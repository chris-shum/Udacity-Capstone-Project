package app.com.loaded.android.loaded.ui.MenuStuff;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.com.loaded.android.loaded.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurgerFragment extends Fragment {

    List<String> spinnerArray;
    DatabaseReference firebaseDatabase;
    public BurgerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinnerArray =  new ArrayList<String>();


        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
firebaseDatabase.child("menu").child("-Burger").child("cooked").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.e("Count " ,""+dataSnapshot.getChildrenCount());
            List<String> td = (ArrayList<String>) dataSnapshot.getValue();
            spinnerArray.addAll(td);

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.e("The read failed: " ,databaseError.getMessage());

    }
});
        // you need to have a list of data that you want the spinner to display
        spinnerArray.add("item1");
        spinnerArray.add("item2");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger, container, false);
        // Inflate the layout for this fragment

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) view.findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);
        return view;
    }

}
