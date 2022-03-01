package com.br.hobbyapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.br.hobbyapp.R;
import com.br.hobbyapp.model.Match;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ControlMatchActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        if(isOnline(this)) {
            //faz algo :)
            Log.i("Internet Connection","Estou online");
        }else{
            Log.i("Internet Connection","NÃO Estou online");
        }

        // ------------------------------------------  //
        // Token MixPanel
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(this, "fe9adaa55d1ec68c6c4b64e2e2708415");
        JSONObject props = new JSONObject();
        try {
            props.put("source", "Pat's affiliate site");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            props.put("param", "teste de evento do PH agora online");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mixpanel.track("Test Event", props);
        Log.i("MixPanel","acho que consegui enviar o event");
        // ------------------------------------------  //

        // ------------------------------------------  //
        Intent intent = getIntent();
        //final Turma turma = (Turma) intent.getSerializableExtra("turma");
        final Match matchParam = (Match) intent.getSerializableExtra("match");
        final String parametro = (String) intent.getSerializableExtra("parametro");
        // ------------------------------------------  //

        // ------------------------------------------  //
        ArrayList<String> listaMatchs = new ArrayList<String>();
        Match match = new Match("Turma do japaNes","10/10/2010","14:00");

        match.setNm_group_match("Turma do JapaNes");
        listaMatchs.add(match.getNm_group_match());
        match.setNm_group_match("Turma do IdonZeira");
        listaMatchs.add(match.getNm_group_match());
        match.setNm_group_match("Turma do PhZito");
        listaMatchs.add(match.getNm_group_match());

        if (parametro != null){
            Toast.makeText(this, "Mandei parametro", Toast.LENGTH_SHORT).show();
            listaMatchs.add(matchParam.getNm_group_match());
        }else{
            Toast.makeText(this, "NÃO Mandei parametro", Toast.LENGTH_SHORT).show();
        }
        // ------------------------------------------  //

        // ------------------------------------------  //
        // Dados da Turma.
        //List<String> dataGroupsSchedule = new ArrayList<>(
        //        Arrays.asList("Turma do Japa","Turma do Idon","turma do PH"));

        // Achando a Lista e Add no Componente da Tela.
        ListView groupScheduleList = findViewById(R.id.ListSchedule);
        groupScheduleList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMatchs));

        Button btnTeste = findViewById(R.id.btnMatch);
        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intentGoToMatchForm = new Intent(ControlMatchActivity.this, ControlFormActivity.class);
                //startActivity(intentGoToMatchForm);
                //Toast.makeText(ControlMatchActivity.this, "TESTANDO BOTÃO", Toast.LENGTH_SHORT).show();

                try {
                    String retorno = new WebServiceConnection().execute().get();
                    Log.i("WebService Connection","Retorno: " + retorno.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i("WebService Connection","Erro no retorno do WebService");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.i("WebService Connection","Erro na execução do WebService");
                }
            }
        });
        // ------------------------------------------  //
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }
}
