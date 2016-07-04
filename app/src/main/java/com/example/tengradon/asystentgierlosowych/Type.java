package com.example.tengradon.asystentgierlosowych;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Mateusz on 2016-06-28.
 */
public class Type {
    private int id;
    private TypGry typGry;
    private Date dataPierwszegoLosowania;
    private Date dataOstatniegoLosowania;
    private String typowaneNumery;
    private int iloscSkreslonychLiczb;

    public Type(int id, TypGry typGry, Date dataPierwszegoLosowania, Date dataOstatniegoLosowania, String typowaneNumery, int iloscSkreslonychLiczb) {
        this.id = id;
        this.typGry = typGry;
        this.dataPierwszegoLosowania = dataPierwszegoLosowania;
        this.dataOstatniegoLosowania = dataOstatniegoLosowania;
        this.typowaneNumery = typowaneNumery;
        this.iloscSkreslonychLiczb = iloscSkreslonychLiczb;
    }

    public Type(TypGry typGry, Date dataPierwszegoLosowania, Date dataOstatniegoLosowania, String typowaneNumery, int iloscSkreslonychLiczb) {
        this.typGry = typGry;
        this.dataPierwszegoLosowania = dataPierwszegoLosowania;
        this.dataOstatniegoLosowania = dataOstatniegoLosowania;
        this.typowaneNumery = typowaneNumery;
        this.iloscSkreslonychLiczb = iloscSkreslonychLiczb;
    }

    public Type() {
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

    public TypGry getTypGryOriginal(){return typGry;}

    public String getDataPierwszegoLosowania() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data;
        data = simpleDateFormat.format(dataPierwszegoLosowania);
        return data;
    }

    public String getDataOstatniegoLosowania() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String data;
        data = simpleDateFormat.format(dataOstatniegoLosowania);
        return data;
    }

    public int getIloscSkreslonychLiczb() {
        return iloscSkreslonychLiczb;
    }

    public void setIloscSkreslonychLiczb(int iloscSkreslonychLiczb) {
        this.iloscSkreslonychLiczb = iloscSkreslonychLiczb;
    }

    public ArrayList<Integer> getTypowaneNumeryLista(){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        String[] liczby = typowaneNumery.split("\\|");
        for(int i = 0; i < liczby.length; i++){
            integerArrayList.add(Integer.parseInt(liczby[i]));
        }
        return integerArrayList;
    }

    public String getTypowaneNumery() {
        return typowaneNumery;
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

    public void setDataPierwszegoLosowania(Date dataPierwszegoLosowania) {
        this.dataPierwszegoLosowania = dataPierwszegoLosowania;
    }

    public void setDataOstatniegoLosowania(Date dataOstatniegoLosowania) {
        this.dataOstatniegoLosowania = dataOstatniegoLosowania;
    }

    public void setTypowaneNumery(String typowaneNumery) {
        this.typowaneNumery = typowaneNumery;
    }

    public static String typowaneNumeryZListy(ArrayList<Integer> typowaneNumery){
        String mojeTypy = "";
        for(int i = 0; i < typowaneNumery.size(); i++){
            mojeTypy += typowaneNumery.get(i);
            if(i != typowaneNumery.size() - 1)mojeTypy += "|";
        }

        return mojeTypy;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                "typGry=" + typGry +
                ", dataPierwszegoLosowania=" + dataPierwszegoLosowania +
                ", dataOstatniegoLosowania=" + dataOstatniegoLosowania +
                ", typowaneNumery='" + typowaneNumery + '\'' +
                ", iloscSkreslonychLiczb=" + iloscSkreslonychLiczb +
                '}';
    }
}
