package org.brunoeleodoro.com.cda;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Victor Gabriel on 04/07/2017.
 */

public class Internet extends BaseURL {

    String res;
    String res2;
    String data;

    public String get(String data) {
        try {
            URL url = new URL(getUrl()+""+data);
            URLConnection con = url.openConnection();
            con.setDoOutput(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "ISO-8859-1"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                res = line;
            }
        } catch (Exception e) {
            res = "" + e;
        }
        return res;
    }

    public String set(String data) {
        try {
            URL url = new URL("http://pontodaesfiha.pe.hu/teste/newUsuario.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes(data);
            printer.flush();
            printer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "ISO-8859-1"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                res2 = line;
            }
        } catch (Exception e) {
            res2 = "" + e;
        }
        return res2;
    }
}
