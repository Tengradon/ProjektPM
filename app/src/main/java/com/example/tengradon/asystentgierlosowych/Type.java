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

    public int getId() {
        return id;
    }

    public TypGry getTypGry() {
        return typGry;
    }

    public Date getDataPierwszegoLosowania() {
        return dataPierwszegoLosowania;
    }

    public Date getDataOstatniegoLosowania() {
        return dataOstatniegoLosowania;
    }

    public String getTypowaneNumery() {
        return typowaneNumery;
    }

    public void setTypGry(TypGry typGry) {
        this.typGry = typGry;
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