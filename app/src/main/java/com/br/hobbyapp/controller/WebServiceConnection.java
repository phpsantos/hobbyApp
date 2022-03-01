package com.br.hobbyapp.controller;

import android.os.AsyncTask;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class WebServiceConnection extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder resposta = new StringBuilder();

        try {
            Log.i("AsyncTask", "# ------------- Executando Thread: " + Thread.currentThread().getName());

            URL url = new URL("https://5e4c308ea641ed0014b0291f.mockapi.io/mockapi/user");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
                //Log.i("AsyncTask", "* --------------------------> Resultado: " + resposta.append(scanner.next()));
            }
        }catch (Exception  e){
            if (e != null)
            Log.i("AsyncTask", "* --------------------------> Erro na Conxão: " + e.getMessage());
        }
        return resposta.toString();
    }
}
