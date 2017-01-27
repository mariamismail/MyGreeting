package com.example.dell.mygreeting.Models;

import java.io.Serializable;

/**
 * Created by DELL on 1/18/2017.
 */

public class FlickerModel implements Serializable {

    private String ID;
    private String secret;
    private String server;
    private int farm;
    private String url;
    private int tag;
    private boolean isFav;

    private int  source;


    public String getSecret() {
        return secret;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
