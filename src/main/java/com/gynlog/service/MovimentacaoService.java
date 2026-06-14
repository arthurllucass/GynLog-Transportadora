package com.gynlog.service;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.repository.MovimentacaoRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class MovimentacaoService {

    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    //validar se o tipo de despesa e veiculo existe
    public void criar(Movimentacao movimentacao) {

        validarNullVazio(movimentacao);

        String dataString = movimentacao.getDataMovimentacao()
                .format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));

        validarData(dataString);

        validarValorMovimentacao(movimentacao.getValorMovimentacao());

        movimentacao.setId(movimentacaoRepository.gerarId());

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

    public Movimentacao atualizar(Movimentacao movimentacao) {

        movimentacaoRepository.buscarPorId(movimentacao.getId());

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

        if (!validarDataExiste(dataMovimentacao))
            throw new IllegalArgumentException("A data não existe!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataValidar = LocalDate.parse(dataMovimentacao, formatter);

        if (dataValidar.isAfter(
                LocalDate.now
                        (ZoneId.of
                                ("America/Sao_Paulo"))))
            throw new IllegalArgumentException("A data não pode ser depois que a atual");
    }

    private boolean validarDataExiste(String dataMovimentacao) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(dataMovimentacao, formatter);
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
