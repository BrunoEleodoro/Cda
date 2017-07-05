package org.brunoeleodoro.com.cda;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by bruno on 04/07/17.
 */

public class _receberDados extends AsyncTask<String,String,String> {
    Internet internet = new Internet();
    String data = "";
    Activity activity;
    public _receberDados(Activity activity,String data)
    {
        this.activity = activity;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String res = "";
        //res = internet.get2("http://apigateway.ima.sp.gov.br:8080/apiman-gateway/Hackathon/api/2.0/animal/perdidos?apikey=527e5212-a169-4649-bbda-163d20b614fa&pagina=1&limite=20&dataInicial=01%2F03%2F2017&dataFinal=30%2F03%2F2017","");
        //res = internet.get(data);
        try
        {
            URL url = new URL("http://apigateway.ima.sp.gov.br:8080/apiman-gateway/Hackathon/api/2.0/godc/ocorrencias?apikey=527e5212-a169-4649-bbda-163d20b614fa&pagina=1&limite=10&ano=2017");
            URLConnection con = url.openConnection();
            con.setDoOutput(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line = null;
            while((line = reader.readLine()) != null)
            {
                res += line;
            }
        }
        catch (Exception e)
        {
            res += "[erro]";
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("Script",s);
        Message message = new Message();
        if(s.contains("[erro]"))
        {
            message.setMessage("Ocorreu um erro,tente novamente mais tarde",activity);
        }
        else
        {
            try
            {
                ArrayList<Ponto> pontos = new ArrayList<>();
                JSONObject object = new JSONObject(s);
                JSONArray array = new JSONArray(object.getString("content"));
                int i = 0;
                while(i < array.length())
                {
                    JSONObject dados = array.getJSONObject(i);
                    try
                    {
                        String dataPublicacao = dados.getString("codarDescricao");
                        Double lat = Double.parseDouble(dados.getString("latitude"));
                        Double lng = Double.parseDouble(dados.getString("longitude"));
                        Ponto ponto = new Ponto();
                        ponto.setData(dataPublicacao);
                        ponto.setLat(lat);
                        ponto.setLng(lng);
                        pontos.add(ponto);
                    }
                    catch(Exception e)
                    {

                    }

                    i++;
                }

                Intent intent = new Intent(activity,MapsActivity.class);
                intent.putExtra("pontos",pontos);
                activity.startActivity(intent);
                activity.finish();
            }
            catch (Exception e)
            {
                Toast.makeText(activity, "erro e="+e, Toast.LENGTH_SHORT).show();
            }

        }
        //Message message = new Message();
        //message.setMessage(s,activity);
    }
}
