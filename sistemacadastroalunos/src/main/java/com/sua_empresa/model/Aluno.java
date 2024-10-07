package com.sua_empresa.model;

public class Aluno {
    private int id;
    private String nome;
    private int idade;
    private String curso;
    private String matricula;
    private int cursoId;
    private double notaProva1; // Nota Prova 1
    private double notaProva2; // Nota Prova 2

    // Construtor
    public Aluno(int id, String nome, int idade, String curso, String matricula, int cursoId) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
        this.matricula = matricula;
        this.cursoId = cursoId;
        this.notaProva1 = 0.0; // Inicializa Prova 1
        this.notaProva2 = 0.0; // Inicializa Prova 2
    }

    // Getters e Setters
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public double getNotaProva1() {
        return notaProva1;
    }

    public void setNotaProva1(double notaProva1) {
        this.notaProva1 = notaProva1;
    }

    public double getNotaProva2() {
        return notaProva2;
    }

    public void setNotaProva2(double notaProva2) {
        this.notaProva2 = notaProva2;
    }
}
