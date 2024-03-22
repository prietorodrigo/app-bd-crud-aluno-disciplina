package com.example.exemplobd;

public class Aluno {
    private int id;
    private String nome;
    private String curso;

    public Aluno() {
        this.id = 0;
        this.nome = "";
        this.curso = "";
    }

    public Aluno(String nome, String curso) {
        this.id = 0;
        this.nome = nome;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
