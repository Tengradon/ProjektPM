package com.example.tengradon.asystentgierlosowych;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * Created by Tengradon on 2016-06-30.
 */
public class DateTextWatcher implements TextWatcher {

    private View view;
    private boolean dayMonthValid = true;
    private static final String DATE_REGEX = "\\d{4}\\-\\d{2}\\-\\d{2}";

    public DateTextWatcher(View view){
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String data = s.toString();
        if(data.matches(DATE_REGEX)){
            int miesiac = Integer.parseInt(data.substring(5,7).toString());
            int dzien = Integer.parseInt(data.substring(8,10).toString());
            if((miesiac > 0) && (miesiac <= 12)) {
                if(czyPoprawnaData(miesiac, dzien)){
                    view.setBackgroundColor(Color.WHITE);
                    dayMonthValid = true;
                }
            }
        }
        else{
            view.setBackgroundColor(Color.RED);
            dayMonthValid = false;
        }
    }

    private boolean czyPoprawnaData(int miesiac, int dzien){
        boolean czyPoprawna = false;
        switch (miesiac){
            case 1:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
            case 2:
                if((dzien > 0) && (dzien <= 29)){
                    czyPoprawna = true;
                }
                break;
            case 3:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
            case 4:
                if((dzien > 0) && (dzien < 31)){
                    czyPoprawna = true;
                }
                break;
            case 5:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
            case 6:
                if((dzien > 0) && (dzien < 31)){
                    czyPoprawna = true;
                }
                break;
            case 7:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
            case 8:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
            case 9:
                if((dzien > 0) && (dzien < 31)){
                    czyPoprawna = true;
                }
                break;
            case 10:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
            case 11:
                if((dzien > 0) && (dzien < 31)){
                    czyPoprawna = true;
                }
                break;
            case 12:
                if((dzien > 0) && (dzien <= 31)){
                    czyPoprawna = true;
                }
                break;
        }
        return czyPoprawna;
    }
}
