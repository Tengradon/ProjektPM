package com.example.tengradon.asystentgierlosowych;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
/**
 * Created by Rafal on 2016-07-01.
 */
public class UsuwanieKuponowService extends IntentService {

    private int i;
    private Handler handler = new Handler();
    private DBHelper dbHelper = new DBHelper(this);
    private String currentDate;
    private ArrayList<Type> typeArrayList = null;
    private ArrayList<Wygrane> wygraneArrayList = null;

    public UsuwanieKuponowService() {
        super("usuwanie kuponow");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while(true){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    typeArrayList = dbHelper.pobierzTypy(new Date());
                    wygraneArrayList = dbHelper.pobierzWygrane();
                    if(usunStare())Toast.makeText(getApplicationContext(), getResources().getString(R.string.usuniecie_kuponu), Toast.LENGTH_LONG).show();
                }
            });
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean usunStare(){
        boolean czyUsuieto = false;
        for(int i = 0; i < typeArrayList.size(); i++){
            boolean toDelete = true;
            for(int j = 0; j < wygraneArrayList.size(); j++){
                if(typeArrayList.get(i).getId() == wygraneArrayList.get(j).getIdKuponu()){
                    toDelete = false;
                    break;
                }
            }
            if(toDelete){
                czyUsuieto = true;
                dbHelper.deleteTypy(typeArrayList.get(i).getId());
            }
        }
        return czyUsuieto;
    }
}
