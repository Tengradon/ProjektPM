package com.example.tengradon.asystentgierlosowych;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Tengradon on 2016-07-02.
 */
public class WygraneFragment extends Fragment {

    private ArrayList<Wygrane> wygraneArrayList;
    private Type type;
    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Context context = container.getContext();
        dbHelper = new DBHelper(context);
        try {
            wygraneArrayList = dbHelper.pobierzWygrane();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ScrollView scrollView = new ScrollView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
        for(int i = 0; i < wygraneArrayList.size(); i++){
            TextView textView = new TextView(context);
            textView.setText(stworzTekst(wygraneArrayList.get(i)));
            linearLayout.addView(textView);
        }
        return scrollView;
    }

    private String stworzTekst(Wygrane wygrane){
        String tekst = "";
        type = dbHelper.pobierzTypy(wygrane.getIdKuponu());
        tekst = getResources().getString(R.string.kupon_o_id) + wygrane.getIdKuponu() + getResources().getString(R.string.jest_zwycieski) + type.getTypGryOriginal() + getResources().getString(R.string.z_dnia) + wygrane.getData();
        return tekst;
    }
}
