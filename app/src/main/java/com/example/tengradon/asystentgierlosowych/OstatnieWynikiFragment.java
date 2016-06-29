package com.example.tengradon.asystentgierlosowych;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tengradon on 2016-06-29.
 */
public class OstatnieWynikiFragment extends Fragment {

    private TextView lottoView;
    private TextView multi14View;
    private TextView multi22view;
    private TextView ekstraView;
    private TextView miniView;
    private ArrayList<Integer> wyniki;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.ostatnie_wyniki_fragment, container, false);
    }



    @Override
    public void onStart() {
        super.onStart();
        lottoView = (TextView)getView().findViewById(R.id.LottoText);
        multi14View = (TextView)getView().findViewById(R.id.multiText14);
        multi22view = (TextView)getView().findViewById(R.id.multiText22);
        ekstraView = (TextView)getView().findViewById(R.id.ekstraText);
        miniView = (TextView)getView().findViewById(R.id.miniText);
        wyniki = new ArrayList<>();
        lottoView.setText(setTextViewText(TypGry.LOTTO));
        miniView.setText(setTextViewText(TypGry.MINI_LOTTO));
        multi14View.setText(setTextViewText(TypGry.MULTI_MULTI14));
        multi22view.setText(setTextViewText(TypGry.MULTI_MULTI22));
        ekstraView.setText(setTextViewText(TypGry.EKSTRA_PENSJA));
    }

    private String setTextViewText(TypGry typGry){
        String tekst = String.valueOf(typGry) + "\n";
        ObrabianieWynikow obrabianieWynikow = new ObrabianieWynikow(typGry);
        obrabianieWynikow.execute();
        wyniki = obrabianieWynikow.getWyniki();
        for(int i = 0; i < wyniki.size(); i++){
            tekst += wyniki.get(i).toString() + "|";
        }
        return tekst;
    }
}
