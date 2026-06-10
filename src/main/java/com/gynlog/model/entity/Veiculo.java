package com.gynlog.model.entity;
import com.gynlog.model.enums.MarcaDeCarro;
import com.gynlog.model.enums.StatusVeiculo;

public class Veiculo {
    private int idVeiculo = 0;
    private String placa = "";
    MarcaDeCarro marca = null;
    private String modelo = "";
    private int anoDeFrabicacao = 0;
    private StatusVeiculo statusVeiculo = null;


    public Veiculo(){

    }
    public Veiculo(int idveiculo, String placa, MarcaDeCarro marca,  String modelo, int anoDeFrabicacao,StatusVeiculo statusVeiculo){
        this.idVeiculo = idveiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoDeFrabicacao = anoDeFrabicacao;
       
        this.statusVeiculo = statusVeiculo;
    }
    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public MarcaDeCarro getMarca() {
        return marca;
    }
    public void setMarca(MarcaDeCarro modelo) {
        this.marca = modelo;
    }
    public int getAnoDeFrabicacao() {
        return anoDeFrabicacao;
    }
    public void setAnoDeFrabicacao(int anoDeFrabicacao) {
        this.anoDeFrabicacao = anoDeFrabicacao;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }
    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }
    
    

}
