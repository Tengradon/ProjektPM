package com.example.tengradon.asystentgierlosowych;

import java.text.SimpleDateFormat;
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

    public Results() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypGry() {
        return typGry.getValue();
    }

    public void setTypGry(int typGry) {
        switch (typGry) {
            case 1:
                this.typGry = TypGry.LOTTO;
                break;
            case 2:
                this.typGry = TypGry.EKSTRA_PENSJA;
                break;
            case 3:
                this.typGry = TypGry.MULTI_MULTI14;
                break;
            case 4:
                this.typGry = TypGry.MULTI_MULTI22;
                break;
            case 5:
                this.typGry = TypGry.MINI_LOTTO;
                break;
        }
    }

    public String getDataLosowania() {
        String data;
        data = String.valueOf(dataLosowania);
        return data;
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
