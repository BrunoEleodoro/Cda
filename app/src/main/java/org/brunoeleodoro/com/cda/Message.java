package org.brunoeleodoro.com.cda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Victor Gabriel on 04/07/2017.
 */

public class Message {

    public void setMessage(String msg, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Aviso");
        builder.setMessage(msg);
        builder.show();
        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

}
