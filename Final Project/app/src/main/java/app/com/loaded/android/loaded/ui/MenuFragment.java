package app.com.loaded.android.loaded.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.loaded.android.loaded.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    RecyclerView menuReyclerView;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        menuReyclerView = (RecyclerView) container.findViewById(R.id.menu_recycler_view);
        // TODO: 12/14/16 create recyclerview adapter and viewholder for the menu
        // TODO: 12/14/16 firebasee to get the data
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

}
