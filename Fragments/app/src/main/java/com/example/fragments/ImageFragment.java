package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class ImageFragment extends Fragment {

    private ImageView mImageView = null;
    private int mCurrIdx = -1;
    private int mImageArrLen;

    int getShownIndex() {
        return mCurrIdx;
    }

    void showImageAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mImageArrLen)
            return;
        mCurrIdx = newIndex;
        mImageView.setImageResource(MainActivity.mImageArray[mCurrIdx]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_fragment,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageView = getActivity().findViewById(R.id.imageView);
        mImageArrLen = MainActivity.mImageArray.length;
    }
}

