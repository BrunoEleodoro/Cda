package org.brunoeleodoro.com.cda;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by bruno on 04/07/17.
 */

public class _cadastrarUsuario extends AsyncTask<String,String,String> {
    String data;
    Activity activity;
    public _cadastrarUsuario(Activity activity,String data)
    {
        this.activity = activity;
        this.data = data;
    }
    @Override
    protected String doInBackground(String... strings) {
        Internet internet = new Internet();
        String res = internet.set(data);
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
    }
}
