package com.example.tengradon.asystentgierlosowych;

import java.util.Date;


public class Results {

    private int id;
    private TypGry typGry;
    private Date dataLosowania;
    private String wylosowaneLiczby;

    public Results(int id, TypGry typGry, Date dataLosowania, String wylosowaneLiczby) {
        this.id = id;
        this.typGry = typGry;
        this.dataLosowania = dataLosowania;
        this.wylosowaneLiczby = wylosowaneLiczby;
    }

    public Results(TypGry typGry, Date dataLosowania, String wylosowaneLiczby) {
        this.typGry = typGry;
        this.dataLosowania = dataLosowania;
        this.wylosowaneLiczby = wylosowaneLiczby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypGry getTypGry() {
        return typGry;
    }

    public void setTypGry(TypGry typGry) {
        this.typGry = typGry;
    }

    public Date getDataLosowania() {
        return dataLosowania;
    }

    public void setDataLosowania(Date dataLosowania) {
        this.dataLosowania = dataLosowania;
    }

    public String getWylosowaneLiczby() {
        return wylosowaneLiczby;
    }

    public void setWylosowaneLiczby(String wylosowaneLiczby) {
        this.wylosowaneLiczby = wylosowaneLiczby;
    }
}
