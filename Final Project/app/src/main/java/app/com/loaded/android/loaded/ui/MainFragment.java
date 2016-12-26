package app.com.loaded.android.loaded.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import app.com.loaded.android.loaded.R;


public class MainFragment extends Fragment {

    ImageView mLogoImageView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // TODO: 12/14/16 constraint layout and add homepage stuff

        container.setPadding(16, 64, 16, 64);

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mLogoImageView = (ImageView) view.findViewById(R.id.imageView_main_loadedLogo);
        Picasso.with(view.getContext()).load(getResources().getString(R.string.logo_image)).into(mLogoImageView);

        MobileAds.initialize(getActivity().getApplicationContext(), getResources().getString(R.string.admob));
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

}