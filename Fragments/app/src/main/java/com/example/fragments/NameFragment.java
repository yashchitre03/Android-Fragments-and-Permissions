package com.example.fragments;

import 	android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class NameFragment extends ListFragment {
    private ListSelectionListener mListener = null;

    public interface ListSelectionListener {
        void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        getListView().setItemChecked(pos, true);
        mListener.onListSelection(pos);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {
            mListener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        setListAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.phone_item, MainActivity.mNameArray));

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }
}
