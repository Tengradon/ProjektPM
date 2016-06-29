package com.example.tengradon.asystentgierlosowych;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Tengradon on 2016-06-27.
 */
public class ObrabianieWynikow extends AsyncTask<String, Void, String> {
    private ArrayList<Integer> wyniki;
    private boolean isReady = false;
    private TypGry typGry;
    private URL url;
    private Date dataLosowania;

    public ObrabianieWynikow(TypGry typGry) {
        this.typGry = typGry;
        wyniki = new ArrayList<>();
        dataLosowania = new Date();
    }

    public ArrayList<Integer> getWyniki() {
        while(!isReady)try { Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace(); }
        System.out.println(typGry);
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

    public int sprawdzTypy(ArrayList<Integer> typowaneWyniki, ArrayList<Integer> wyniki){
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

    @Override
    protected String doInBackground(String... params) {
        try {
            switch (typGry) {
                case LOTTO:
                    url = new URL("http://app.lotto.pl/wyniki/?type=dl");
                    break;
                case EKSTRA_PENSJA:
                    url = new URL("http://app.lotto.pl/wyniki/?type=ep");
                    break;
                case MULTI_MULTI14:
                    url = new URL("http://app.lotto.pl/wyniki/?type=mm14plus");
                    break;
                case MULTI_MULTI22:
                    url = new URL("http://app.lotto.pl/wyniki/?type=mm22plus");
                    break;
                case MINI_LOTTO:
                    url = new URL("http://app.lotto.pl/wyniki/?type=el");
                    break;
            }
        }catch (MalformedURLException e){
            System.out.println("Malformed URL: " + e.getMessage());
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            inputLine = in.readLine();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dataLosowania = simpleDateFormat.parse(inputLine);
            while ((inputLine = in.readLine()) != null)
                wyniki.add(Integer.parseInt(inputLine));
            in.close();
            Collections.sort(wyniki);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(typGry);
        System.out.println(wyniki.get(1));
        isReady = true;
        return null;
    }
}
