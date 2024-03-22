package com.example.exemplobd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNome;
    private EditText edtProfessor;
    private Button btnSalvar;
    private Button btnBuscar;
    private ListView listView;
    //private AlunoDB alunodb;
    private Banco banco;
    List<Disciplina> dadosDisciplinas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNome = findViewById(R.id.nome);
        edtProfessor = findViewById(R.id.professor);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBuscar = findViewById(R.id.btnBuscar);
        listView = findViewById(R.id.listView);
        btnSalvar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        banco = new Banco(getApplicationContext());
        //db = new AlunoDB(getApplicationContext());
    }

    public void preencheListView(){
        DisciplinaDB disciplinaDB = new DisciplinaDB(banco);
        dadosDisciplinas = disciplinaDB.buscarTodos();
        String[] arrayDisciplinas = new String[dadosDisciplinas.size()];
        for (int i=0; i<dadosDisciplinas.size(); i++) {
            Disciplina disciplina = dadosDisciplinas.get(i);
            arrayDisciplinas[i] = disciplina.getId()+ "- " + disciplina.getNome()+ " - " + disciplina.getProfessor();
        }
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayDisciplinas);
        listView.setAdapter(adaptador1);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnSalvar) {
            //salvar as informacoes BD
            Disciplina d= new Disciplina();
            DisciplinaDB disciplinadb = disciplinadb = new DisciplinaDB(this.banco);
            d.setNome(edtNome.getText().toString());
            d.setProfessor(edtProfessor.getText().toString());
            disciplinadb.save(d);
            Toast.makeText(getApplicationContext(),"Disciplina cadastrada com sucesso", Toast.LENGTH_LONG).show();
        }
        else if (view.getId()==R.id.btnBuscar) {
            //salvar as informacoes no BD
            DisciplinaDB disciplinadb = disciplinadb = new DisciplinaDB(this.banco);
            List<Disciplina> disciplinas = disciplinadb.buscarTodos();
            for(int i=0; i< disciplinas.size(); i++) {
                System.out.println(disciplinas.get(i).getId());
                System.out.println(disciplinas.get(i).getNome());
                System.out.println(disciplinas.get(i).getProfessor());
            }
            preencheListView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.alunos) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }
        else if (item.getItemId()==R.id.cursando) {
            Intent i = new Intent(getApplicationContext(), MainActivity3.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}