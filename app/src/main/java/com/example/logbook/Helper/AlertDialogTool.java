package com.example.logbook.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogTool {
    public static void showConfirmDialog(Context context, String message, String title, DialogInterface.OnClickListener onOk, DialogInterface.OnClickListener onCancel) {
        new AlertDialog.Builder(context).setMessage(message)
                .setPositiveButton("OK", onOk).setNegativeButton("Cancel", onCancel).setTitle(title).show();
    }
}
