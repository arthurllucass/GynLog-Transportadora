package com.gynlog.report.service;

import com.gynlog.report.pdf.RelatorioPDF;
import com.gynlog.repository.MovimentacaoRepository;
import com.gynlog.repository.TipoDespesaRepository;
import com.gynlog.repository.VeiculoRepository;
import com.gynlog.repository.impl.TipoDespesaRepositoryImpl;
import com.gynlog.repository.impl.VeiculoRepositoryImpl;

public class RelatorioService {

    private TipoDespesaRepository tipoDespesaRepository = new TipoDespesaRepositoryImpl();
    private VeiculoRepository veiculoRepository = new VeiculoRepositoryImpl();
    private MovimentacaoRepository movimentacaoRepository;
    private RelatorioPDF relatorioPDF;

    public RelatorioService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.relatorioPDF = new RelatorioPDF();
    }

    public void gerarDespesasPorVeiculo() {
    }

    public void somatorioGeralDespesasFrotaMes() {
    }

    public void totalGastoFrotaCombustivelMes() {
    }

    public void somatorioIpvaAno() {
    }

    public void listarVeiuclosInativos() {
    }

    public void multasPagasVeiculoAno() {
    }
}
