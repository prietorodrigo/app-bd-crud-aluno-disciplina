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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNome;
    private EditText edtCurso;
    private Button btnSalvar;
    private Button btnBuscar;
    private ListView listView;
    //private AlunoDB alunodb;
    private Banco banco;
    List<Aluno> dadosAlunos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.nome);
        edtCurso = findViewById(R.id.curso);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnBuscar = findViewById(R.id.btnBuscar);
        listView = findViewById(R.id.listView);
        btnSalvar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        banco = new Banco(getApplicationContext());
        //db = new AlunoDB(getApplicationContext());
    }

    public void preencheListView(){
        AlunoDB alunoDB = new AlunoDB(banco);
        dadosAlunos = alunoDB.buscarTodos();
        String[] arrayAlunos = new String[dadosAlunos.size()];
        for (int i=0; i<dadosAlunos.size(); i++) {
            Aluno aluno = dadosAlunos.get(i);
            arrayAlunos[i] = aluno.getId()+ "- " + aluno.getNome()+ " - " + aluno.getCurso();
        }
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayAlunos);
        listView.setAdapter(adaptador1);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnSalvar) {
            //salvar as informacoes BD
            Aluno a= new Aluno();
            AlunoDB alunodb = alunodb = new AlunoDB(this.banco);
            a.setNome(edtNome.getText().toString());
            a.setCurso(edtCurso.getText().toString());
            alunodb.save(a);
            Toast.makeText(getApplicationContext(),"Aluno cadastrado com sucesso", Toast.LENGTH_LONG).show();
        }
        else if (view.getId()==R.id.btnBuscar) {
            //salvar as informacoes no BD
            AlunoDB alunodb = alunodb = new AlunoDB(this.banco);
            List<Aluno> alunos = alunodb.buscarTodos();
            for(int i=0; i< alunos.size(); i++) {
                System.out.println(alunos.get(i).getId());
                System.out.println(alunos.get(i).getNome());
                System.out.println(alunos.get(i).getCurso());
            }
            preencheListView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.disciplinas) {
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
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