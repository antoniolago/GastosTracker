package com.example.gastostracker;

public class Cliente implements Serializable {

    private int id;
    private String nome;
    private String sexo;
    private String uf;

    public Cliente(int id, String nome, String sexo, String uf){
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.uf = uf;
    }

    public int getId(){ return this.id; }
    public String getNome(){ return this.nome; }
    public String getSexo(){ return this.sexo; }
    public boolean getVip(){ return this.vip; }
    public String getUf(){ return this.uf; }

    @Override
    public boolean equals(Object o){
        return this.id == ((Cliente)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}