package com.example.fragments;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragments.NameFragment.ListSelectionListener;


public class MainActivity extends FragmentActivity implements ListSelectionListener {

    public int temp = -1;

    public static String[] mNameArray;

    public static Integer[] mImageArray = {R.drawable.iphone11promax,
            R.drawable.lgg8thinq,
            R.drawable.note10plus,
            R.drawable.oneplus7pro,
            R.drawable.pixel3xl};

    private final ImageFragment mImageFragment = new ImageFragment();
    private FragmentManager mFragmentManager;

    private FrameLayout mNameFrameLayout, mImageFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Add app icon to the actionbar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.fragment_icon_round);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameArray = getResources().getStringArray(R.array.Name);

        mNameFrameLayout = findViewById(R.id.name_fragment_container);
        mImageFrameLayout = findViewById(R.id.image_fragment_container);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();
        fragmentTransaction.replace(
                R.id.name_fragment_container,
                new NameFragment());
        fragmentTransaction.commit();
        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });
    }

    // Call setLayout when configuration changes
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayout();
    }

    // Send boradcast of the phone selected
    private void sendBroadcast(){
        if (temp != -1){
            Intent intent = new Intent("com.example.brdcst_rec.toast");
            intent.putExtra("com.example.brdcst_rec.text", temp);
            sendOrderedBroadcast(intent, null);
        }
        else{
            Toast.makeText(this, "No Option Selected!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Switch case used for the item selected in menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                sendBroadcast();
                break;
            case R.id.item2:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Sets the layout depending upon if fragment has been added to back-stack or not
    private void setLayout() {
        if (!mImageFragment.isAdded()) {
            mNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            if (getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE){
                mNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
                mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }

            else {
                mNameFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
                mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
        }
    }

    // Add fragment if item is selected in the list
    @Override
    public void onListSelection(int index) {
        temp = index;
        if (!mImageFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.image_fragment_container, mImageFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }

        if (mImageFragment.getShownIndex() != index) {
            mImageFragment.showImageAtIndex(index);
        }
    }
}
