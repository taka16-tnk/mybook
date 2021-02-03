package com.example.mybook.services;


import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class CommonHelper {

    // Toastの表示
    public static void showToast(final Handler handler, final Context context, final int msgId) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                String msg = context.getString(msgId);
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
