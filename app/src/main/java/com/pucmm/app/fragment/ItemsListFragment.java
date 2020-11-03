package com.pucmm.app.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import com.pucmm.app.R;

import java.util.ArrayList;

public class ItemsListFragment extends ListFragment {
    private OnItemSelectedListener listener;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnItemSelectedListener {
        /**
         * Called by HeadlinesFragment when a list item is selected
         */
        public void onItemSelected(Item item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        final int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        // Create an array adapter for the list view, using the Ipsum headlines array
        final ArrayList<Item> items = Item.getItems();
        final ArrayAdapter<Item> adapter = new ArrayAdapter<>(getActivity(), layout, items);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate view
        return inflater.inflate(R.layout.fragment_items_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Activity activity = (Activity) context;

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            if (activity instanceof OnItemSelectedListener) {
                listener = (OnItemSelectedListener) activity;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        Item item = (Item) getListAdapter().getItem(position);
        listener.onItemSelected(item);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(position, true);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}
