
package com.example.tengradon.asystentgierlosowych;

        import java.util.ArrayList;
/**
 * Created by Rafal on 2016-06-28.
 */


public class GryLiczbowe {
    private Type type;
    private ArrayList<Integer> typowaneLIczby;
    private ObrabianieWynikow obrabianieWynikow;
    private ArrayList<Results> resultsArrayList;

    public GryLiczbowe(Type type, ArrayList<Results> resultsArrayList) {
        this.type = type;
        obrabianieWynikow = new ObrabianieWynikow(type.getTypGryOriginal());
        this.resultsArrayList = resultsArrayList;
        typowaneLIczby = type.getTypowaneNumeryLista();
    }

    public boolean isLottoWin(){
        boolean flag = false;
        //TODO: sprawdzic czy w podanym przedziale czasowym byly wygrane
        return flag;
    }

    public boolean isMultiWin(){
        boolean flag = false;
        //TODO: sprawdzic czy w podanym przedziale czasowym byly wygrane
        return flag;
    }

    public boolean isPensjaWin(){
        boolean flag = false;
        //TODO: sprawdzic czy w podanym przedziale czasowym byly wygrane
        return flag;
    }

    public boolean isMiniWin(){
        boolean flag = false;
        //TODO: sprawdzic czy w podanym przedziale czasowym byly wygrane
        return flag;
    }
}
