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
    private TypGry typGry;
    private URL url;
    private Date dataLosowania;

    public ObrabianieWynikow(TypGry typGry) {
        this.typGry = typGry;
        wyniki = new ArrayList<Integer>();
        dataLosowania = new Date();
    }

    public ArrayList<Integer> getWyniki() {
        return wyniki;
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
        return null;
    }
}
