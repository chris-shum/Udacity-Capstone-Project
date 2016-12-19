package app.com.loaded.android.loaded.ui.Menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import app.com.loaded.android.loaded.R;

public class MenuFragment extends Fragment {

    TextView burgerTextView;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        burgerTextView = (TextView) view.findViewById(R.id.burgers);
        burgerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedText();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void clickedText(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BurgerFragment secondFragment = new BurgerFragment();
        fragmentTransaction.replace(R.id.fragment_container, secondFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
