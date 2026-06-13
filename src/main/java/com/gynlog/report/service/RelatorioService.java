package com.gynlog.report.service;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.report.pdf.RelatorioPDF;
import com.gynlog.repository.MovimentacaoRepository;
import com.gynlog.repository.TipoDespesaRepository;
import com.gynlog.repository.VeiculoRepository;
import com.gynlog.repository.impl.TipoDespesaRepositoryImpl;
import com.gynlog.repository.impl.VeiculoRepositoryImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

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

    public void somatorioGeralDespesasFrotaMes(String mes, String ano) throws Exception {

        if (!validarDataExiste(mes + "/" + ano, "MM/yyyy"))
            throw new IllegalArgumentException("A data não existe!");

        List<Movimentacao> listaFiltrada = movimentacaoRepository.buscarTodasMovimentacoes();

        listaFiltrada.stream()
                        .filter(m -> m.getDataMovimentacao().getMonth().equals(LocalDate.parse(mes)))
                        .map(Movimentacao::getDataMovimentacao)
                        .toList();

        if (listaFiltrada == null) throw new IllegalArgumentException("Nenhuma movimentação existente");

        relatorioPDF.gerarSomatorioDespesasMes(listaFiltrada, mes, ano);
    }

    public void totalGastoFrotaCombustivelMes() {
    }

    public void somatorioIpvaAno() {
    }

    public void listarVeiuclosInativos() {
    }

    public void multasPagasVeiculoAno() {
    }

    private boolean validarDataExiste(String dataMovimentacao, String formato) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(formato)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate data = LocalDate.parse(dataMovimentacao, dateTimeFormatter);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
