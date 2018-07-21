package com.example.utilisateur.qrthing.server;

import com.example.utilisateur.qrthing.models.Timelog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class TimelogMessage implements Serializable {

    private JSONObject obj;

    public TimelogMessage(int event, Timelog log){
        obj = new JSONObject();
        try {
            obj.put("event", event);
            obj.put("runner", log.getRunner());
            obj.put("time", log.getTime());
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public int getEvent() {
        try{
            return obj.getInt("event");
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Timelog getLog() {
        try{
            return new Timelog(obj.getInt("runner"), obj.getLong("time"));
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
