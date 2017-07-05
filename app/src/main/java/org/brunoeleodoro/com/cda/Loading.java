package org.brunoeleodoro.com.cda;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Loading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        BaseURL baseURL = new BaseURL();
        String data = "&pagina=1&limite=20&dataInicial=01/03/2017&dataFinal=30/03/2017";
        new _receberDados(this,data).execute("");

    }

}
