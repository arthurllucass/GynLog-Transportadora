package com.gynlog.repository;

import com.gynlog.model.entity.Movimentacao;

import java.util.List;

public interface MovimentacaoRepository {

    void criar (Movimentacao movimentacao);
    List<Movimentacao> buscarTodasMovimentacoes();
    Movimentacao buscarPorId(Long id) ;
    Movimentacao atualizar(Movimentacao movimentacao);
    void deletar(Movimentacao movimentacao);
    Long gerarId();
}
