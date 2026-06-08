package com.gynlog.model.entity;
import com.gynlog.model.enums.ModeloDeCarro;
import com.gynlog.model.enums.StatusVeiculo;

public class Veiculo {
    private int idVeiculo = 0;
    private String placa = "";
    private String marca = "";
    private int anoDeFrabicacao = 0;
    private ModeloDeCarro modelo = null;
    private StatusVeiculo statusVeiculo = null;


    public Veiculo(){

    }
    public Veiculo(int idveiculo, String placa, String marca, int anoDeFrabicacao, ModeloDeCarro modelo, StatusVeiculo statusVeiculo){
        this.idVeiculo = idveiculo;
        this.placa = placa;
        this.marca = marca;
        this.anoDeFrabicacao = anoDeFrabicacao;
        this.modelo = modelo;
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
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public int getAnoDeFrabicacao() {
        return anoDeFrabicacao;
    }
    public void setAnoDeFrabicacao(int anoDeFrabicacao) {
        this.anoDeFrabicacao = anoDeFrabicacao;
    }
    public ModeloDeCarro getModelo() {
        return modelo;
    }
    public void setModelo(ModeloDeCarro modelo) {
        this.modelo = modelo;
    }
    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }
    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }
    
    

}
