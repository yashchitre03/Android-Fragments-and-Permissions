package com.example.brdcst_rec_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReceiverS extends BroadcastReceiver {

    // Toasts a message according to the broadcast received
    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.example.brdcst_rec.toast".equals(intent.getAction())){
            Integer received = intent.getIntExtra("com.example.brdcst_rec.text", -1);
            switch (received) {
                case 0:
                    Toast.makeText(context, "Iphone 11 Pro Max selected (from App 2)", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(context, "LG G8 thinQ selected (from App 2)", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(context, "Samsung Note 10 Plus selected (from App 2)", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(context, "Oneplus 7 Pro selected (from App 2)", Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(context, "Pixel 3XL selected (from App 2)", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }
}
