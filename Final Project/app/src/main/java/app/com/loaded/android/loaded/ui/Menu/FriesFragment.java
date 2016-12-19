package app.com.loaded.android.loaded.ui.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.loaded.android.loaded.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriesFragment extends Fragment {

    // TODO: 12/18/16 follow burger fragment
    // TODO: 12/18/16 make BBQ fragment

    public FriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fries, container, false);
    }

}
