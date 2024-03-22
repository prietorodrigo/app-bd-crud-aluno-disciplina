package com.example.exemplobd;

public class AlunoDisciplina {
    private int id_aluno;
    private int id_disciplina;
    private String aluno_nome;
    private String disciplina_nome;
    private Aluno a;

    public String getAluno_nome() {
        return aluno_nome;
    }

    public void setAluno_nome(String aluno_nome) {
        this.aluno_nome = aluno_nome;
    }

    public String getDisciplina_nome() {
        return disciplina_nome;
    }

    public void setDisciplina_nome(String disciplina_nome) {
        this.disciplina_nome = disciplina_nome;
    }

    public AlunoDisciplina() {
        this.id_aluno = 0;
        this.id_disciplina = 0;
    }

    public AlunoDisciplina(int id_aluno, int id_disciplina) {
        this.id_aluno = id_aluno;
        this.id_disciplina = id_disciplina;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

}
