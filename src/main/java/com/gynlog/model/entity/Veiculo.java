package com.gynlog.model.entity;

import com.gynlog.model.enums.StatusVeiculo;

import java.time.LocalDate;
import java.util.Objects;

public class Veiculo {

    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private LocalDate anoFabricacao;
    private StatusVeiculo statusVeiculo;

    public Veiculo() {
    }

    public Veiculo(Long id, String placa, String marca, String modelo, LocalDate anoFabricacao, StatusVeiculo statusVeiculo) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.statusVeiculo = statusVeiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        modelo = modelo;
    }

    public LocalDate getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(LocalDate anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public StatusVeiculo getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(placa);
    }
}
