package com.example.alumne.descarregatexte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class EnviarMissatge extends AppCompatActivity {
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_missatge);
        editText = findViewById(R.id.missatge);
    }

    public void onClick(View v) {
        HashMap<String, String> dades = new HashMap<>();
        String text = editText.getText().toString();
        dades.put("msg", text);
        dades.put("codiusuari", String.valueOf(MainActivity.preferencies.getCodiusuari()));
        System.out.println(dades);
        CridadaPost(dades);
        new TascaDescarrega(this).execute();
        finish();
    }

    public static String CridadaPost(HashMap<String, String> parametres) {
        String resultat = "";
        String adrecaURL = "https://iesmantpc.000webhostapp.com/public/provamissatge/";
        try {
            URL url = new URL(adrecaURL);
            HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection();
            httpConn.setReadTimeout(15000);
            httpConn.setConnectTimeout(25000);
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            OutputStream os = httpConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            Log.i("ResConnectUtils", montaParametres(parametres));
            writer.write(montaParametres(parametres));
            writer.flush();
            writer.close();
            os.close();
            int resposta = httpConn.getResponseCode();
            if (resposta == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    resultat += line;
                }
                Log.i("ResConnectUtils", resultat);
            } else {
                resultat = "";
                Log.i("ResConnectUtils", "Errors:" + resposta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultat;
    }

    private static String montaParametres(HashMap<String, String> params) throws UnsupportedEncodingException {
// A partir d'un hashmap clau-valor cream
//               clau1=valor1&clau2=valor2&...
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}
