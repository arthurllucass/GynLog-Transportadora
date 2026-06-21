package com.gynlog.controller;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.service.MovimentacaoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MovimentacaoController {

    private MovimentacaoService movimentacaoService;
    private TipoDespesaController tipoDespesaController;
    private VeiculoController veiculoController;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
        this.veiculoController = new VeiculoController();
        this.tipoDespesaController = new TipoDespesaController();
    }

    public void criar (Long idVeiculo, Long idTipoDespesa,
                       String descricaoMovimentacao, String dataMovimentacao, Double valorMovimentacao) throws Exception {

        validarPontoVirgula(descricaoMovimentacao, "Descrição");
        validarPontoVirgula(dataMovimentacao, "Data");

        Veiculo veiculo = veiculoController.buscarPorId(idVeiculo.intValue());
        TipoDespesa tipoDespesa = tipoDespesaController.buscarPorId(idTipoDespesa.intValue());

       Movimentacao movimentacao = new Movimentacao(null, veiculo, tipoDespesa, descricaoMovimentacao,
       LocalDate.parse(dataMovimentacao, formatter), valorMovimentacao);

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

        Veiculo veiculo = veiculoController.buscarPorId(idVeiculo.intValue());
        TipoDespesa tipoDespesa = tipoDespesaController.buscarPorId(idTipoDespesa.intValue());

        Movimentacao movimentacao = new Movimentacao(idMovimentacao, veiculo, tipoDespesa,
                descricaoMovimentacao, LocalDate.parse(dataMovimentacao,formatter), valorMovimentacao);

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
