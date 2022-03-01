package com.br.hobbyapp.model;

import java.io.Serializable;

public class Match implements Serializable {

    private String id_match;
    private String nm_group_match;
    private String date_match;
    private String time_match;

    public Match(String nameGroupMatch, String dateMatch, String timeMatch){
        setNm_group_match(nameGroupMatch);
        setDate_match(dateMatch);
        setTime_match(timeMatch);
    }

    public Match() {
    }

    public String getId_match() {
        return id_match;
    }

    public void setId_match(String id_match) {
        this.id_match = id_match;
    }

    public String getNm_group_match() {
        return nm_group_match;
    }

    public void setNm_group_match(String nm_group_match) {
        this.nm_group_match = nm_group_match;
    }

    public String getDate_match() {
        return date_match;
    }

    public void setDate_match(String date_match) {
        this.date_match = date_match;
    }

    public String getTime_match() {
        return time_match;
    }

    public void setTime_match(String time_match) {
        this.time_match = time_match;
    }
}
