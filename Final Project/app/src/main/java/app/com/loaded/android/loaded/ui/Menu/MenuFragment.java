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
        Picasso.with(view.getContext()).load("https://scontent.fewr1-2.fna.fbcdn.net/v/t1.0-9/10492027_949936778387503_5151553114551951097_n.jpg?oh=42688c135b12a83dfde306bf356d03c2&oe=58E40CEE").into(mBurgerTextView);

        mBurgerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("Burgers");
            }
        });
        mFriesTextView = (CustomPicassoLayout) view.findViewById(R.id.textview_menu_fries);
        Picasso.with(view.getContext()).load("https://scontent.fewr1-2.fna.fbcdn.net/v/t1.0-9/12096452_1014543561926824_9098923290040425899_n.jpg?oh=1e2b448c02a4d31ab06096f13ddd9a44&oe=58F25FE5").into(mFriesTextView);

        mFriesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText("Fries");
            }
        });
        mBBQTextView = (CustomPicassoLayout) view.findViewById(R.id.textview_menu_bbq);
        Picasso.with(view.getContext()).load("https://scontent.fewr1-2.fna.fbcdn.net/v/t1.0-9/12027814_1011620845552429_3054100526418314299_n.jpg?oh=8646e9a09291eb8a3062312ab4e0ce40&oe=58F24978").into(mBBQTextView);

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
