package com.hoperunn.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    private final String TAG="MainActivity";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG,"action is..."+action);
        Log.i(TAG,"开机完成...");
        Toast.makeText(context, "收到开机完成广播", Toast.LENGTH_SHORT).show();
    }
}
