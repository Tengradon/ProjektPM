
package com.example.tengradon.asystentgierlosowych;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
/**
 * Created by Rafal on 2016-06-28.
 */

public class DBHelper extends SQLiteOpenHelper{
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final String DB_NAME = "myDB";

    public static final String TYPE_TABLE_NAME = "typy";
    public static final String TYPE_COLUMN_ID = "id";
    public static final String TYPE_COLUMN_GAME_TYPE = "typGry";
    public static final String TYPE_COLUMN_FIRST_DATE = "dataPierwszegoLosowania";
    public static final String TYPE_COLUMN_LAST_DATE = "dataOstatniegoLosowania";
    public static final String TYPE_COLUMN_NUMBERS = "typowaneLiczby";
    public static final String TYPE_COLUMN_QUANTITY = "iloscLiczb";

    public static final String RESULTS_TABLE_NAME = "wyniki";
    public static final String RESULTS_COLUMN_ID = "id";
    public static final String RESULTS_COLUMN_GAME_TYPE = "typGry";
    public static final String RESULTS_COLUMN_DATE = "dataLosowania";
    public static final String RESULTS_COLUMN_NUMBERS = "wylosowaneLiczby";

    public static final String WINNER_TABLE_NAME = "wygrane";
    public static final String WINNER_COLUMN_ID = "id";
    public static final String WINNER_COLUMN_ID_TYPE = "idKuponu";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TYPE_TABLE_NAME +
                        "(id integer primary key autoincrement, typGry integer, dataPierwszegoLosowania text, dataOstatniegoLosowania text, typowaneLiczby text, iloscLiczb integer)"
        );

        db.execSQL(
                "create table " + RESULTS_TABLE_NAME +
                        "(id integer primary key autoincrement, typGry integer, dataLosowania text, wylosowaneLiczby text)"
        );

        db.execSQL(
                "create table " + WINNER_TABLE_NAME +
                        "(id integer primary key autoincrement, idKuponu integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TYPE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RESULTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WINNER_TABLE_NAME);
        onCreate(db);
    }

    public int wstawType(Type type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE_COLUMN_GAME_TYPE, type.getTypGry());
        contentValues.put(TYPE_COLUMN_FIRST_DATE, type.getDataPierwszegoLosowania());
        contentValues.put(TYPE_COLUMN_LAST_DATE, type.getDataOstatniegoLosowania());
        contentValues.put(TYPE_COLUMN_NUMBERS, type.getTypowaneNumery());
        contentValues.put(TYPE_COLUMN_QUANTITY, type.getIloscSkreslonychLiczb());
        return (int) db.insert(TYPE_TABLE_NAME, null, contentValues);

    }

    public int wstawResult(Results results){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RESULTS_COLUMN_GAME_TYPE, results.getTypGry());
        contentValues.put(RESULTS_COLUMN_DATE, results.getDataLosowania());
        contentValues.put(RESULTS_COLUMN_NUMBERS, results.getWylosowaneLiczby());
        return (int) db.insert(RESULTS_TABLE_NAME, null, contentValues);
    }

    public int wstawWinner(Wygrane wygrane){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WINNER_COLUMN_ID_TYPE, wygrane.getIdKuponu());
        return (int) db.insert(WINNER_TABLE_NAME, null, contentValues);
    }

    public ArrayList<Type> pobierzTypy(){
        ArrayList<Type>typeArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TYPE_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Type type = null;
        if(cursor.moveToFirst()){
            do{
                try {
                    type = new Type();
                    type.setId(Integer.parseInt(cursor.getString(0)));
                    type.setTypGry(Integer.parseInt(cursor.getString(1)));
                    type.setDataPierwszegoLosowania(simpleDateFormat.parse(cursor.getString(2)));
                    type.setDataOstatniegoLosowania(simpleDateFormat.parse(cursor.getString(3)));
                    type.setTypowaneNumery(cursor.getString(4));
                    type.setIloscSkreslonychLiczb(Integer.parseInt(cursor.getString(5)));
                    typeArrayList.add(type);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while(cursor.moveToNext());
        }
        return typeArrayList;
    }

    public ArrayList<Type> pobierzTypy(Date date){
        ArrayList<Type>typeArrayList = pobierzTypy();
        for(int i = 0; i < typeArrayList.size(); i++){
            try {
                if((simpleDateFormat.parse(typeArrayList.get(i).getDataOstatniegoLosowania()).after(date))){
                    typeArrayList.remove(i);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return typeArrayList;
    }

    public Type pobierzTypy(int id){
        String query = "SELECT * FROM " + TYPE_TABLE_NAME + " where " + TYPE_COLUMN_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Type type = null;
        if(cursor.moveToFirst()){
            try {
                type = new Type();
                type.setId(Integer.parseInt(cursor.getString(0)));
                type.setTypGry(Integer.parseInt(cursor.getString(1)));
                type.setDataPierwszegoLosowania(simpleDateFormat.parse(cursor.getString(2)));
                type.setDataOstatniegoLosowania(simpleDateFormat.parse(cursor.getString(3)));
                type.setTypowaneNumery(cursor.getString(4));
                type.setIloscSkreslonychLiczb(Integer.parseInt(cursor.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return type;
    }

    public ArrayList<Results> pobierzWyniki(){
        ArrayList<Results>resultsArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + RESULTS_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Results results = null;
        if(cursor.moveToFirst()){
            do{
                try{
                    results = new Results();
                    results.setId(Integer.parseInt(cursor.getString(0)));
                    results.setTypGry(Integer.parseInt(cursor.getString(1)));
                    results.setDataLosowania(simpleDateFormat.parse(cursor.getString(2)));
                    results.setWylosowaneLiczby(cursor.getString(3));
                    resultsArrayList.add(results);
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }while(cursor.moveToNext());
        }
        return resultsArrayList;
    }

    public ArrayList<Results> pobierzWyniki(Date poczatek, Date koniec, TypGry typGry){
        ArrayList<Results>resultsArrayList = new ArrayList<>();
        resultsArrayList = pobierzWyniki();
        for(int i = 0; i < resultsArrayList.size(); i++){
            try {
                if(((simpleDateFormat.parse(resultsArrayList.get(i).getDataLosowania()).before(poczatek)) && (simpleDateFormat.parse(resultsArrayList.get(i).getDataLosowania()).after(koniec))) || resultsArrayList.get(i).getTypGry() != typGry.getValue()){
                    resultsArrayList.remove(i);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return resultsArrayList;
    }

    public ArrayList<Wygrane> pobierzWygrane(){
        ArrayList<Wygrane>wygraneArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + WINNER_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Wygrane wygrane = null;
        if(cursor.moveToFirst()){
            do{
                wygrane = new Wygrane();
                wygrane.setId(Integer.parseInt(cursor.getString(0)));
                wygrane.setIdKuponu(Integer.parseInt(cursor.getString(1)));
                wygraneArrayList.add(wygrane);
            }while(cursor.moveToNext());
        }
        return wygraneArrayList;
    }

    public void usunWiersz(int id, String nazwaTabeli){
        SQLiteDatabase db = this.getWritableDatabase();
        switch(nazwaTabeli){
            case TYPE_TABLE_NAME:
                db.delete(TYPE_TABLE_NAME, TYPE_COLUMN_ID + " = " + id, null);
                break;
            case RESULTS_TABLE_NAME:
                db.delete(RESULTS_TABLE_NAME, RESULTS_COLUMN_ID + " = " + id, null);
                break;
            case WINNER_TABLE_NAME:
                db.delete(WINNER_TABLE_NAME, WINNER_COLUMN_ID + " = " + id, null);
                break;
        }

    }

    public ArrayList<Integer> przetworzWyniki(String wyniki){
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        String[] strings = wyniki.split("|");
        for(int i = 0; i < strings.length; i++){
            integerArrayList.add(Integer.parseInt(strings[i]));
        }
        return integerArrayList;
    }

    public boolean czyPobrano(TypGry typGry, Date date){
        boolean czyIstnieje = false;
        String query = "SELECT " + RESULTS_COLUMN_ID + " from " + RESULTS_TABLE_NAME + " where " + RESULTS_COLUMN_GAME_TYPE + " = " + typGry.getValue() + " and " + RESULTS_COLUMN_DATE + " = " + simpleDateFormat.format(date);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        if(count > 0) czyIstnieje = true;
        return czyIstnieje;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE from " + WINNER_TABLE_NAME;
        db.execSQL(delete);
    }

    public void deleteTypy(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE from " + TYPE_TABLE_NAME + " where " + TYPE_COLUMN_ID + " = " + id;
        db.execSQL(delete);
    }
}
