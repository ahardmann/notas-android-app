package com.notas.faculdade.minhasnotas.domain;

public class Materia {

    private Long id;
    private String disciplina;
    private String professor;
    private int semestre;
    private int faltas;
    private int carga_hr;

    public Materia() {}

    public Materia(Long id, int faltas, int semestre, int carga_hr, String professor, String disciplina) {
        this.id = id;
        this.faltas = faltas;
        this.semestre = semestre;
        this.carga_hr = carga_hr;
        this.professor = professor;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCarga_hr() {
        return carga_hr;
    }

    public void setCarga_hr(int carga_hr) {
        this.carga_hr = carga_hr;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}
