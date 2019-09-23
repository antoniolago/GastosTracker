package com.example.gastostracker;

import java.util.Date;

public class Gasto {

    private int id;
    private String descricao;
    private Double valor;
    private Date data;

    public Gasto(int id, String descricao, Double valor, Date data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
    public Gasto(String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }
    public int getId(){ return this.id; }
    public String getDescricao(){ return this.descricao; }
    public Double getValor(){ return this.valor; }
    public Date getData(){ return this.data; }

}