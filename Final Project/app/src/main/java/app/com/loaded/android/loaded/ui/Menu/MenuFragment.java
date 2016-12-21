package app.com.loaded.android.loaded.ui.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.com.loaded.android.loaded.R;

public class MenuFragment extends Fragment {

    TextView mBurgerTextView;
    TextView mFriesTextView;
    TextView mBBQTextView;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mBurgerTextView = (TextView) view.findViewById(R.id.textview_menu_burgers);
        mBurgerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("Burgers");
            }
        });
        mFriesTextView = (TextView) view.findViewById(R.id.textview_menu_fries);
        mFriesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("Fries");
            }
        });
        mBBQTextView = (TextView) view.findViewById(R.id.textview_menu_bbq);
        mBBQTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("BBQ");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: 12/20/16 figure out backstack?
    public void clickedText(String food){
        Fragment fragment = null;
        Class fragmentClass;

        switch (food) {
            case "Burgers":
                fragmentClass = BurgerFragment.class;
                break;
            case "Fries":
                fragmentClass = FriesFragment.class;
                break;
            case "BBQ":
                fragmentClass = BBQFragment.class;
                break;
            default:
                fragmentClass = MenuFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}
