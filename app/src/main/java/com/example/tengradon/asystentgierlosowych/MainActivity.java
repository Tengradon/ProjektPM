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
    private static final String PREF_DATA = "dataOstatniegoPoboru";
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
        if(calendar2.after(calendar1))aktualizujBazeLosowan();
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
        ObrabianieWynikow lotto = new ObrabianieWynikow(TypGry.LOTTO);
        lotto.execute();
        if(!dbHelper.czyPobrano(TypGry.LOTTO, lotto.getDataLosowania())){
            dbHelper.wstawResult(new Results(TypGry.LOTTO, lotto.getDataLosowania(), Type.typowaneNumeryZListy(lotto.getWyniki())));
        }
        ObrabianieWynikow multiMulti14 = new ObrabianieWynikow(TypGry.MULTI_MULTI14);
        multiMulti14.execute();
        if(!dbHelper.czyPobrano(TypGry.MULTI_MULTI14, multiMulti14.getDataLosowania())){
            dbHelper.wstawResult(new Results(TypGry.MULTI_MULTI14, multiMulti14.getDataLosowania(), Type.typowaneNumeryZListy(multiMulti14.getWyniki())));
        }
        ObrabianieWynikow multiMulti22 = new ObrabianieWynikow(TypGry.MULTI_MULTI22);
        multiMulti22.execute();
        if(!dbHelper.czyPobrano(TypGry.MULTI_MULTI22, multiMulti22.getDataLosowania())){
            dbHelper.wstawResult(new Results(TypGry.MULTI_MULTI22, multiMulti22.getDataLosowania(), Type.typowaneNumeryZListy(multiMulti22.getWyniki())));
        }
        ObrabianieWynikow ekstraPensja = new ObrabianieWynikow(TypGry.EKSTRA_PENSJA);
        ekstraPensja.execute();
        if(!dbHelper.czyPobrano(TypGry.EKSTRA_PENSJA, lotto.getDataLosowania())){
            dbHelper.wstawResult(new Results(TypGry.EKSTRA_PENSJA, ekstraPensja.getDataLosowania(), Type.typowaneNumeryZListy(ekstraPensja.getWyniki())));
        }
        ObrabianieWynikow miniLotto = new ObrabianieWynikow(TypGry.MINI_LOTTO);
        miniLotto.execute();
        if(!dbHelper.czyPobrano(TypGry.MINI_LOTTO, miniLotto.getDataLosowania())){
            dbHelper.wstawResult(new Results(TypGry.MINI_LOTTO, miniLotto.getDataLosowania(), Type.typowaneNumeryZListy(miniLotto.getWyniki())));
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_DATA, DateFormat.getDateInstance().format(calendar2.getTime()));
        editor.commit();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.pobrano_dane), Toast.LENGTH_LONG).show();
    }

}
