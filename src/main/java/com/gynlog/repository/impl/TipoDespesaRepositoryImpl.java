package com.gynlog.repository.impl;

import com.gynlog.model.entity.TipoDespesa;
import com.gynlog.repository.TipoDespesaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDespesaRepositoryImpl implements TipoDespesaRepository {

    //Atributos
    private String nomeDoArquivoNoDisco = null;
    //Metodo Construtor
    public TipoDespesaRepositoryImpl(){
        String home = System.getProperty("user.home");
        String base = new File("").getAbsolutePath();
        final String CAMINHO = "src/main/java/com/gynlog/database";
        final String ARQUIVO = "TipoDespesa.txt";

        nomeDoArquivoNoDisco = CAMINHO + "/" + ARQUIVO;
    }
    @Override
    public void salvar(TipoDespesa TipoDespesa) throws Exception {
        try{
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco,true);
            //Criar o buffer do arquivo
            BufferedWriter bw =new BufferedWriter(fw);
            //Escreve no arquivo
            String str = TipoDespesa.getIdTipoDespesa() + ";";
            str += TipoDespesa.getDescricao() + "\n";
            bw.write(str);
            //fecha o arquivo
            bw.close();
        }catch(Exception erro){
            String msg = "Persistencia - Metodo Salvar - "+erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public List<TipoDespesa> listaDeTiposDeDespesas() throws Exception {
        return List.of();
    }

    @Override
    public List<TipoDespesa> listaDeTiposDespesas() throws Exception {
        try{
            List<TipoDespesa> listaDeTiposDespesas = new ArrayList<>();
            //abrir um arquivo existente
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine())!=null){
                String[] vetorStr = linha.split(";");
                TipoDespesa objTipoDespesa = null;
                int idTipoDespesaAux = Integer.parseInt(vetorStr[0]);
                String descricao = vetorStr[1];
                objTipoDespesa = new TipoDespesa(idTipoDespesaAux,descricao);
                listaDeTiposDespesas.add(objTipoDespesa);
            }
            br.close();
            return listaDeTiposDespesas;
        }catch(Exception erro){
            String msg = "Persistencia - Metodo Lista - "+erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public TipoDespesa buscarPorId(int idTipoDespesa) throws Exception {
        try{
            //abrir um arquivo existente
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine())!=null){
                String[] vetorStr = linha.split(";");
                int idTipoDespesaAux = Integer.parseInt(vetorStr[0]);
                if(idTipoDespesaAux == idTipoDespesa){
                    String descricao = vetorStr[1];
                    TipoDespesa objTipoDespesa = null;
                    objTipoDespesa = new TipoDespesa(idTipoDespesaAux,descricao);
                    br.close();
                    return objTipoDespesa;
                }
            }
            br.close();
            return null;
        }catch(Exception erro){
            String msg = "Persistencia - Metodo Buscar - "+erro.getMessage();
            throw new Exception(msg);
        }
    }
    @Override
    public TipoDespesa buscarPorDescricao(String descricao) throws Exception {
        try{
            //abrir um arquivo existente
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedReader br  = new BufferedReader(fr);
            String linha = "";
            while((linha=br.readLine())!=null){
                String[] vetorStr = linha.split(";");
                String descricaoAux = vetorStr[1];
                if(descricaoAux.equalsIgnoreCase(descricao)){
                    int idTipoDespesa = Integer.parseInt(vetorStr[0]);
                    TipoDespesa objTipoDespesa = null;
                    objTipoDespesa = new TipoDespesa(idTipoDespesa,descricao);
                    br.close();
                    return objTipoDespesa;
                }
            }
            br.close();
            return null;
        }catch(Exception erro){
            String msg = "Persistencia - Metodo Buscar - "+erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public void atualizar(TipoDespesa TipoDespesa) throws Exception {
        try {
            List<TipoDespesa> listagem = null;
            listagem = this.listaDeTiposDespesas();
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedWriter bw =new BufferedWriter(fw);
            for(TipoDespesa obj : listagem){
                if(obj.getIdTipoDespesa() == TipoDespesa.getIdTipoDespesa()){
                    String str = TipoDespesa.getIdTipoDespesa() + ";";
                    str += TipoDespesa.getDescricao() + "\n";
                    bw.write(str);
                }else{
                    String str = obj.getIdTipoDespesa() + ";";
                    str += obj.getDescricao() + "\n";
                    bw.write(str);
                }
            }
            bw.close();
        }catch(Exception erro){
            String msg = "Persistencia - Metodo Atualizar - "+erro.getMessage();
            throw new Exception(msg);
        }
    }

    @Override
    public void remover(int idTipoDespesa) throws Exception {
        try {
            List<TipoDespesa> listagem = null;
            listagem = this.listaDeTiposDespesas();
            //cria o arquivo
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
            //Criar o buffer do arquivo
            BufferedWriter bw =new BufferedWriter(fw);
            for(TipoDespesa obj : listagem){
                if(obj.getIdTipoDespesa() != idTipoDespesa){
                    String str = obj.getIdTipoDespesa() + ";";
                    str += obj.getDescricao() + "\n";
                    bw.write(str);
                }
            }
            bw.close();
        }catch(Exception erro){
            String msg = "Persistencia - Metodo Remover - "+erro.getMessage();
            throw new Exception(msg);
        }
    }

}
