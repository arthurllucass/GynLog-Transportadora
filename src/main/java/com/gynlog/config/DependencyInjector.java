package com.gynlog.config;

import com.gynlog.controller.MovimentacaoController;
import com.gynlog.report.service.RelatorioService;
import com.gynlog.repository.MovimentacaoRepository;
import com.gynlog.repository.impl.MovimentacaoRepositoryImpl;
import com.gynlog.service.MovimentacaoService;

public class DependencyInjector {

    private static final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepositoryImpl();

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
