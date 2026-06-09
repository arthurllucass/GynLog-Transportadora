package com.gynlog.config;

import com.gynlog.controller.MovimentacaoController;
import com.gynlog.repository.GeradorIdMovimentacao;
import com.gynlog.repository.MovimentacaoRepository;
import com.gynlog.repository.impl.MovimentacaoRepositoryImpl;
import com.gynlog.service.MovimentacaoService;

public class DependencyInjector {

    private static final MovimentacaoRepository movimentacaoRepository =
            new MovimentacaoRepositoryImpl();

    private static final GeradorIdMovimentacao geradorIdMovimentacao =
            new MovimentacaoRepositoryImpl();

    private static final MovimentacaoService movimentacaoService =
            new MovimentacaoService(
                    movimentacaoRepository,
                    geradorIdMovimentacao
            );

    private static final MovimentacaoController movimentacaoController =
            new MovimentacaoController(movimentacaoService);

    public static MovimentacaoController getMovimentacaoController() {
        return movimentacaoController;
    }
}
