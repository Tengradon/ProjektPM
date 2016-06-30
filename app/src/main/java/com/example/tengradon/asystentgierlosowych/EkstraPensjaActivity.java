package com.example.tengradon.asystentgierlosowych;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.transition.Visibility;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Mateusz on 2016-06-30.
 */
public class EkstraPensjaActivity extends Activity {

    private RadioGroup ekstraPensjaGroup;
    private EditText dataStart;
    private EditText dataKoniec;
    private EditText liczba1;
    private EditText liczba2;
    private EditText liczba3;
    private EditText liczba4;
    private EditText liczba5;
    private EditText liczba6;
    private EditText liczba7;
    private EditText liczba8;
    private int ileLiczb = 1;
    private int groupCheckedId = R.id.ekstraPensja1Radio;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekstra_pensja);
        ekstraPensjaGroup =(RadioGroup)findViewById(R.id.ekstraPensjaGrupa);
        System.out.println(ekstraPensjaGroup);
        ekstraPensjaGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                groupCheckedId = checkedId;
                pokazUkrytePola();
                System.out.println(checkedId);
            }
        });
        dataStart = (EditText)findViewById(R.id.ekstraPensjaDataPierwszegoEditText);
        dataKoniec = (EditText)findViewById(R.id.ekstraPensjaDataOstaniegoEditText);
        liczba1 = (EditText)findViewById(R.id.ekstraPensja1Liczba);
        liczba2 = (EditText)findViewById(R.id.ekstraPensja2Liczba);
        liczba3 = (EditText)findViewById(R.id.ekstraPensja3Liczba);
        liczba4 = (EditText)findViewById(R.id.ekstraPensja4Liczba);
        liczba5 = (EditText)findViewById(R.id.ekstraPensja5Liczba);
        liczba6 = (EditText)findViewById(R.id.ekstraPensja6Liczba);
        liczba7 = (EditText)findViewById(R.id.ekstraPensja7Liczba);
        liczba8 = (EditText)findViewById(R.id.ekstraPensja8Liczba);
        setBoundries();
    }

    private void pokazUkrytePola(){
        switch(groupCheckedId){
            case R.id.ekstraPensja2Radio:
                ileLiczb = 2;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case R.id.ekstraPensja3Radio:
                ileLiczb = 3;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE);
                break;
            case R.id.ekstraPensja4Radio:
                ileLiczb = 4;
                ukrytePola(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            default:
                ileLiczb = 1;
                ukrytePola(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
        }
    }

    private void ukrytePola(int pole1, int pole2, int pole3){
        liczba6.setVisibility(pole1);
        liczba7.setVisibility(pole2);
        liczba8.setVisibility(pole3);
    }

    public void saveEkstraPensjaNumbers(View view){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Type type = null;
        try {
            type = new Type(TypGry.EKSTRA_PENSJA, simpleDateFormat.parse(dataStart.getText().toString()), simpleDateFormat.parse(dataKoniec.getText().toString()), Type.typowaneNumeryZListy(typowaneNumery()), ileLiczb);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.wstawType(type);
    }

    private void setBoundries(){
        liczba1.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 35)});
        liczba2.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 35)});
        liczba3.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 35)});
        liczba4.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 35)});
        liczba5.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 35)});
        liczba6.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 4)});
        liczba7.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 4)});
        liczba8.setFilters(new InputFilter[]{new InputFilterMinMax(1, 4)});
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
        if(ileLiczb == 4){
            for(int i = 0; i < numery.size(); i++){
                int j = i+1;
                numery.add(j);
            }
        }
        else{
            numery.add(Integer.parseInt(liczba6.getText().toString()));
            if(ileLiczb > 1)numery.add(Integer.parseInt(liczba7.getText().toString()));
            if(ileLiczb > 2)numery.add(Integer.parseInt(liczba8.getText().toString()));
        }
        return numery;
    }

    public void finisActivity(View view){
        finish();
    }
}
