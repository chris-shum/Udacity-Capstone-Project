package app.com.loaded.android.loaded.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.loaded.android.loaded.R;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // TODO: 12/14/16 figure out how to get data from content provider and use loaders to load order
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}
