package com.example.exemplobd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AlunoDB {
    private Banco banco;
    public AlunoDB(Banco b) {
        this.banco = b;
    }
    public long save(Aluno aluno) {
        long id = aluno.getId();
        SQLiteDatabase db = banco.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nome", aluno.getNome());
            values.put("curso", aluno.getCurso());
            if (id==0) {
                //novo registro
                id = db.insert("aluno", "", values);
            }
            else {
                //atualizar registro
                String _id = String.valueOf(aluno.getId());
                String[] whereArgs = new String[]{_id};
                id = db.update("aluno", values, "id=?", whereArgs);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            //db.close();
        }
        return id;
    }
    @SuppressLint("Range")
    private List<Aluno> paraLista(Cursor c) {
        List<Aluno> alunos = new ArrayList<>();
        if (c.moveToFirst()) {
            do{
                Aluno a = new Aluno();
                a.setId(c.getInt(c.getColumnIndex("id")));
                a.setNome(c.getString(c.getColumnIndex("nome")));
                a.setCurso(c.getString(c.getColumnIndex("curso")));
                alunos.add(a);

            } while (c.moveToNext());
        }
        return alunos;
    }
    public List<Aluno> buscarTodos(){
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor c = null;
        try {
            //c = db.query("aluno", null, null, null, null, null, null);
            c = db.rawQuery("SELECT * FROM aluno", null);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //db.close();
        }
        if (c != null) {
            return paraLista(c);
        }
        return null;
    }
}
