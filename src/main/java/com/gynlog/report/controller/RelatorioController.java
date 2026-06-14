package com.gynlog.report.controller;

import com.gynlog.report.service.RelatorioService;

public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public void gerarRelatorioPorFiltro(String dataInicial, String dataFinal,
                                        String descricaoTipoDespesa, String placaVeiculo) throws Exception {

        relatorioService.gerarRelatorioPorFiltro(dataInicial, dataFinal,descricaoTipoDespesa, placaVeiculo);
    }

    public void gerarRelatorioPorFiltro(String descricaoTipoDespesa, String placaVeiculo) throws Exception {
        relatorioService.gerarRelatorioPorFiltro(descricaoTipoDespesa, placaVeiculo);
    }

    public void gerarVeiculosInativos() throws Exception {
        relatorioService.listarVeiculosInativos();
    }
}
