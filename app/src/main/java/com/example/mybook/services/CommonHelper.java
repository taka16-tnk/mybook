package com.example.mybook.services;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.security.InvalidKeyException;

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

    public static void showErrorDialog(final Exception e, final Context context) {
        new AlertDialog.Builder(context)
                .setMessage(e.getMessage())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
