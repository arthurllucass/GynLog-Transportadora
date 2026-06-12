package com.gynlog.repository.impl;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.repository.GeradorIdTxt;
import com.gynlog.repository.MovimentacaoRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoRepositoryImpl extends GeradorIdTxt implements MovimentacaoRepository {

    private static final String CAMINHO = "database";
    private static final String ARQUIVO = "Movimentacoes.txt";
    private static final String GERADOR_ID = "Id_Movimentacoes.txt";

    @Override
    public void criar(Movimentacao movimentacao) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getArquivo(ARQUIVO), true))) {

            String dadosEscrita = converterMovimentacaoParaLinha(movimentacao);

            bufferedWriter.write(dadosEscrita);

        }  catch (IOException erro) {
            throw new RuntimeException("Erro ao criar uma movimentação: " + erro.getMessage());
        }
    }

    @Override
    public List<Movimentacao> buscarTodasMovimentacoes() {

        List<Movimentacao> listaMovimentacoes = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getArquivo(ARQUIVO)))) {

            String linhaDoArquivo = bufferedReader.readLine();

            while (linhaDoArquivo != null) {

                Object camposBancoDeDados[] = linhaDoArquivo.split(";");

                Long id = (Long) camposBancoDeDados[0];
                Veiculo veiculo = (Veiculo) camposBancoDeDados[1];
                TipoDespesa tipoDespesa = (TipoDespesa) camposBancoDeDados[2];
                String descricaoMovimentacao = camposBancoDeDados[3].toString();
                LocalDate dataMovimentacao = (LocalDate) camposBancoDeDados[4];
                Double valorMovimentacao = (Double) camposBancoDeDados[5];

                listaMovimentacoes.add(
                        new Movimentacao(
                                id,
                                veiculo,
                                tipoDespesa,
                                descricaoMovimentacao,
                                dataMovimentacao,
                                valorMovimentacao
                        )
                );

                linhaDoArquivo = bufferedReader.readLine();
            }

            return listaMovimentacoes;

        } catch (IOException erro) {
            throw new RuntimeException("Erro ao buscar todas as movimentações: " + erro.getMessage());
        }
    }

    @Override
    public Movimentacao buscarPorId(Long id) {

        List<Movimentacao> listaMovimentacoes = buscarTodasMovimentacoes();

        Movimentacao movimentacaoBuscada = buscaBinaria(listaMovimentacoes, id);

        return movimentacaoBuscada;
    }

    @Override
    public Movimentacao atualizar(Movimentacao movimentacaoAtualizada) {

        List<Movimentacao> listaMovimentacoes = buscarTodasMovimentacoes();

        for (int i = 0; i < listaMovimentacoes.size(); i++) {

            Movimentacao movimentacao = listaMovimentacoes.get(i);

            if (movimentacao.getId().equals(movimentacaoAtualizada.getId())) {

                listaMovimentacoes.set(i, movimentacaoAtualizada);

                break;
            }
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getArquivo(ARQUIVO)))) {

            for (Movimentacao movimentacao : listaMovimentacoes) {

                String dadosEscrita = converterMovimentacaoParaLinha(movimentacao);

                bufferedWriter.write(dadosEscrita);

                bufferedWriter.newLine();
            }

        } catch (IOException erro) {
            throw new RuntimeException("Erro ao atualizar movimentação: " + erro.getMessage());
        }

        return movimentacaoAtualizada;
    }

    @Override
    public void deletar(Movimentacao movimentacao) {

        List<Movimentacao> listaMovimentacoes = buscarTodasMovimentacoes();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getArquivo(ARQUIVO)))) {

            for (Movimentacao movimentacoes : listaMovimentacoes) {

                if (!movimentacoes.getId().equals(movimentacao.getId())) {

                    String dadosEscrita = converterMovimentacaoParaLinha(movimentacoes);

                    bufferedWriter.write(dadosEscrita);

                    bufferedWriter.newLine();
                } 
            }
        } catch (IOException erro) {
            throw new RuntimeException("Erro ao deletar uma movimentação: " + erro.getMessage());
        }
    }

    @Override
    public Long gerarId() {
        return gerarId(getArquivo(GERADOR_ID)).longValue();
    }

    public File getArquivo (String arquivoBancoDeDados) {
        try {
            File caminhoArquivo = new File(CAMINHO);

            if (!caminhoArquivo.exists())
                caminhoArquivo.mkdir();

            File arquivo = new File(caminhoArquivo,arquivoBancoDeDados);

            if (!arquivo.exists())
                arquivo.createNewFile();

            return arquivo;

        } catch (IOException erro) {
            throw new RuntimeException("Erro ao criar o arquivo de banco de dados: " + erro.getMessage());
        }
    }

    private Movimentacao buscaBinaria (List<Movimentacao> listaMovimentacoes, Long id) {
        int baixo = 0;
        int alto = listaMovimentacoes.size() - 1;

        while (baixo <= alto) {
            int meio = (baixo  + alto) / 2;

            Movimentacao movimentacaoBuscada = listaMovimentacoes.get(meio);

            if (movimentacaoBuscada.getId() == id) {
                return movimentacaoBuscada;
            }

            if  (movimentacaoBuscada.getId() > id) {
                alto = meio - 1;
            } else {
                baixo = meio + 1;
            }
        }
        return null;
    }

    private String converterMovimentacaoParaLinha(Movimentacao movimentacao) {

        return movimentacao.getId() + ";" +
                movimentacao.getVeiculo().getId() + ";" +
                movimentacao.getTipoDespesa().getIdTipoDespesa() + ";" +
                movimentacao.getDescricaoMovimentacao() + ";" +
                movimentacao.getDataMovimentacao() + ";" +
                movimentacao.getValorMovimentacao();
    }
}
