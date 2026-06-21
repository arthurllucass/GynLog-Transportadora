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

import javax.swing.*;
import java.io.File;
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

    public String pegarCaminho(String nomeArquivo) throws Exception {

        JFileChooser salvarArquivo = new JFileChooser();
        salvarArquivo.setDialogTitle("Salvar relatório...");
        salvarArquivo.setSelectedFile(new java.io.File(nomeArquivo + ".pdf"));

        int opcao = salvarArquivo.showDialog(null, "Salvar");

        if (opcao == javax.swing.JFileChooser.APPROVE_OPTION) {

            File arquivo = salvarArquivo.getSelectedFile();
            String caminhoParaSalvar = arquivo.getAbsolutePath();

            JOptionPane.showMessageDialog(null, "O arquivo será salvo em: " + caminhoParaSalvar);

            return caminhoParaSalvar;

        } else {
            throw new Exception("Operação cancelada pelo usuário.");
        }
    }

    public void gerarRelatorioPorFiltro(String dataInicial, String dataFinal, String despesa, String placa) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate inicio = LocalDate.parse(dataInicial, formatter);
        LocalDate fim = LocalDate.parse(dataFinal, formatter);

        String nomeArquivo = "relatorio";

        if (inicio.isAfter(fim))
            throw new IllegalArgumentException("Data inicial não pode ser depois da data final!");

        List<Movimentacao> listaMovimentacoes = movimentacaoRepository.buscarTodasMovimentacoes();

        List<Movimentacao> listaFiltrada = listaMovimentacoes.stream()
                .filter(movimentacao -> !movimentacao.getDataMovimentacao().isBefore(inicio)
                        && !movimentacao.getDataMovimentacao().isAfter(fim))
                .toList();

        listaFiltrada = aplicarFiltrosPlacaEDespesa(listaFiltrada, despesa, placa);

        if(!despesa.equalsIgnoreCase("TODOS")){nomeArquivo+=("_"+despesa);}
        if(!placa.equalsIgnoreCase("TODOS")){nomeArquivo+=("_"+placa);}

        if (listaFiltrada.isEmpty())
            throw new IllegalArgumentException("Nenhuma movimentação encontrada para os filtros selecionados!");

        relatorioPDF.gerarRelatorioPorFiltro(pegarCaminho(nomeArquivo), listaFiltrada);

    }

    public void gerarRelatorioPorFiltro(String despesa, String placa) throws Exception {

        String nomeArquivo = "relatorio_total";

        List<Movimentacao> listaMovimentacoes = movimentacaoRepository.buscarTodasMovimentacoes();

        List<Movimentacao> listaFiltrada = aplicarFiltrosPlacaEDespesa(listaMovimentacoes, despesa, placa);

        if(!despesa.equalsIgnoreCase("TODOS")){nomeArquivo+=("_"+despesa);}
        if(!placa.equalsIgnoreCase("TODOS")){nomeArquivo+=("_"+placa);}

        if (listaFiltrada.isEmpty())
            throw new IllegalArgumentException("Nenhuma movimentação encontrada para os filtros selecionados!");

        relatorioPDF.gerarRelatorioPorFiltro(pegarCaminho(nomeArquivo), listaFiltrada);
    }

    public void listarVeiculosInativos() throws Exception {

        List<Veiculo> listaVeiculosInativos = veiculoRepository.buscarPorStatus(StatusVeiculo.INATIVO);

        if (listaVeiculosInativos.isEmpty())
            throw new IllegalArgumentException("Nenhum veículo inativo encontrado!");

        relatorioPDF.listarVeiculosInativos(pegarCaminho("veiculos_inativos"), listaVeiculosInativos);
    }

    private List<Movimentacao> aplicarFiltrosPlacaEDespesa(List<Movimentacao> listaFiltrada, String despesa, String placaVeiculo) {

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
