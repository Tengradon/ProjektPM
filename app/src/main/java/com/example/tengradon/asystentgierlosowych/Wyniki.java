package com.example.tengradon.asystentgierlosowych;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tengradon on 2016-07-04.
 */
public class Wyniki {
    private ArrayList<Integer> wyniki;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private boolean isReady = false;
    private TypGry typGry;

    public Wyniki(TypGry typGry, ArrayList<Integer> wyniki) {
        this.typGry = typGry;
        this.wyniki = wyniki;
    }

    public Wyniki(TypGry typGry){
        this.typGry = typGry;
        wyniki = new ArrayList<>();

    }

    public ArrayList<Integer> getWyniki() {
        return wyniki;
    }



    public int sprawdzTypy(ArrayList<Integer> typowaneWyniki){
        int iloscTrafien = 0;
        int j = 0;
        Collections.sort(typowaneWyniki);
        for(int i = 0; i < typowaneWyniki.size(); i++){
            if(j == wyniki.size())break;
            if(typowaneWyniki.get(i) == wyniki.get(j)){
                iloscTrafien++;
                j++;
            }
            else if(typowaneWyniki.get(i) < wyniki.get(j)){
                continue;
            }
            else {
                j++;
                i--;
                continue;
            }
        }
        return iloscTrafien;
    }

    public boolean czyWygrana(Type type){
        boolean czyWygrana = false;
        switch(type.getTypGryOriginal()){
            case LOTTO:
                czyWygrana = czyWygrana(type, 2);
                break;
            case EKSTRA_PENSJA:
                czyWygrana = czyWygrana(type, 1);
                break;
            case MINI_LOTTO:
                czyWygrana = czyWygrana(type, 2);
                break;
            case MULTI_MULTI14:
                czyWygrana = czyWygranaMultiMulti(type);
                break;
            case MULTI_MULTI22:
                czyWygrana = czyWygranaMultiMulti(type);
                break;
        }
        return czyWygrana;
    }

    private boolean czyWygranaMultiMulti(Type type){
        boolean czyWygrana;
        switch(type.getIloscSkreslonychLiczb()){
            case 1:
                czyWygrana = czyWygrana(type, 0);
                break;
            case 2:
                czyWygrana = czyWygrana(type, 1);
                break;
            case 3:
                czyWygrana = czyWygrana(type, 1);
                break;
            case 4:
                czyWygrana = czyWygrana(type, 1);
                break;
            case 5:
                czyWygrana = czyWygrana(type, 2);
                break;
            case 6:
                czyWygrana = czyWygrana(type, 2);
                break;
            case 7:
                czyWygrana = czyWygrana(type, 2);
                break;
            default:
                czyWygrana = czyWygrana(type, 3);
                break;
        }

        return czyWygrana;
    }

    public boolean czyWygrana(Type type, int ilosc){
        boolean czyWygrana = false;
        int trafienia = 0;
        trafienia = sprawdzTypy(type.getTypowaneNumeryLista());
        if(trafienia > ilosc){
            czyWygrana = true;
        }
        return czyWygrana;
    }

    public void setWyniki(ArrayList<Integer> wyniki) {
        this.wyniki = wyniki;
    }
}
