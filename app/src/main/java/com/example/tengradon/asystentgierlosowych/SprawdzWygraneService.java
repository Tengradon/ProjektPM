package com.example.tengradon.asystentgierlosowych;

import android.app.IntentService;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Tengradon on 2016-07-02.
 */
public class SprawdzWygraneService extends IntentService {

    private ObrabianieWynikow lotto = new ObrabianieWynikow(TypGry.LOTTO);
    private ObrabianieWynikow multiMulti14 = new ObrabianieWynikow(TypGry.MULTI_MULTI14);
    private ObrabianieWynikow multiMulti22 = new ObrabianieWynikow(TypGry.MULTI_MULTI22);
    private ObrabianieWynikow ekstraPensja = new ObrabianieWynikow(TypGry.EKSTRA_PENSJA);
    private ObrabianieWynikow miniLotto = new ObrabianieWynikow(TypGry.MINI_LOTTO);
    private DBHelper dbHelper = new DBHelper(this);
    private Calendar calendar = Calendar.getInstance();

    public SprawdzWygraneService() {
        super("Sprawdz wygrane");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true){

            //TODO:sprawdzanie cykliczne kuponow
        }
    }
}
