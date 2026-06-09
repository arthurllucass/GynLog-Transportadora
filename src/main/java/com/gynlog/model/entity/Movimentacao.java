package com.gynlog.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Movimentacao {

    private Long id;
    private Veiculo veiculo;
    private TipoDespesa tipoDespesa;
    private String descricaoMovimentacao;
    private LocalDateTime dataMovimentacao;
    private Double valorMovimentacao;

    public Movimentacao() {
    }

    public Movimentacao(Long id, Veiculo veiculo, TipoDespesa tipoDespesa, String descricaoMovimentacao, LocalDateTime dataMovimentacao, Double valorMovimentacao) {
        this.id = id;
        this.veiculo = veiculo;
        this.tipoDespesa = tipoDespesa;
        this.descricaoMovimentacao = descricaoMovimentacao;
        this.dataMovimentacao = dataMovimentacao;
        this.valorMovimentacao = valorMovimentacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public String getDescricaoMovimentacao() {
        return descricaoMovimentacao;
    }

    public void setDescricaoMovimentacao(String descricaoMovimentacao) {
        this.descricaoMovimentacao = descricaoMovimentacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(Double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movimentacao that = (Movimentacao) o;
        return Objects.equals(id, that.id) && Objects.equals(veiculo, that.veiculo) && Objects.equals(tipoDespesa, that.tipoDespesa) && Objects.equals(descricaoMovimentacao, that.descricaoMovimentacao) && Objects.equals(dataMovimentacao, that.dataMovimentacao) && Objects.equals(valorMovimentacao, that.valorMovimentacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, veiculo, tipoDespesa, descricaoMovimentacao, dataMovimentacao, valorMovimentacao);
    }
}
