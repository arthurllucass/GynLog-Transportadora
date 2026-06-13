package com.gynlog.report.controller;

import com.gynlog.report.service.RelatorioService;

public class RelatorioController {

    private RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    public void gerarDespesasPorVeiculo() {
        relatorioService.gerarDespesasPorVeiculo();
    }

    public void somatorioGeralDespesasFrotaMes(String mes, String ano) throws Exception {
        relatorioService.somatorioGeralDespesasFrotaMes(mes, ano);
    }

    public void totalGastoFrotaCombustivelMes() {
        relatorioService.totalGastoFrotaCombustivelMes();
    }

    public void somatorioIpvaAno() {
        relatorioService.somatorioIpvaAno();
    }

    public void listarVeiuclosInativos() {
        relatorioService.listarVeiuclosInativos();
    }

    public void multasPagasVeiculoAno() {
    }
}
