package com.gynlog.report.service;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.model.enums.StatusVeiculo;
import com.gynlog.report.pdf.RelatorioPDF;
import com.gynlog.repository.MovimentacaoRepository;
import com.gynlog.repository.TipoDespesaRepository;
import com.gynlog.repository.VeiculoRepository;
import com.gynlog.repository.impl.TipoDespesaRepositoryImpl;
import com.gynlog.repository.impl.VeiculoRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioService {

    private TipoDespesaRepository tipoDespesaRepository;
    private VeiculoRepository veiculoRepository;
    private MovimentacaoRepository movimentacaoRepository;
    private RelatorioPDF relatorioPDF;

    public RelatorioService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.relatorioPDF = new RelatorioPDF();
        this.tipoDespesaRepository = new TipoDespesaRepositoryImpl();
        this.veiculoRepository = new VeiculoRepositoryImpl();
    }

    public void gerarRelatorioPorFiltro(String caminhoSalvar, String dataInicial, String dataFinal, String placa, String despesa) throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate inicio = LocalDate.parse(dataInicial, formatter);
        LocalDate fim = LocalDate.parse(dataFinal, formatter);

        if (inicio.isAfter(fim))
            throw new IllegalArgumentException("Data inicial não pode ser depois da data final!");

        List<Movimentacao> listaMovimentacoes = movimentacaoRepository.buscarTodasMovimentacoes();

        List<Movimentacao> listaFiltrada = listaMovimentacoes.stream()
                .filter(movimentacao -> !movimentacao.getDataMovimentacao().isBefore(inicio)
                        && !movimentacao.getDataMovimentacao().isAfter(fim))
                .toList();

        listaFiltrada = aplicarFiltrosPlacaEDespesa(listaFiltrada, placa, despesa);

        if (listaFiltrada.isEmpty())
            throw new IllegalArgumentException("Nenhuma movimentação encontrada para os filtros selecionados!");

        relatorioPDF.gerarRelatorioPorFiltro(caminhoSalvar, listaFiltrada, dataInicial, dataFinal, placa, despesa);
    }

    public void gerarRelatorioPorFiltro(String caminhoSalvar, String placa, String despesa) throws Exception {

        List<Movimentacao> listaMovimentacoes = movimentacaoRepository.buscarTodasMovimentacoes();

        List<Movimentacao> listaFiltrada = aplicarFiltrosPlacaEDespesa(listaMovimentacoes, placa, despesa);

        if (listaFiltrada.isEmpty())
            throw new IllegalArgumentException("Nenhuma movimentação encontrada para os filtros selecionados!");

        relatorioPDF.gerarRelatorioPorFiltro(caminhoSalvar, listaFiltrada, null, null, placa, despesa);
    }

    public void listarVeiculosInativos(String caminhoSalvar) throws Exception {

        List<Veiculo> listaVeiculosInativos = veiculoRepository.buscarPorStatus(StatusVeiculo.INATIVO);

        if (listaVeiculosInativos.isEmpty())
            throw new IllegalArgumentException("Nenhum veículo inativo encontrado!");

        relatorioPDF.listarVeiculosInativos(caminhoSalvar, listaVeiculosInativos);
    }

    private List<Movimentacao> aplicarFiltrosPlacaEDespesa(List<Movimentacao> listaFiltrada, String placaVeiculo, String despesa) {

        List<Movimentacao> resultadoLista = listaFiltrada;

        if (!placaVeiculo.equalsIgnoreCase("TODOS")) {
            resultadoLista = resultadoLista.stream()
                    .filter(movimentacao -> movimentacao.getVeiculo().getPlaca().equalsIgnoreCase(placaVeiculo))
                    .toList();
        }

        if (!despesa.equalsIgnoreCase("TODOS")) {
            resultadoLista = resultadoLista.stream()
                    .filter(movimentacao -> movimentacao.getTipoDespesa().getDescricao().equalsIgnoreCase(despesa))
                    .toList();
        }

        return resultadoLista;
    }


}
