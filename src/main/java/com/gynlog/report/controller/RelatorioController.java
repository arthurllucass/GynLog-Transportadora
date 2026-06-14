package com.gynlog.report.controller;

import com.gynlog.report.service.RelatorioService;

public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public void gerarRelatorioPorFiltro(String caminhoSalvar, String dataInicial, String dataFinal,
                                        String descricaoTipoDespesa, String placaVeiculo) throws Exception {

        relatorioService.gerarRelatorioPorFiltro(caminhoSalvar, dataInicial, dataFinal, placaVeiculo, descricaoTipoDespesa);
    }

    public void gerarRelatorioPorFiltro(String caminhoSalvar, String descricaoTipoDespesa, String placaVeiculo) throws Exception {
        relatorioService.gerarRelatorioPorFiltro(caminhoSalvar, descricaoTipoDespesa, placaVeiculo);
    }

    public void gerarVeiculosInativos(String caminhoSalvar) throws Exception {
        relatorioService.listarVeiculosInativos(caminhoSalvar);
    }
}
