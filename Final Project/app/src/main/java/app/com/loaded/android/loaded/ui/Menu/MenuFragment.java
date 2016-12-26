package app.com.loaded.android.loaded.ui.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.presenter.CustomPicassoLayout;

public class MenuFragment extends Fragment {

    CustomPicassoLayout mBurgerTextView;
    CustomPicassoLayout mFriesTextView;
    CustomPicassoLayout mBBQTextView;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        container.setPadding(0,0,0,0);
        mBurgerTextView = (CustomPicassoLayout) view.findViewById(R.id.textview_menu_burgers);
        Picasso.with(view.getContext()).load(getResources().getString(R.string.burger_image)).into(mBurgerTextView);

        mBurgerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("Burgers");
            }
        });
        mFriesTextView = (CustomPicassoLayout) view.findViewById(R.id.textview_menu_fries);
        Picasso.with(view.getContext()).load(getResources().getString(R.string.fries_image)).into(mFriesTextView);

        mFriesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("Fries");
            }
        });
        mBBQTextView = (CustomPicassoLayout) view.findViewById(R.id.textview_menu_bbq);
        Picasso.with(view.getContext()).load(getResources().getString(R.string.bbq_image)).into(mBBQTextView);

        mBBQTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("BBQ");
            }
        });

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
