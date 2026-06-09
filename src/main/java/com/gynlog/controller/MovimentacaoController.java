package com.gynlog.controller;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.service.MovimentacaoService;

import java.util.List;

public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    public void criar (Long idVeiculo, Long idTipoDespesa,
                       String descricaoMovimentacao, String dataMovimentacao, Double valorMovimentacao) {

//        Movimentacao movimentacao = new Movimentacao(null, )
//
//        movimentacaoService.criar();
    }

    public List<Movimentacao> buscarTodasMovimentacoes() {
        return movimentacaoService.buscarTodasMovimentacoes();
    }

    public Movimentacao buscarPorId(Long id) {
        return movimentacaoService.buscarPorId(id);
    }

    public Movimentacao atualizar(Movimentacao movimentacao) {
        return null;
    }

    public void deletar(Long id) {
        movimentacaoService.deletar(id);
    }

    private void validarPontoVirgula(String texto, String campo) {
        if (texto != null && texto.contains(";"))
            throw new IllegalArgumentException ("Não pode conter ponto e virgula (;) no campo " + campo);
    }
}
