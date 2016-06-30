package com.example.tengradon.asystentgierlosowych;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Tengradon on 2016-06-29.
 */
public class MultiMultiActivity extends Activity {
    private RadioGroup firstGroup;
    private RadioGroup secondGroup;
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
    private EditText liczba9;
    private EditText liczba10;
    private boolean isChecking = true;
    private int ileLiczb = 1;
    private int groupCheckedId = R.id.multiMulti1Radio;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multimulti_activity);
        firstGroup =(RadioGroup)findViewById(R.id.multiMulti1Grupa);
        secondGroup =(RadioGroup)findViewById(R.id.multiMulti2Grupa);
        firstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId != -1 && isChecking){
                    isChecking = false;
                    secondGroup.clearCheck();
                    groupCheckedId = checkedId;

                }
                isChecking = true;
                pokazUkrytePola();
                System.out.println(checkedId);
            }
        });
        secondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId != -1 && isChecking){
                    isChecking = false;
                    firstGroup.clearCheck();
                    groupCheckedId = checkedId;
                }
                isChecking = true;
                pokazUkrytePola();
                System.out.println(checkedId);
            }
        });
        dataStart = (EditText)findViewById(R.id.multiMultiDataPierwszegoEditText);
        dataKoniec = (EditText)findViewById(R.id.multiMultiDataOstaniegoEditText);
        liczba1 = (EditText)findViewById(R.id.multiMulti1Liczba);
        liczba2 = (EditText)findViewById(R.id.multiMulti2Liczba);
        liczba3 = (EditText)findViewById(R.id.multiMulti3Liczba);
        liczba4 = (EditText)findViewById(R.id.multiMulti4Liczba);
        liczba5 = (EditText)findViewById(R.id.multiMulti5Liczba);
        liczba6 = (EditText)findViewById(R.id.multiMulti6Liczba);
        liczba7 = (EditText)findViewById(R.id.multiMulti7Liczba);
        liczba8 = (EditText)findViewById(R.id.multiMulti8Liczba);
        liczba9 = (EditText)findViewById(R.id.multiMulti9Liczba);
        liczba10 = (EditText)findViewById(R.id.multiMulti10Liczba);
        setBoundries();
    }


    public void saveMiniLottoNumbers(View view){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Type type = null;
        try {
            type = new Type(TypGry.MULTI_MULTI14, simpleDateFormat.parse(dataStart.getText().toString()), simpleDateFormat.parse(dataKoniec.getText().toString()), Type.typowaneNumeryZListy(typowaneNumery()), ileLiczb);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.wstawType(type);
    }

    private void pokazUkrytePola(){
        switch(groupCheckedId){
            case R.id.multiMulti2Radio:
                ileLiczb = 2;
                ukrytePola(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti3Radio:
                ileLiczb = 3;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti4Radio:
                ileLiczb = 4;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti5Radio:
                ileLiczb = 5;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti6Radio:
                ileLiczb = 6;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti7Radio:
                ileLiczb = 7;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti8Radio:
                ileLiczb = 8;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case R.id.multiMulti9Radio:
                ileLiczb = 9;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
                break;
            case R.id. multiMulti10Radio:
                ileLiczb = 10;
                ukrytePola(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE);
                break;
            default:
                ileLiczb = 1;
                ukrytePola(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);

                break;
        }
    }

    private void ukrytePola(int pole1, int pole2, int pole3, int pole4, int pole5, int pole6, int pole7, int pole8, int pole9){
        liczba2.setVisibility(pole1);
        liczba3.setVisibility(pole2);
        liczba4.setVisibility(pole3);
        liczba5.setVisibility(pole4);
        liczba6.setVisibility(pole5);
        liczba7.setVisibility(pole6);
        liczba8.setVisibility(pole7);
        liczba9.setVisibility(pole8);
        liczba10.setVisibility(pole9);
    }

    private void setBoundries(){
        liczba1.setFilters(new InputFilter[]{ new InputFilterMinMax(1, 80)});
        liczba2.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba3.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba4.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba5.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba6.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba7.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba8.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba9.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        liczba10.setFilters(new InputFilter[]{new InputFilterMinMax(1, 80)});
        dataKoniec.addTextChangedListener(new DateTextWatcher(dataKoniec));
        dataKoniec.setFilters(new InputFilter[]{
                new DateTextInputFilter()
        });
        dataStart.addTextChangedListener(new DateTextWatcher(dataStart));
        dataStart.setFilters(new InputFilter[]{
                new DateTextInputFilter()
        });
    }

    private ArrayList<Integer> typowaneNumery() {
        ArrayList<Integer> numery = new ArrayList<>();
        numery.add(Integer.parseInt(liczba1.getText().toString()));
        if (ileLiczb > 1) numery.add(Integer.parseInt(liczba2.getText().toString()));
        if (ileLiczb > 2) numery.add(Integer.parseInt(liczba3.getText().toString()));
        if (ileLiczb > 3) numery.add(Integer.parseInt(liczba4.getText().toString()));
        if (ileLiczb > 4) numery.add(Integer.parseInt(liczba5.getText().toString()));
        if (ileLiczb > 5) numery.add(Integer.parseInt(liczba6.getText().toString()));
        if (ileLiczb > 6) numery.add(Integer.parseInt(liczba7.getText().toString()));
        if (ileLiczb > 7) numery.add(Integer.parseInt(liczba8.getText().toString()));
        if (ileLiczb > 8) numery.add(Integer.parseInt(liczba9.getText().toString()));
        if (ileLiczb > 9) numery.add(Integer.parseInt(liczba10.getText().toString()));
        return numery;
    }

    public void finisActivity(View view){
        finish();
    }
}

