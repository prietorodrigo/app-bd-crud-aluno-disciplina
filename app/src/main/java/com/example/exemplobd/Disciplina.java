package com.example.exemplobd;

public class Disciplina {
    private int id;
    private String nome;
    private String professor;
    public Disciplina() {
        this.id = 0;
        this.nome = "";
        this.professor = "";
    }

    public Disciplina(String nome, String professor) {
        this.id = 0;
        this.nome = nome;
        this.professor = professor;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
