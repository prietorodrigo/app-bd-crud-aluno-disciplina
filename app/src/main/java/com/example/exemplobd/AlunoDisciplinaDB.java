package com.example.exemplobd;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlunoDisciplinaDB {
    private Banco banco;

    public AlunoDisciplinaDB(Banco b) {
        this.banco = b;
    }
    public void save(AlunoDisciplina alunodisciplina) {

        SQLiteDatabase db = banco.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("id_aluno", alunodisciplina.getId_aluno());
            values.put("id_disciplina", alunodisciplina.getId_disciplina());

            db.insert("alunodisciplina", "", values);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            //db.close();
        }

    }
    @SuppressLint("Range")
    private List<AlunoDisciplina> paraLista(Cursor c) {
        List<AlunoDisciplina> alunosdisciplinas = new ArrayList<>();
        if (c.moveToFirst()) {
            do{
                AlunoDisciplina ad = new AlunoDisciplina();
                ad.setAluno_nome(c.getString(c.getColumnIndex("aluno_nome")));
                ad.setDisciplina_nome(c.getString(c.getColumnIndex("disciplina_nome")));
                alunosdisciplinas.add(ad);

            } while (c.moveToNext());
        }
        return alunosdisciplinas;
    }

    public List<AlunoDisciplina> buscarTodos(){
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor c = null;
        try {
            //c = db.query("aluno", null, null, null, null, null, null);
            //c = db.rawQuery("SELECT * FROM alunodisciplina", null);
            c = db.rawQuery("SELECT aluno.nome as aluno_nome, disciplina.nome as disciplina_nome FROM aluno " +
                    "INNER JOIN alunodisciplina ON aluno.id = alunodisciplina.id_aluno " +
                    "INNER JOIN disciplina ON alunodisciplina.id_disciplina = disciplina.id;", null);
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
