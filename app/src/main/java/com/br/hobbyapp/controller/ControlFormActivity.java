package com.br.hobbyapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.br.hobbyapp.R;
import com.br.hobbyapp.model.Match;
import com.br.hobbyapp.service.MixPanelService;

public class ControlFormActivity extends Activity {

    MixPanelService mixpanelService = new MixPanelService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context contexto = this;

        setContentView(R.layout.actitivy_form_match);

        Button btnSaveMatch = findViewById(R.id.btnSaveMatch);
        btnSaveMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editTextNameGroup = findViewById(R.id.editTextNameGroup);
                EditText editTextDateMatch = findViewById(R.id.editTextDateMatch);
                EditText editTextTimeMatch = findViewById(R.id.editTextHourMatch);

                Match match = new Match();
                match.setNm_group_match(editTextNameGroup.getText().toString());
                match.setDate_match(editTextDateMatch.getText().toString());
                match.setTime_match(editTextTimeMatch.getText().toString());

                mixpanelService.track(contexto,"Event Test PH 3", "Cadastro de Turma", "Clique no botão" );
                Intent intentGoToMatchControl = new Intent(ControlFormActivity.this,ControlMatchActivity.class);
                intentGoToMatchControl.putExtra("parametro","S");
                intentGoToMatchControl.putExtra("match",match);

                startActivity(intentGoToMatchControl);


                //match.setNm_group_match("Turma do JapaNes");
                //listaMatchs.add(match.getNm_group_match());
                // Toast.makeText(ControlFormActivity.this, "Nome do grupo " + match.getNm_group_match() + " Data: " + match.getDate_match() + " Hora: " + match.getTime_match(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
