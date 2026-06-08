package com.gynlog.repository.impl;

import java.util.ArrayList;

import com.gynlog.model.entity.Veiculo;
import com.gynlog.repository.VeiculoRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;

public class VeiculoRepositoryImpl implements VeiculoRepository {
    File arquivo;

    public VeiculoRepositoryImpl(){
        try{
            String home = System.getProperty("user.home");
            File pasta = new File(home, "Veiculos");

            if(!pasta.exists()){
                pasta.mkdir();
            }

            arquivo = new File(pasta, "TipoDeVeiculos.txt");
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            

        }catch(Exception erro){
            String msg = "Persistencia - Construtor- " + erro.getMessage();
            System.out.println(msg);
        }
    }

    @Override
    public void salvar(Veiculo tipoDeVeiculo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public ArrayList<Veiculo> listaDeTipoDeVeiculo() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listaDeTipoDeVeiculo'");
    }

    @Override
    public Veiculo buscarPorID(int idTipoDeDespesa) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorID'");
    }

    @Override
    public void atualizar(Veiculo tipVeiculo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void remover(int idVeiculo) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
}
