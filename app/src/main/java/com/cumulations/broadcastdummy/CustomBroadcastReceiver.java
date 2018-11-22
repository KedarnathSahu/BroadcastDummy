package com.cumulations.broadcastdummy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "2nd App: "+intent.getAction(), Toast.LENGTH_SHORT).show();

    }
}