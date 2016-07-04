package com.example.tengradon.asystentgierlosowych;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    public static final String PREF_DATA = "dataOstatniegoPoboru";
    public static final String PREF_LOTTO = "wynikiLotto";
    public static final String PREF_MINI = "wynikiMini";
    public static final String PREF_MULTI14 = "wynikiMulti14";
    public static final String PREF_EKSTRA = "wynikiEkstra";
    public static final String PREF_MULTI22= "wynikiMulti22";
    public static final String PREF_LOTTO_DATA = "wynikiLottoData";
    public static final String PREF_MINI_DATA = "wynikiMiniData";
    public static final String PREF_MULTI14_DATA = "wynikiMulti14Data";
    public static final String PREF_EKSTRA_DATA = "wynikiEkstraData";
    public static final String PREF_MULTI22_DATA= "wynikiMulti22Data";
    private DBHelper dbHelper;
    private Calendar calendar1 = Calendar.getInstance();
    private Calendar calendar2 = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("dataPoboru", Activity.MODE_PRIVATE);
        try {
            calendar1.setTime(DateFormat.getDateInstance().parse(sharedPreferences.getString(PREF_DATA, "")));
        } catch (ParseException e) {e.printStackTrace();}
        calendar2.setTime(new Date());
        calendar2.set(Calendar.MILLISECOND, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        dbHelper = new DBHelper(this);
        aktualizujBazeLosowan();
        Intent intent = new Intent(this, UsuwanieKuponowService.class);
        startService(intent);
        Intent intent2 = new Intent(this, SprawdzWygraneService.class);
        startService(intent2);
        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new WygraneFragment();
        fragmentTransaction.replace(R.id.fragment_place, fragment, "POW");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.options) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void pokazWyborGry(View view){
        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new WyborGryFragment();
        fragmentTransaction.replace(R.id.fragment_place, fragment, "PWG");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void pokazOstatnieWyniki(View view){
        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new OstatnieWynikiFragment();
        fragmentTransaction.replace(R.id.fragment_place, fragment, "POW");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void pokazEkranLotto(View view){
        Intent intent = new Intent(MainActivity.this, LottoActivity.class);
        startActivity(intent);
    }

    public void pokazEkranMiniLotto(View view){
        Intent intent = new Intent(MainActivity.this, MiniLottoActivity.class);
        startActivity(intent);
    }

    public void pokazEkranMultiMulti(View view){
        Intent intent = new Intent(MainActivity.this, MultiMultiActivity.class);
        startActivity(intent);
    }

    public void pokazEkranEkstraPensja(View view){
        Intent intent = new Intent(MainActivity.this, EkstraPensjaActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        int count = getFragmentManager().getBackStackEntryCount();
        if(count == 0){
            super.onBackPressed();
        }
        else{
            getFragmentManager().popBackStack();
        }
    }

    public void aktualizujBazeLosowan(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ObrabianieWynikow lotto = new ObrabianieWynikow(TypGry.LOTTO);
        lotto.execute();
        Results lottoResult = new Results(TypGry.LOTTO, lotto.getDataLosowania(), Type.typowaneNumeryZListy(lotto.getWyniki()));
        if(!dbHelper.czyPobrano(TypGry.LOTTO, lotto.getDataLosowania()))dbHelper.wstawResult(lottoResult);
        editor.putString(PREF_DATA, DateFormat.getDateInstance().format(calendar2.getTime()));
        editor.putString(PREF_LOTTO, Type.typowaneNumeryZListy(lotto.getWyniki()));
        editor.putString(PREF_LOTTO_DATA, DateFormat.getDateInstance().format(lotto.getDataLosowania()));
        ObrabianieWynikow multiMulti14 = new ObrabianieWynikow(TypGry.MULTI_MULTI14);
        multiMulti14.execute();
        Results multiResult = new Results(TypGry.MULTI_MULTI14, multiMulti14.getDataLosowania(), Type.typowaneNumeryZListy(multiMulti14.getWyniki()));
        if(!dbHelper.czyPobrano(TypGry.MULTI_MULTI14, multiMulti14.getDataLosowania()))dbHelper.wstawResult(multiResult);
        editor.putString(PREF_MULTI14, Type.typowaneNumeryZListy(multiMulti14.getWyniki()));
        editor.putString(PREF_MULTI14_DATA, DateFormat.getDateInstance().format(multiMulti14.getDataLosowania()));
        ObrabianieWynikow multiMulti22 = new ObrabianieWynikow(TypGry.MULTI_MULTI22);
        multiMulti22.execute();
        Results multi2Result = new Results(TypGry.MULTI_MULTI22, multiMulti22.getDataLosowania(), Type.typowaneNumeryZListy(multiMulti22.getWyniki()));
        if(!dbHelper.czyPobrano(TypGry.MULTI_MULTI22, multiMulti22.getDataLosowania()))dbHelper.wstawResult(multi2Result);
        editor.putString(PREF_MULTI22, Type.typowaneNumeryZListy(multiMulti22.getWyniki()));
        ObrabianieWynikow ekstraPensja = new ObrabianieWynikow(TypGry.EKSTRA_PENSJA);
        ekstraPensja.execute();
        Results ekstraResult = new Results(TypGry.EKSTRA_PENSJA, ekstraPensja.getDataLosowania(), Type.typowaneNumeryZListy(ekstraPensja.getWyniki()));
        if(!dbHelper.czyPobrano(TypGry.EKSTRA_PENSJA, lotto.getDataLosowania()))dbHelper.wstawResult(ekstraResult);
        editor.putString(PREF_EKSTRA, Type.typowaneNumeryZListy(ekstraPensja.getWyniki()));
        editor.putString(PREF_EKSTRA_DATA, DateFormat.getDateInstance().format(ekstraPensja.getDataLosowania()));
        ObrabianieWynikow miniLotto = new ObrabianieWynikow(TypGry.MINI_LOTTO);
        miniLotto.execute();
        Results miniResult = new Results(TypGry.MINI_LOTTO, miniLotto.getDataLosowania(), Type.typowaneNumeryZListy(miniLotto.getWyniki()));
        if(!dbHelper.czyPobrano(TypGry.MINI_LOTTO, miniLotto.getDataLosowania()))dbHelper.wstawResult(miniResult);
        editor.putString(PREF_MINI, Type.typowaneNumeryZListy(miniLotto.getWyniki()));
        editor.putString(PREF_MINI_DATA, DateFormat.getDateInstance().format(miniLotto.getDataLosowania()));
        editor.commit();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.pobrano_dane), Toast.LENGTH_LONG).show();
    }

}