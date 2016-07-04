package com.example.tengradon.asystentgierlosowych;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tengradon on 2016-07-02.
 */
public class SprawdzWygraneService extends IntentService {

    private Handler handler = new Handler();
    private SharedPreferences sharedPreferences;
    private Wyniki lotto = new Wyniki(TypGry.LOTTO);
    private Wyniki multiMulti14 = new Wyniki(TypGry.MULTI_MULTI14);
    private Wyniki multiMulti22 = new Wyniki(TypGry.MULTI_MULTI22);
    private Wyniki ekstraPensja = new Wyniki(TypGry.EKSTRA_PENSJA);
    private Wyniki miniLotto = new Wyniki(TypGry.MINI_LOTTO);
    private ArrayList<Type> types;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DBHelper dbHelper = new DBHelper(this);
    private Date date;

    public SprawdzWygraneService() {
        super("Sprawdz wygrane");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                pobierzKupony();
                try {
                    czyZwycieski();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, 480000);
            }
        };
        handler.post(runnableCode);
    }

    private void pobierzKupony(){
        types = dbHelper.pobierzTypy(new Date());
    }

    private boolean czyZwycieski() throws ParseException {
        boolean wynik = false;
        sharedPreferences = getSharedPreferences("dataPoboru", MODE_PRIVATE);
        lotto.setWyniki(numeryLista(sharedPreferences.getString(MainActivity.PREF_LOTTO, "-1")));
        miniLotto.setWyniki(numeryLista(sharedPreferences.getString(MainActivity.PREF_MINI, "-1")));
        multiMulti14.setWyniki(numeryLista(sharedPreferences.getString(MainActivity.PREF_MULTI14, "-1")));
        multiMulti22.setWyniki(numeryLista(sharedPreferences.getString(MainActivity.PREF_MULTI22, "-1")));
        ekstraPensja.setWyniki(numeryLista(sharedPreferences.getString(MainActivity.PREF_EKSTRA, "-1")));
        for(int i = 0; i < types.size(); i++){
            if(sprawdz(types.get(i))){
                if(!dbHelper.czyIstnieje(types.get(i).getId(), date))dbHelper.wstawWinner(new Wygrane(types.get(i).getId(), date));
                wynik = true;
            }
        }
        return wynik;
    }

    private boolean sprawdz(Type type) throws ParseException {
        boolean wynik = false;
        switch (type.getTypGryOriginal()){
            case LOTTO:
                if(lotto.czyWygrana(type))wynik = true;
                date = simpleDateFormat.parse(getSharedPreferences("dataPoboru", MODE_PRIVATE).getString(MainActivity.PREF_LOTTO_DATA, ""));
                break;
            case MULTI_MULTI22:
                if(multiMulti22.czyWygrana(type))wynik = true;
                date = simpleDateFormat.parse(getSharedPreferences("dataPoboru", MODE_PRIVATE).getString(MainActivity.PREF_MULTI22_DATA, ""));
                break;
            case MULTI_MULTI14:
                if(multiMulti14.czyWygrana(type))wynik = true;
                date = simpleDateFormat.parse(getSharedPreferences("dataPoboru", MODE_PRIVATE).getString(MainActivity.PREF_MULTI14_DATA, ""));
                break;
            case MINI_LOTTO:
                if(miniLotto.czyWygrana(type))wynik = true;
                date = simpleDateFormat.parse(getSharedPreferences("dataPoboru", MODE_PRIVATE).getString(MainActivity.PREF_MINI_DATA, ""));
                break;
            case EKSTRA_PENSJA:
                if(ekstraPensja.czyWygrana(type))wynik = true;
                date = simpleDateFormat.parse(getSharedPreferences("dataPoboru", MODE_PRIVATE).getString(MainActivity.PREF_EKSTRA_DATA, ""));
                break;
        }
        return wynik;
    }

    public ArrayList<Integer> numeryLista(String typowaneNumery){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        System.out.println("Liczba =  " + typowaneNumery);
        typowaneNumery.trim();
        typowaneNumery.replaceAll("\\s", "");
        String[] liczby = typowaneNumery.split("\\|");
        for(int i = 0; i < liczby.length; i++){
            System.out.println("Liczba =  " + liczby[i]);
            integerArrayList.add(Integer.parseInt(liczby[i]));
        }
        return integerArrayList;
    }
}
