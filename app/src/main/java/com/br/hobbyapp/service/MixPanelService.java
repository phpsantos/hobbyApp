package com.br.hobbyapp.service;

import android.content.Context;
import android.util.Log;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

public class MixPanelService {

    public void track(Context contexto, String eventName, String eventSource, String eventParam){

        MixpanelAPI mixpanel = MixpanelAPI.getInstance(contexto, "fe9adaa55d1ec68c6c4b64e2e2708415");
        JSONObject props = new JSONObject();
        try {
            props.put("source", eventSource);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            props.put("param", eventParam);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mixpanel.track(eventName, props);
        Log.i("MixPanel","acho que consegui enviar o event");
    }
}
