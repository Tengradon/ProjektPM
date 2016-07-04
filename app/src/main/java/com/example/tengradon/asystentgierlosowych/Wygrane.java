package com.example.tengradon.asystentgierlosowych;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mateusz on 2016-06-28.
 */

public class Wygrane {
    private int id;
    private int idKuponu;
    private Date dataWygrania;

    public Wygrane(int id, int idKuponu, Date dataWygrania) {
        this.id = id;
        this.idKuponu = idKuponu;
        this.dataWygrania = dataWygrania;
    }

    public Wygrane(int idKuponu, Date dataWygrania) {
        this.idKuponu = idKuponu;
        this.dataWygrania = dataWygrania;
    }

    public Wygrane(int idKuponu, String dataWygrania) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.idKuponu = idKuponu;
        this.dataWygrania = simpleDateFormat.parse(dataWygrania);
    }

    public Wygrane() {
    }

    public String getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data;
        data = simpleDateFormat.format(dataWygrania);
        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKuponu() {
        return idKuponu;
    }

    public void setIdKuponu(int idKuponu) {
        this.idKuponu = idKuponu;
    }

    public Date getDataWygrania() {
        return dataWygrania;
    }

    public void setDataWygrania(Date dataWygrania) {
        this.dataWygrania = dataWygrania;
    }
}