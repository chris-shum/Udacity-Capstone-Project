package app.com.loaded.android.loaded.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.loaded.android.loaded.R;


public class MainFragment extends Fragment {

//    private DatabaseReference mFirebaseDatabaseReference;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // TODO: 12/14/16 constraint layout and add homepage stuff
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}