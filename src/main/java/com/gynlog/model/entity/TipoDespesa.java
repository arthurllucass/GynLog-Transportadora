package com.gynlog.model.entity;

/**
 *
 * @author Augusto
 */
public class TipoDespesa {
    //Atributos
    private int idTipoDespesa = 0;
    private String descricao = "";
    //Metodos
    public TipoDespesa() {
    }
    public TipoDespesa(int idTipoDespesa, String descricao) {
        this.idTipoDespesa = idTipoDespesa;
        this.descricao = descricao;
    }
    public int getIdTipoDespesa() {
        return idTipoDespesa;
    }

    public void setIdTipoDespesa(int idTipoDespesa) {
        this.idTipoDespesa = idTipoDespesa;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}


