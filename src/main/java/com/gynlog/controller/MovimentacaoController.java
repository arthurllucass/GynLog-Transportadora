package com.gynlog.controller;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.service.MovimentacaoService;

import java.time.LocalDate;
import java.util.List;

public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;
    private TipoDespesaController tipoDespesaController;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    public void criar (Long idVeiculo, Long idTipoDespesa,
                       String descricaoMovimentacao, String dataMovimentacao, Double valorMovimentacao) throws Exception {

        validarPontoVirgula(descricaoMovimentacao, "Descrição");
        validarPontoVirgula(dataMovimentacao, "Data");

        TipoDespesa tipoDespesa = tipoDespesaController.buscarPorId(idTipoDespesa.intValue());
        Veiculo veiculo = new Veiculo();

       Movimentacao movimentacao = new Movimentacao(null, veiculo, tipoDespesa, descricaoMovimentacao,
       LocalDate.parse(dataMovimentacao), valorMovimentacao);

       movimentacaoService.criar(movimentacao);
    }

    public List<Movimentacao> buscarTodasMovimentacoes() {
        return movimentacaoService.buscarTodasMovimentacoes();
    }

    public Movimentacao buscarPorId(Long id) {
        return movimentacaoService.buscarPorId(id);
    }

    public Movimentacao atualizar(Long idMovimentacao, Long idVeiculo, Long idTipoDespesa,
                                  String descricaoMovimentacao, String dataMovimentacao, Double valorMovimentacao) throws Exception {

        validarPontoVirgula(descricaoMovimentacao, "Descrição");
        validarPontoVirgula(dataMovimentacao, "Data");

        TipoDespesa tipoDespesa = tipoDespesaController.buscarPorId(idTipoDespesa.intValue());
        Veiculo veiculo = new Veiculo();

        Movimentacao movimentacao = new Movimentacao(idMovimentacao, veiculo, tipoDespesa,
                descricaoMovimentacao, LocalDate.parse(dataMovimentacao), valorMovimentacao);

        return movimentacaoService.atualizar(movimentacao);
    }

    public void deletar(Long id) {
        movimentacaoService.deletar(id);
    }

    private void validarPontoVirgula(String texto, String campo) {
        if (texto != null && texto.contains(";"))
            throw new IllegalArgumentException ("Não pode conter ponto e virgula (;) no campo " + campo);
    }
}
