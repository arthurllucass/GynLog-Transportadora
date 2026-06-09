package com.gynlog.service;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.repository.GeradorIdMovimentacao;
import com.gynlog.repository.MovimentacaoRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;
    private GeradorIdMovimentacao geradorIdMovimentacao;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, GeradorIdMovimentacao geradorIdMovimentacao) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.geradorIdMovimentacao = geradorIdMovimentacao;
    }


    //validar se o tipo de despesa e veiculo existe
    public void criar(Movimentacao movimentacao) {

        validarNullVazio(movimentacao);

        validarDataExiste(String.valueOf(movimentacao.getDataMovimentacao()));

        validarData(String.valueOf(movimentacao.getDataMovimentacao()));

        validarValorMovimentacao(movimentacao.getValorMovimentacao());

        movimentacao.setId(geradorIdMovimentacao.gerarId());

        movimentacaoRepository.criar(movimentacao);
    }

    public List<Movimentacao> buscarTodasMovimentacoes() {
        return movimentacaoRepository.buscarTodasMovimentacoes();
    }

    public Movimentacao buscarPorId(Long id) {

        Movimentacao movimentacaoBuscada = movimentacaoRepository.buscarPorId(id);

        if (movimentacaoBuscada == null) throw new RuntimeException("Movimentação não encontrada!");

        return movimentacaoBuscada;
    }

    //VoltarAQUI
    public Movimentacao atualizar(Movimentacao movimentacao) {
        return movimentacaoRepository.atualizar(movimentacao);
    }

    public void deletar(Long id) {

        Movimentacao movimentacao = buscarPorId(id);

        movimentacaoRepository.deletar(movimentacao);
    }

    private void validarNullVazio(Movimentacao movimentacao) {

        if (movimentacao == null)
            throw new NullPointerException("Movimentação vazia ou nula!");

        if (movimentacao.getVeiculo() == null)
            throw new NullPointerException("Veículo cadastrado não pode ser vazio ou nulo!");

        if (movimentacao.getTipoDespesa() == null)
            throw new NullPointerException("Tipo de despesa cadastrado não pode ser vazio ou nulo!");

        if (movimentacao.getDescricaoMovimentacao() == null ||
                movimentacao.getDescricaoMovimentacao().trim().isEmpty())
            throw new IllegalArgumentException("Descrição vazia ou nula!");

        if (movimentacao.getDataMovimentacao() == null)
            throw new NullPointerException("Data da movimentação vazia ou nula!");

        if (movimentacao.getValorMovimentacao() == null)
            throw new NullPointerException("Valor da movimentação vazio ou nulo!");
    }

    private void validarData(String dataMovimentacao) {

        if (dataMovimentacao.length() != 10)
            throw new IllegalArgumentException("Data inválida");

        LocalDateTime dataValidar = LocalDateTime.parse(dataMovimentacao);

        if (dataValidar.isAfter(
                LocalDateTime.now(
                        ZoneId.of(
                                "America/Sao_Paulo"))))

            throw new IllegalArgumentException("A data não pode ser depois que a atual");

    }

    private boolean validarDataExiste(String dataMovimentacao) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(String.valueOf(sdf))
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDateTime data = LocalDateTime.parse(dataMovimentacao, dateTimeFormatter);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void validarValorMovimentacao(Double valorMovimentacao) {

        if (valorMovimentacao <= 0)
            throw new IllegalArgumentException("Valor inválido!");

        if (valorMovimentacao > Double.MAX_VALUE)
            throw new IllegalArgumentException("Valor não permitido!");
    }

}
