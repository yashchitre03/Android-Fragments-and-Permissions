package com.example.brdcst_rec_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class BroadcastReceiverF extends BroadcastReceiver {

    // Open Activity 2 when broadcast is received
    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.example.brdcst_rec.toast".equals(intent.getAction())){
            Integer received = intent.getIntExtra("com.example.brdcst_rec.text", -1);
            Intent in = new Intent(context, BrowserActivity.class);
            in.putExtra("number", received);
            context.startActivity(in);
        }
    }
}
