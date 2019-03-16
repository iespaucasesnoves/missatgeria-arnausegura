package com.example.alumne.descarregatexte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSourceQuepassaeh {
    private SQLiteDatabase database;
    private HelperQuepassaeeh dbAjuda;

    private String[] allColumnsQuepassaeh = {
            HelperQuepassaeeh.COLUMN_CODI, HelperQuepassaeeh.COLUMN_CODIUSUARI,
            HelperQuepassaeeh.COLUMN_DATAHORA, HelperQuepassaeeh.COLUMN_EMAIL,
            HelperQuepassaeeh.COLUMN_FKCODIUSUARI, HelperQuepassaeeh.COLUMN_FOTO,
            HelperQuepassaeeh.COLUMN_MSG, HelperQuepassaeeh.COLUMN_NOM,
            HelperQuepassaeeh.COLUMN_PENDENT
    };

    public DataSourceQuepassaeh(Context context){dbAjuda = new HelperQuepassaeeh(context);}

    public void open() throws SQLException {
        database = dbAjuda.getWritableDatabase();
    }

    public void close() {
        dbAjuda.close();
    }

    public Missatge createMissatge(Missatge missatge){
        ContentValues values = new ContentValues();
        values.put(HelperQuepassaeeh.COLUMN_CODI,missatge.getCodi());
        values.put(HelperQuepassaeeh.COLUMN_MSG,missatge.getMsg());
        values.put(HelperQuepassaeeh.COLUMN_DATAHORA,missatge.getDataHora());
        values.put(HelperQuepassaeeh.COLUMN_FKCODIUSUARI,String.valueOf(missatge.getCodiUsuari()));
        //values.put(HelperQuepassaeeh.COLUMN_PENDENT,);
        long insertID = database.insert(HelperQuepassaeeh.TABLE_MISSATGE,null,values);
        missatge.setCodi(insertID);
        return missatge;
    }

    public boolean updateMissatge(Missatge missatge){
        ContentValues values = new ContentValues();
        long codi = missatge.getCodi();
        values.put(HelperQuepassaeeh.COLUMN_CODI,missatge.getCodi());
        values.put(HelperQuepassaeeh.COLUMN_MSG,missatge.getMsg());
        values.put(HelperQuepassaeeh.COLUMN_DATAHORA,missatge.getDataHora());
        values.put(HelperQuepassaeeh.COLUMN_FKCODIUSUARI,String.valueOf(missatge.getCodiUsuari()));
        return database.update(HelperQuepassaeeh.TABLE_MISSATGE, values,
                HelperQuepassaeeh.COLUMN_CODI + "=" + codi, null) > 0;
    }

    public void deleteMissatge(Missatge missatge){
        long codi = missatge.getCodi();
        database.delete(HelperQuepassaeeh.TABLE_MISSATGE,
                HelperQuepassaeeh.COLUMN_CODI + "=" + codi, null);
    }

    public Missatge getMissatge(long codi){
        Missatge missatge;
        Cursor cursor = database.query(HelperQuepassaeeh.TABLE_MISSATGE,
                allColumnsQuepassaeh, HelperQuepassaeeh.COLUMN_CODI + "=" + codi,null,
                null, null, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            missatge = cursorToMissatge(cursor);
        } else {
            missatge = new Missatge();
        }
        cursor.close();
        return missatge;
    }

    public List<Missatge> getAllMissatge(){
        List<Missatge> missatges =  new ArrayList<Missatge>();
        Cursor cursor = database.query(
                HelperQuepassaeeh.TABLE_MISSATGE, allColumnsQuepassaeh, null,
                null,null, null,
                HelperQuepassaeeh.COLUMN_DATAHORA + " DESC"
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Missatge missatge = cursorToMissatge(cursor);
            missatges.add(missatge);
            cursor.moveToNext();
        }
        cursor.close();
        return missatges;
    }

    private Missatge cursorToMissatge(Cursor cursor){
        Missatge m = new Missatge();
        m.setCodi(cursor.getLong(0));
        m.setMsg(cursor.getString(1));
        m.setDataHora(cursor.getString(2));
        m.setCodiUsuari(cursor.getLong(3));
        return m;
    }

    public Usuari createUsuari(Usuari usuari){
        ContentValues values = new ContentValues();
        values.put(HelperQuepassaeeh.COLUMN_CODIUSUARI, usuari.getCodiUsuari());
        values.put(HelperQuepassaeeh.COLUMN_NOM, usuari.getNom());
        values.put(HelperQuepassaeeh.COLUMN_EMAIL, usuari.getEmail());
        values.put(HelperQuepassaeeh.COLUMN_FOTO, usuari.getFoto());
        long insertCodi = database.insert(HelperQuepassaeeh.TABLE_USUARI, null, values);
        usuari.setCodiUsuari(insertCodi);
        return usuari;
    }

    public boolean updateUsuari(Usuari usuari){
        ContentValues values = new ContentValues();
        long codi = usuari.getCodiUsuari();
        values.put(HelperQuepassaeeh.COLUMN_CODIUSUARI, usuari.getCodiUsuari());
        values.put(HelperQuepassaeeh.COLUMN_NOM, usuari.getNom());
        values.put(HelperQuepassaeeh.COLUMN_EMAIL, usuari.getEmail());
        values.put(HelperQuepassaeeh.COLUMN_FOTO, usuari.getFoto());
        return database.update(HelperQuepassaeeh.TABLE_USUARI, values,
                HelperQuepassaeeh.COLUMN_CODIUSUARI + "=" + codi, null) > 0;
    }

    public void deleteUsuari(Usuari usuari){
        long codi = usuari.getCodiUsuari();
        database.delete(HelperQuepassaeeh.TABLE_USUARI,
                HelperQuepassaeeh.COLUMN_CODIUSUARI + "=" + codi, null);
    }

    public Usuari getUsuari(long codi){
        Usuari usuari;
        Cursor cursor = database.query(HelperQuepassaeeh.TABLE_USUARI,
                allColumnsQuepassaeh, HelperQuepassaeeh.COLUMN_CODIUSUARI + "="+ codi,
                null, null,null,null);
        if (cursor.getCount()> 0){
            cursor.moveToFirst();
            usuari = cursorToUsuari(cursor);
        } else {
            usuari = new Usuari();
        }
        cursor.close();
        return usuari;
    }

    public List<Usuari>getAllUsuari(){
        List<Usuari> usuaris = new ArrayList<Usuari>();
        Cursor cursor = database.query(
                HelperQuepassaeeh.TABLE_USUARI, allColumnsQuepassaeh, null,
                null, null,null,
                HelperQuepassaeeh.COLUMN_DATAHORA + " DESC"
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Usuari usuari = cursorToUsuari(cursor);
            usuaris.add(usuari);
            cursor.moveToNext();
        }
        cursor.close();
        return usuaris;
    }

    private Usuari cursorToUsuari(Cursor cursor){
        Usuari u = new Usuari();
        u.setCodiUsuari(cursor.getLong(0));
        u.setNom(cursor.getString(1));
        u.setEmail(cursor.getString(2));
        return u;
    }
}
