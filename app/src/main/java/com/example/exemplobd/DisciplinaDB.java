package com.example.exemplobd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaDB {
    private Banco banco;
    public DisciplinaDB(Banco b) {
        this.banco = b;
    }

    public long save(Disciplina disciplina) {
        long id = disciplina.getId();
        SQLiteDatabase db = banco.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nome", disciplina.getNome());
            values.put("professor", disciplina.getProfessor());
            if (id==0) {
                //novo registro
                id = db.insert("disciplina", "", values);
            }
            else {
                //atualizar registro
                String _id = String.valueOf(disciplina.getId());
                String[] whereArgs = new String[]{_id};
                id = db.update("disciplina", values, "id=?", whereArgs);
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
    private List<Disciplina> paraLista(Cursor c) {
        List<Disciplina> disciplinas = new ArrayList<>();
        if (c.moveToFirst()) {
            do{
                Disciplina d = new Disciplina();
                d.setId(c.getInt(c.getColumnIndex("id")));
                d.setNome(c.getString(c.getColumnIndex("nome")));
                d.setProfessor(c.getString(c.getColumnIndex("professor")));
                disciplinas.add(d);

            } while (c.moveToNext());
        }
        return disciplinas;
    }
    public List<Disciplina> buscarTodos(){
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor c = null;
        try {
            //c = db.query("aluno", null, null, null, null, null, null);
            c = db.rawQuery("SELECT * FROM disciplina", null);

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
