package com.example.tengradon.asystentgierlosowych;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Rafal on 2016-06-30.
 */

public class MiniLottoActivity extends Activity {

    private EditText dataStart;
    private EditText dataKoniec;
    private EditText liczba1;
    private EditText liczba2;
    private EditText liczba3;
    private EditText liczba4;
    private EditText liczba5;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotto_activity);
        dataStart = (EditText)findViewById(R.id.miniLottoDataPierwszegoEditText);
        dataKoniec = (EditText)findViewById(R.id.miniLottoDataOstaniegoEditText);
        liczba1 = (EditText)findViewById(R.id.miniLotto1Liczba);
        liczba2 = (EditText)findViewById(R.id.miniLotto2Liczba);
        liczba3 = (EditText)findViewById(R.id.miniLotto3Liczba);
        liczba4 = (EditText)findViewById(R.id.miniLotto4Liczba);
        liczba5 = (EditText)findViewById(R.id.miniLotto5Liczba);
        setBoundries();
    }

    public void saveMiniLottoNumbers(View view){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Type type = null;
        try {
            type = new Type(TypGry.MINI_LOTTO, simpleDateFormat.parse(dataStart.getText().toString()), simpleDateFormat.parse(dataKoniec.getText().toString()), Type.typowaneNumeryZListy(typowaneNumery()), 5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.wstawType(type);
    }

    private void setBoundries(){
        liczba1.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 42)});
        liczba2.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 42)});
        liczba3.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 42)});
        liczba4.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 42)});
        liczba5.setFilters(new InputFilter[]{new InputFilterMinMax(1, 42)});
        dataKoniec.addTextChangedListener(new DateTextWatcher(dataKoniec));
        dataKoniec.setFilters(new InputFilter[]{
                new DateTextInputFilter()
        });
        dataStart.addTextChangedListener(new DateTextWatcher(dataStart));
        dataStart.setFilters(new InputFilter[]{
                new DateTextInputFilter()
        });
    }

    private ArrayList<Integer> typowaneNumery(){
        ArrayList<Integer> numery = new ArrayList<>();
        numery.add(Integer.parseInt(liczba1.getText().toString()));
        numery.add(Integer.parseInt(liczba2.getText().toString()));
        numery.add(Integer.parseInt(liczba3.getText().toString()));
        numery.add(Integer.parseInt(liczba4.getText().toString()));
        numery.add(Integer.parseInt(liczba5.getText().toString()));
        return numery;
    }

    public void finisActivity(View view){
        finish();
    }
}
