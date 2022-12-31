package com.example.fmis.adapter;

public class Apriori {
    private String for1;
    private String for2;
    private String conf;

    public String getFor1() {
        return for1;
    }

    public void setFor1(String for1) {
        this.for1 = for1;
    }

    public String getFor2() {
        return for2;
    }

    public void setFor2(String for2) {
        this.for2 = for2;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public Apriori(String for1, String for2, String conf){
        this.for1 = for1;
        this.for2 = for2;
        this.conf = conf;
    }
}
