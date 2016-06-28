package com.example.tengradon.asystentgierlosowych;

/**
 * Created by Mateusz on 2016-06-28.
 */
public class Wygrane {
    private int id;
    private int idKuponu;

    public Wygrane(int id, int idKuponu) {
        this.id = id;
        this.idKuponu = idKuponu;
    }

    public Wygrane(int idKuponu) {
        this.idKuponu = idKuponu;
    }

    public Wygrane() {
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
}
