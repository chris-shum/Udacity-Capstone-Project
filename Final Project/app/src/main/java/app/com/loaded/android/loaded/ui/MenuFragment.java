package app.com.loaded.android.loaded.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.model.LoadedMenuItem;
import app.com.loaded.android.loaded.presenter.FirebaseViewHolder;

public class MenuFragment extends Fragment {
    private View view;
    private Context c;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<LoadedMenuItem, FirebaseViewHolder> firebaseRecyclerAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        c = getContext();

        //Initializes Recycler View and Layout Manager.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
        manager = new LinearLayoutManager(c);
        mRecyclerView.setHasFixedSize(true);
        firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<LoadedMenuItem, FirebaseViewHolder>(
                        LoadedMenuItem.class,
                        R.layout.menu_card,
                        FirebaseViewHolder.class,
                        mFirebaseDatabaseReference.child("menu")
                ) {
                    @Override
                    protected void populateViewHolder(FirebaseViewHolder viewHolder, LoadedMenuItem student, int i) {
                        viewHolder.nameTextView.setText(student.getName());
                        viewHolder.priceTextView.setText(student.getPrice());
                        viewHolder.descriptionTextView.setText("testing");
                        viewHolder.imageTextView.setText("yea");
                        viewHolder.extrasTextView.setText("eaa");
                    }
                };

        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseRecyclerAdapter.getItemCount();
                int lastVisiblePosition =
                        manager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mRecyclerView.scrollToPosition(positionStart);
                }
            }
        });


        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        mRecyclerView.setLayoutManager(manager);



        // Inflate the layout for this fragment
        return view;
    }
}
