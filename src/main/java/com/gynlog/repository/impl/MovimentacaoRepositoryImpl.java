package com.gynlog.repository.impl;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.repository.MovimentacaoRepository;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoRepositoryImpl implements MovimentacaoRepository {

    private static final String CAMINHO = "src/main/java/com/gynlog/database";
    private static final String ARQUIVO = "Movimentacoes.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public void criar(Movimentacao movimentacao) {

        File arquivoBanco = getArquivo();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivoBanco, true))) {

            String dadosEscrita =
                movimentacao.getId() + ";" +
                movimentacao.getVeiculo() + ";" + // falar com o Akira
                movimentacao.getTipoDespesa() + ";" + // falar com Augusto
                movimentacao.getDescricaoMovimentacao() + ";" +
                sdf.format(movimentacao.getDataMovimentacao()) + ";" +
                movimentacao.getValorMovimentacao();

            bufferedWriter.write(dadosEscrita);

        }  catch (IOException erro) {
            throw new RuntimeException("Erro ao criar uma movimentação: " + erro.getMessage());
        }
    }

    @Override
    public List<Movimentacao> buscarTodasMovimentacoes() {

        File arquivoBanco = getArquivo();

        List<Movimentacao> listaMovimentacoes = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoBanco))) {

            String linhaDoArquivo = bufferedReader.readLine();

            if (linhaDoArquivo ==  null) throw new RuntimeException("Nenhuma movimentação cadastrada");

            while (linhaDoArquivo != null) {

                Object camposBancoDeDados[] = linhaDoArquivo.split(";");

                Long id = (Long) camposBancoDeDados[0];
                Veiculo veiculo = (Veiculo) camposBancoDeDados[1];
                TipoDespesa tipoDespesa = (TipoDespesa) camposBancoDeDados[2];
                String descricaoMovimentacao = camposBancoDeDados[3].toString();
                LocalDateTime dataMovimentacao = (LocalDateTime) camposBancoDeDados[4];
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
        return null;
    }

    @Override
    public Movimentacao atualizar(Movimentacao movimentacao) {
        return null;
    }

    @Override
    public void deletar(Movimentacao movimentacao) {
    }


    public File getArquivo () {
        try {
            File caminhoArquivo = new File(CAMINHO);

            if (!caminhoArquivo.exists())
                caminhoArquivo.mkdir();

            File arquivo = new File(caminhoArquivo,ARQUIVO);

            if (!arquivo.exists())
                arquivo.createNewFile();

            return arquivo;

        } catch (IOException erro) {
            throw new RuntimeException("Erro ao criar o arquivo de banco de dados: " + erro.getMessage());
        }
    }
}
