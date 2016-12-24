package app.com.loaded.android.loaded.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-4193213438982161~1829803539");
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

}