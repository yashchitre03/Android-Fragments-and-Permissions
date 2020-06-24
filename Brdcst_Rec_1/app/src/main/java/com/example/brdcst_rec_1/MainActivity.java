package com.example.brdcst_rec_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    BroadcastReceiverF receiver = new BroadcastReceiverF();

    // Creates the main Activity and has a button to open app 2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission("edu.uic.cs478.s19.kaboom");
            }
        });
    }

    // Registers the broadcast receiver only if Permission 'Kaboom' is given
    public void checkPermission(String permission) {
        if(ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {permission}, 1);
        }
        else {
            IntentFilter filter = new IntentFilter("com.example.brdcst_rec.toast");
            filter.setPriority(5);
            registerReceiver(receiver, filter);
            Intent launch = getPackageManager().getLaunchIntentForPackage("com.example.brdcst_rec_2");
            if(launch != null) {
                startActivity(launch);
            }
            else {
                Toast.makeText(this, "'App 2' app is not available/installed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Checks if user has provided the request when asked and opens app 2 if provided, else toasts a message
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "We need this permission to open 'App 2'", Toast.LENGTH_LONG).show();
                }
                else{
                    IntentFilter filter = new IntentFilter("com.example.brdcst_rec.toast");
                    filter.setPriority(5);
                    registerReceiver(receiver, filter);
                    Intent launch = getPackageManager().getLaunchIntentForPackage("com.example.brdcst_rec_2");
                    if(launch != null) {
                        startActivity(launch);
                    }
                    else {
                        Toast.makeText(this, "'App 2' app is not available/installed", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    // Unregisters the broadcast receiver when the Activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (receiver != null) {
                this.unregisterReceiver(receiver);
            }
        } catch (IllegalArgumentException e) {
            Log.i(TAG,"Receiver 1 is already unregistered");
        }
    }
}
