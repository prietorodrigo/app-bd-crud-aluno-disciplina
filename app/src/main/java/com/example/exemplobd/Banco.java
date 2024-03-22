package com.example.exemplobd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco extends SQLiteOpenHelper {
    private static final String TAG = "";
    private static final String NOME_BANCO = "cadastro.sqlite";
    private static final int VERSAO = 1;
    public Banco (Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Criando as tabelas");
        sqLiteDatabase.execSQL("create table if not exists aluno (id integer primary key autoincrement," +
                "nome text, curso text);");
        sqLiteDatabase.execSQL("create table if not exists disciplina (id integer primary key autoincrement," +
                "nome text, professor text);");
        sqLiteDatabase.execSQL("create table if not exists alunodisciplina(id_aluno integer, id_disciplina integer," +
                "primary key(id_aluno, id_disciplina)," +
                "foreign key(id_aluno) references aluno(id)," +
                "foreign key(id_disciplina) references disciplina(id));");
        Log.d(TAG, "Tabelas criadas");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //nao usaremos
    }
}
