package com.example.tengradon.asystentgierlosowych;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data;
        data = simpleDateFormat.format(dataLosowania);
        return data;
    }

    public void setDataLosowania(Date dataLosowania) {
        this.dataLosowania = dataLosowania;
    }

    public String getWylosowaneLiczby() {
        return wylosowaneLiczby;
    }

    public ArrayList<Integer> getWylosowaneLiczbyLista(){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        String[] liczby = wylosowaneLiczby.split("|");
        for(int i = 0; i < liczby.length; i++){
            integerArrayList.add(Integer.parseInt(liczby[i]));
        }
        return integerArrayList;
    }

    public void setWylosowaneLiczby(String wylosowaneLiczby) {
        this.wylosowaneLiczby = wylosowaneLiczby;
    }

    public static String wylosowaneLiczbyZListy(ArrayList<Integer> typowaneNumery){
        String mojeTypy = "";
        for(int i = 0; i < typowaneNumery.size(); i++){
            mojeTypy += typowaneNumery.get(i).toString() + "|";
        }

        return mojeTypy;
    }
}
