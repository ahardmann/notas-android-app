package com.notas.faculdade.minhasnotas.domain;

public class Notas {
    private Long id;
    private double notaQt1;
    private double notaQl1;
    private double notaQt2;
    private double notaQl2;
    private double notaQt3;
    private double notaQl3;
    private double media1;
    private double media2;
    private double media3;
    private double mediaFinal;
    private Long materiaId;

    public Notas() {}

    public Notas(Long materiaId, double mediaFinal, double media3, double media2, double media1
            , double notaQl3, double notaQt3, double notaQl2, double notaQt2, double notaQl1, double notaQt1, Long id) {
        this.materiaId = materiaId;
        this.mediaFinal = mediaFinal;
        this.media3 = media3;
        this.media2 = media2;
        this.media1 = media1;
        this.notaQl3 = notaQl3;
        this.notaQt3 = notaQt3;
        this.notaQl2 = notaQl2;
        this.notaQt2 = notaQt2;
        this.notaQl1 = notaQl1;
        this.notaQt1 = notaQt1;
        this.id = id;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public double getMedia3() {
        return media3;
    }

    public void setMedia3(double media3) {
        this.media3 = media3;
    }

    public double getMedia2() {
        return media2;
    }

    public void setMedia2(double media2) {
        this.media2 = media2;
    }

    public double getMedia1() {
        return media1;
    }

    public void setMedia1(double media1) {
        this.media1 = media1;
    }

    public double getNotaQl3() {
        return notaQl3;
    }

    public void setNotaQl3(double notaQl3) {
        this.notaQl3 = notaQl3;
    }

    public double getNotaQt3() {
        return notaQt3;
    }

    public void setNotaQt3(double notaQt3) {
        this.notaQt3 = notaQt3;
    }

    public double getNotaQl2() {
        return notaQl2;
    }

    public void setNotaQl2(double notaQl2) {
        this.notaQl2 = notaQl2;
    }

    public double getNotaQt2() {
        return notaQt2;
    }

    public void setNotaQt2(double notaQt2) {
        this.notaQt2 = notaQt2;
    }

    public double getNotaQl1() {
        return notaQl1;
    }

    public void setNotaQl1(double notaQl1) {
        this.notaQl1 = notaQl1;
    }

    public double getNotaQt1() {
        return notaQt1;
    }

    public void setNotaQt1(double notaQt1) {
        this.notaQt1 = notaQt1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
