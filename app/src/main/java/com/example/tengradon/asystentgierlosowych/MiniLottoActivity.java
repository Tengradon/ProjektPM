package com.example.tengradon.asystentgierlosowych;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    private AlertDialog.Builder builder;
    private Type type = null;
    private  DBHelper dbHelper = new DBHelper(this);
    private ObrabianieWynikow obrabianieWynikow;
    private boolean czyWygrana = false;
    private Vibrator vibrator;
    private long[] pattern = {0, 500, 500, 500, 500, 500};

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lotto_activity);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        dataStart = (EditText)findViewById(R.id.miniLottoDataPierwszegoEditText);
        dataKoniec = (EditText)findViewById(R.id.miniLottoDataOstaniegoEditText);
        liczba1 = (EditText)findViewById(R.id.miniLotto1Liczba);
        liczba2 = (EditText)findViewById(R.id.miniLotto2Liczba);
        liczba3 = (EditText)findViewById(R.id.miniLotto3Liczba);
        liczba4 = (EditText)findViewById(R.id.miniLotto4Liczba);
        liczba5 = (EditText)findViewById(R.id.miniLotto5Liczba);
        setBoundries();
    }

    public void saveMiniLottoNumbers(View view) throws ParseException {
        builder = new AlertDialog.Builder(this);
        if(sprawdzPola()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            type = new Type(TypGry.MINI_LOTTO, simpleDateFormat.parse(dataStart.getText().toString()), simpleDateFormat.parse(dataKoniec.getText().toString()), Type.typowaneNumeryZListy(typowaneNumery()), 5);
            final int id = dbHelper.wstawType(type);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(new Date());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(DateFormat.getDateInstance().parse(dataStart.getText().toString()));
            if (calendar1.equals(calendar2)) {
                if (Calendar.HOUR_OF_DAY > 22) {
                    obrabianieWynikow = new ObrabianieWynikow(TypGry.MINI_LOTTO);
                    obrabianieWynikow.execute();
                    if (obrabianieWynikow.czyWygrana(type)) {
                        czyWygrana = true;
                        builder.setMessage(getResources().getString(R.string.zwyciestwo));
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.wstawWinner(new Wygrane(id));
                            }
                        });
                        vibrator.vibrate(pattern, -1);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
            }
            if (calendar1.after(calendar2)) {
                obrabianieWynikow = new ObrabianieWynikow(TypGry.MINI_LOTTO);
                ArrayList<Results> resultsArrayList = dbHelper.pobierzWyniki(simpleDateFormat.parse(dataStart.getText().toString()), simpleDateFormat.parse(dataKoniec.getText().toString()), TypGry.MINI_LOTTO);
                boolean czyWygranaInner = false;
                for (int i = 0; i < resultsArrayList.size(); i++) {
                    obrabianieWynikow.setWyniki(resultsArrayList.get(i).getWylosowaneLiczbyLista());
                    if (obrabianieWynikow.czyWygrana(type)) czyWygranaInner = true;
                }
                if (czyWygranaInner) {
                    czyWygrana = true;
                    builder.setMessage(getResources().getString(R.string.zwyciestwo));
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHelper.wstawWinner(new Wygrane(id));
                        }
                    });
                    vibrator.vibrate(pattern, -1);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
            builder.setMessage(getResources().getString(R.string.wiadomosc_dodano_kupon) + id);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else{
            builder.setMessage(getResources().getString(R.string.ostrzezenie_nie_wypelniono_pol));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private boolean sprawdzPola() {
        boolean czyGotowy = true;
        if(isEmpty(dataStart))czyGotowy = false;
        if(isEmpty(dataKoniec))czyGotowy = false;
        if(isEmpty(liczba1))czyGotowy = false;
        if(isEmpty(liczba2))czyGotowy = false;
        if(isEmpty(liczba3))czyGotowy = false;
        if(isEmpty(liczba4))czyGotowy = false;
        if(isEmpty(liczba5))czyGotowy = false;
        return czyGotowy;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
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

    public void miniLottoFinisActivity(View view){
        finish();
    }
}
