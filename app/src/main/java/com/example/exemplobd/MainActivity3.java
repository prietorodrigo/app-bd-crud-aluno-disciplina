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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    private Spinner spIdAluno;
    private Spinner spIdDisciplina;
    private Button btnSalvar;
    private Button btnBuscar;
    private ListView listView;

    //private AlunoDB alunodb;
    private Banco banco;

    List<Aluno> dadosAlunos = new ArrayList<>();
    List<Disciplina> dadosDisciplinas = new ArrayList<>();
    List<AlunoDisciplina> dadosAlunosDisciplinas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        spIdAluno = findViewById(R.id.spIdAluno);
        spIdDisciplina = findViewById(R.id.spIdDisciplina);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBuscar = findViewById(R.id.btnBuscar);
        listView = findViewById(R.id.listView);
        btnSalvar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        banco = new Banco(getApplicationContext());
        //db = new AlunoDB(getApplicationContext());

        preencheSpinners();
    }

    public void preencheSpinners(){
        AlunoDB alunoDB = new AlunoDB(banco);
        dadosAlunos = alunoDB.buscarTodos();
        String[] arrayAlunos = new String[dadosAlunos.size()];
        for (int i=0; i<dadosAlunos.size(); i++) {
            Aluno aluno = dadosAlunos.get(i);
            arrayAlunos[i] = aluno.getId()+ "- " + aluno.getNome();
        }
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayAlunos);
        spIdAluno.setAdapter(adaptador1);

        DisciplinaDB disciplinaDB = new DisciplinaDB(banco);
        dadosDisciplinas = disciplinaDB.buscarTodos();
        String[] arrayDisciplinas = new String[dadosDisciplinas.size()];
        for (int i=0; i<dadosDisciplinas.size(); i++) {
            Disciplina disciplina = dadosDisciplinas.get(i);
            arrayDisciplinas[i] = disciplina.getId()+ "- " + disciplina.getNome();
        }
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayDisciplinas);
        spIdDisciplina.setAdapter(adaptador2);
    }

    public void preencheListView(){
        AlunoDisciplinaDB alunodisciplinaDB = new AlunoDisciplinaDB(banco);
        dadosAlunosDisciplinas = alunodisciplinaDB.buscarTodos();
        String[] arrayAlunosDisciplinas = new String[dadosAlunosDisciplinas.size()];
        for (int i=0; i<dadosAlunosDisciplinas.size(); i++) {
            AlunoDisciplina alunodisciplina = dadosAlunosDisciplinas.get(i);
            arrayAlunosDisciplinas[i] = alunodisciplina.getAluno_nome()+ " - " + alunodisciplina.getDisciplina_nome();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayAlunosDisciplinas);
        listView.setAdapter(adaptador);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnSalvar) {
            //salvar as informacoes BD
            AlunoDisciplina a= new AlunoDisciplina();
            AlunoDisciplinaDB alunodisciplinadb = alunodisciplinadb = new AlunoDisciplinaDB(this.banco);
            a.setId_aluno((int) spIdAluno.getSelectedItemId()+1);
            a.setId_disciplina((int) spIdDisciplina.getSelectedItemId()+1);
            alunodisciplinadb.save(a);
            Toast.makeText(getApplicationContext(),"Cadastro feito com sucesso", Toast.LENGTH_LONG).show();
        }
        else if (view.getId()==R.id.btnBuscar) {
            //salvar as informacoes no BD
            AlunoDisciplinaDB alunodisciplinadb = alunodisciplinadb = new AlunoDisciplinaDB(this.banco);
            List<AlunoDisciplina> alunodisciplinas = alunodisciplinadb.buscarTodos();
            for(int i=0; i< alunodisciplinas.size(); i++) {
                System.out.println(alunodisciplinas.get(i).getAluno_nome());
                System.out.println(alunodisciplinas.get(i).getDisciplina_nome());
            }
            preencheListView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.alunos) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }
        else if (item.getItemId()==R.id.disciplinas) {
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}