package com.gynlog.repository;


import com.gynlog.model.entity.TipoDespesa;

import java.util.List;

public interface TipoDespesaRepository {

    void salvar(TipoDespesa TipoDespesa) throws Exception;

    List<TipoDespesa> listaDeTiposDespesas() throws Exception;

    TipoDespesa buscarPorId(int idTipoDespesa) throws Exception;
    TipoDespesa buscarPorDescricao(String descricao) throws Exception;
    void atualizar(TipoDespesa TipoDespesa) throws Exception;
    void remover(int idTipoDespesa) throws Exception;

}
