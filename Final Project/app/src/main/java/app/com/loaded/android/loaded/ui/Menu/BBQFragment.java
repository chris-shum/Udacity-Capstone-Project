package app.com.loaded.android.loaded.ui.Menu;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.Singleton;

import static app.com.loaded.android.loaded.adapters.FirebaseRecyclerViewAdapter.createFirebaseFriesAndBBQRecyclerViewAdapter;

public class BBQFragment extends Fragment {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    Context mContext;
    DatabaseReference mFirebaseDatabase;
    TextView mTotalTextView;
    Singleton mSingleton;


    public BBQFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mSingleton = Singleton.getInstance();
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bbq, container, false);
        mTotalTextView = (TextView) view.findViewById(R.id.textView_bbqTotal);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_bbq);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setAdapter(createFirebaseFriesAndBBQRecyclerViewAdapter(mFirebaseDatabase, mLinearLayoutManager, mRecyclerView, mTotalTextView, mSingleton, "-BBQ"));
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        return view;
    }

}
