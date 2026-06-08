package com.gynlog.repository.impl;

import com.gynlog.model.entity.Movimentacao;
import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.model.entity.Veiculo;
import com.gynlog.repository.GeradorIdMovimentacao;
import com.gynlog.repository.MovimentacaoRepository;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoRepositoryImpl implements MovimentacaoRepository, GeradorIdMovimentacao {

    private static final String CAMINHO = "/database";
    private static final String ARQUIVO = "Movimentacoes.txt";
    private static final String GERADOR_ID = "Id_Movimentacoes.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public void criar(Movimentacao movimentacao) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getArquivo(ARQUIVO), true))) {

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

        List<Movimentacao> listaMovimentacoes = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getArquivo(ARQUIVO)))) {

            String linhaDoArquivo = bufferedReader.readLine();

            if (linhaDoArquivo ==  null) throw new RuntimeException("Nenhuma movimentação cadastrada!");

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

        List<Movimentacao> listaMovimentacoes = buscarTodasMovimentacoes();

        Movimentacao movimentacaoBuscada = buscaBinaria(listaMovimentacoes, id);

        return movimentacaoBuscada;
    }

    @Override
    public Movimentacao atualizar(Movimentacao movimentacao) {

        buscarPorId(movimentacao.getId());

        //VOLTAR AQUI PARA IMPLEMENTAR
       return null;
    }

    @Override
    public void deletar(Movimentacao movimentacao) {

        movimentacao = buscarPorId(movimentacao.getId());

        if (movimentacao == null) throw new RuntimeException("Movimentação não encontrada");

        List<Movimentacao> listaMovimentacoes = buscarTodasMovimentacoes();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getArquivo(ARQUIVO)))) {

            for (Movimentacao movimentacoes : listaMovimentacoes) {
                if (movimentacoes.getId() != movimentacao.getId()) {
                    criar(movimentacoes);
                } 
            }
        } catch (IOException erro) {
            throw new RuntimeException("Erro ao deletar uma movimentação: " + erro.getMessage());
        }
    }

    @Override
    public Long gerarId() {

        Long ultimoId = 0L;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getArquivo(GERADOR_ID)))) {

            String linhaDoArquivo = bufferedReader.readLine();

            if (linhaDoArquivo != null && !linhaDoArquivo.trim().isEmpty())
                ultimoId = Long.parseLong(linhaDoArquivo.trim());

        } catch (IOException erro) {
            throw new RuntimeException("Erro ao ler o ID: " + erro.getMessage());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getArquivo(GERADOR_ID)))) {

            Long novoId = ultimoId + 1;

            bufferedWriter.write(String.valueOf(novoId));

            return novoId;

        } catch (IOException erro) {
            throw new RuntimeException("Erro ao gerar o ID: " + erro.getMessage());
        }
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
}
