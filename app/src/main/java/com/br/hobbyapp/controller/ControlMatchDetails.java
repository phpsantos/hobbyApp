package com.br.hobbyapp.controller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.br.hobbyapp.R;

public class ControlMatchDetails extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        Toast.makeText(this, "CONGRATS PAULO, we are making progress", Toast.LENGTH_SHORT).show();
    }
}
