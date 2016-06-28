package com.example.tengradon.asystentgierlosowych;
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

    public Type(TypGry typGry, Date dataPierwszegoLosowania, Date dataOstatniegoLosowania, String typowaneNumery) {
        this.typGry = typGry;
        this.dataPierwszegoLosowania = dataPierwszegoLosowania;
        this.dataOstatniegoLosowania = dataOstatniegoLosowania;
        this.typowaneNumery = typowaneNumery;
    }

    public Type(int id, TypGry typGry, Date dataPierwszegoLosowania, Date dataOstatniegoLosowania, String typowaneNumery) {
        this.id = id;
        this.typGry = typGry;
        this.dataPierwszegoLosowania = dataPierwszegoLosowania;
        this.dataOstatniegoLosowania = dataOstatniegoLosowania;
        this.typowaneNumery = typowaneNumery;
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

    public String getDataPierwszegoLosowania() {
        String data;
        data = String.valueOf(dataPierwszegoLosowania);
        return data;
    }

    public String getDataOstatniegoLosowania() {
        String data;
        data = String.valueOf(dataOstatniegoLosowania);
        return data;
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
}