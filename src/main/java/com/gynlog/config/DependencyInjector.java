package com.gynlog.config;

import com.gynlog.controller.MovimentacaoController;
import com.gynlog.report.service.RelatorioService;
import com.gynlog.repository.MovimentacaoRepository;
import com.gynlog.repository.TipoDespesaRepository;
import com.gynlog.repository.VeiculoRepository;
import com.gynlog.repository.impl.MovimentacaoRepositoryImpl;
import com.gynlog.repository.impl.TipoDespesaRepositoryImpl;
import com.gynlog.repository.impl.VeiculoRepositoryImpl;
import com.gynlog.service.MovimentacaoService;

public class DependencyInjector {

    private static final VeiculoRepository veiculoRepository =
            new VeiculoRepositoryImpl();

    private static final TipoDespesaRepository tipoDespesaRepository =
            new TipoDespesaRepositoryImpl();

    private static final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepositoryImpl(veiculoRepository, tipoDespesaRepository);

    private static final MovimentacaoService movimentacaoService =
            new MovimentacaoService(movimentacaoRepository);

    private static final MovimentacaoController movimentacaoController =
            new MovimentacaoController(movimentacaoService);

    private static final RelatorioService relatorioService =
            new RelatorioService(movimentacaoRepository);

    public static MovimentacaoController getMovimentacaoController() {
        return movimentacaoController;
    }
}
