package app.com.loaded.android.loaded.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import app.com.loaded.android.loaded.R;
import app.com.loaded.android.loaded.data.Singleton;
import app.com.loaded.android.loaded.data.model.LoadedMenuItems;
import app.com.loaded.android.loaded.presenter.UpdateTotal;

import static app.com.loaded.android.loaded.presenter.FormatCurrency.formatCurrency;

/**
 * Created by ShowMe on 12/20/16.
 */

public class FirebaseFriesAndBBQRecyclerViewAdapter {

    public static FirebaseRecyclerAdapter<LoadedMenuItems, FriesAndBBQViewHolder>
    createFirebaseRecyclerViewAdapter(DatabaseReference firebaseDatabase,
                                      final LinearLayoutManager manager,
                                      final RecyclerView mRecyclerView, final TextView textView, final Singleton singleton, String friesOrBBQ) {

        final FirebaseRecyclerAdapter<LoadedMenuItems, FriesAndBBQViewHolder> firebaseReyclerAdapter =
                new FirebaseRecyclerAdapter<LoadedMenuItems, FriesAndBBQViewHolder>(
                        LoadedMenuItems.class,
                        R.layout.toppings_card,
                        FriesAndBBQViewHolder.class,
                        // TODO: 12/20/16 something about child texts
                        firebaseDatabase.child("menu").child(friesOrBBQ).child("types")
                ) {
                    @Override
                    protected void populateViewHolder(final FriesAndBBQViewHolder viewHolder,
                                                      final LoadedMenuItems toppings, int i) {
                        // TODO: 12/20/16 string builder?
                        viewHolder.mNameCheckBox.setText(toppings.getName() + " +" + formatCurrency(toppings.getPrice()));
                        singleton.getToppings().clear();
                        viewHolder.mNameCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                UpdateTotal.updateBurgerTotal(textView, formatCurrency(toppings.getPrice()), b);
                                if (b) {
                                    singleton.getToppings().add(toppings.getName());
                                } else {
                                    singleton.getToppings().remove(singleton.getToppings().indexOf(toppings.getName()));
                                }
                            }
                        });
                    }
                };

        firebaseReyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = firebaseReyclerAdapter.getItemCount();
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
        return firebaseReyclerAdapter;
    }
}

