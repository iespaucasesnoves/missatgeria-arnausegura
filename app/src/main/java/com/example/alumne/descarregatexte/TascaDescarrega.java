package com.example.alumne.descarregatexte;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

class TascaDescarrega extends AsyncTask<String, Integer, ArrayList<HashMap<String, String>> > {

    BufferedReader in = null;
    int responseCode = -1;
    String texte = "";
    String[] from = {"id", "nom", "email"};
    int[] to = {R.id.textView, R.id.textView2, R.id.textView3};
    Context contexte;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public TascaDescarrega(Context ct){
        this.contexte = ct;
    }

    @Override
    protected ArrayList<HashMap<String, String>>  doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
                    try {
                        String linea;
                        while((linea = in.readLine()) != null) {
                            texte += linea;
                        }
                            JSONObject json = new JSONObject(texte);
                            JSONArray jArray = json.getJSONArray("dades");
                            for (int i = 0; i < jArray.length(); i++) {
                                HashMap<String, String> map = new HashMap<String, String>();
                                JSONObject jObject = jArray.getJSONObject(i);
                                map.put("id", jObject.getString("id"));
                                map.put("nom", jObject.getString("nom"));
                                map.put("email", jObject.getString("email"));
                                map.put("fk_role", jObject.getString("fk_role"));
                                llista.add(map);
                            }


                    return llista;
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
                in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>>  data) {
        SimpleAdapter adapter = new SimpleAdapter(contexte, data, R.layout.listitem, from, to);
        MainActivity.tv.setAdapter(adapter);
        MainActivity.tv.setTextFilterEnabled(true);
    }
}