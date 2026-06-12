package com.gynlog.repository;

import java.io.*;

public abstract class GeradorIdTxt {

    protected Integer gerarId(File arquivo) {

        Integer ultimoId = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo))) {

            String linhaDoArquivo = bufferedReader.readLine();

            if (linhaDoArquivo != null && !linhaDoArquivo.trim().isEmpty())
                ultimoId = Integer.parseInt(linhaDoArquivo.trim());

        } catch (IOException erro) {

            throw new RuntimeException("Erro ao ler o ID: " + erro.getMessage());
        }

        Integer novoId = ultimoId + 1;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivo))) {

            bufferedWriter.write(String.valueOf(novoId));

        } catch (IOException erro) {

            throw new RuntimeException("Erro ao gerar o ID: " + erro.getMessage());
        }

        return novoId;
    }
}
